package com.demo.KafkaConsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
    public void listen( Rider riderlocation){
        System.out.println("Message Received 1:"+ riderlocation.getRiderId());
        System.out.println("Message Received 2:"+ riderlocation.getLongitude());
        System.out.println("Message Received 3:"+ riderlocation.getLatitude());
    }
}
