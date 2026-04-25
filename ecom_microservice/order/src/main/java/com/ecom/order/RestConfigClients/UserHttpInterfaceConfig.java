package com.ecom.order.RestConfigClients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class UserHttpInterfaceConfig {
    

    @Bean
    @LoadBalanced
    public RestClient.Builder restUserClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public UserHttpInterface restUserClientHttpInterface(RestClient.Builder restUserClientBuilder) {
        RestClient restClient = restUserClientBuilder.baseUrl("http://user")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request,response)-> Optional.empty()))
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        UserHttpInterface service = factory.createClient(UserHttpInterface.class);
        return service;
    }
    
}
