package com.demo.Kafkaproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigTopic {
    @Bean
    public NewTopic createMyTopic(){
        return new NewTopic("New-Topic",3,(short)1);
    }
}
