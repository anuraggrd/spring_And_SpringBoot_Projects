package com.InterSerComm.consumer.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    @Bean
    public WebClient webclient(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8081")
                .build();

    }
}
