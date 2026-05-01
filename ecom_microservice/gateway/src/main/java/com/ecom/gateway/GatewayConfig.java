package com.ecom.gateway;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

@Configuration
public class GatewayConfig {
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10,20,1);
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
            String key = remoteAddress != null && remoteAddress.getAddress() != null
                    ? remoteAddress.getAddress().getHostAddress()
                    : "anonymous";
            return Mono.just(key);
        };
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r
                        .path("/api/product/**")
                        .filters(f -> f.retry(retryConfig -> retryConfig
                                        .setRetries(10)
                                        .setMethods(HttpMethod.GET)
                                )
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(ipKeyResolver())
                                        )
                                .circuitBreaker(config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/products")))
//                        .filters(f -> f.rewritePath("/products(?<segment>/?.*)",
//                                "/api/products${segment}"))
                        .uri("lb://PRODUCT-SERVICE"))
                .route("user-service", r -> r
                        .path("/user/api/**")
//                        .filters(f -> f.rewritePath("/users(?<segment>/?.*)",
//                                "/api/users${segment}"))
                        .uri("lb://USER-SERVICE"))
                .route("order-service", r -> r
                        .path("/api/order/**", "/api/cart/**")
//                        .filters(f -> f.rewritePath("/(?<segment>.*)",
//                                "/api/${segment}"))
                        .uri("lb://ORDER-SERVICE"))
                .route("eureka-server", r -> r
                        .path("/eureka/main")
                        .filters(f -> f.rewritePath("/eureka/main", "/"))
                        .uri("http://localhost:8761"))
                .route("eureka-server-static", r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761"))
                .build();
    }
}
