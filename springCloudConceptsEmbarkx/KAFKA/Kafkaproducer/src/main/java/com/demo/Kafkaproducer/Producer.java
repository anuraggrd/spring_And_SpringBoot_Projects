package com.demo.Kafkaproducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Producer {

    private final KafkaTemplate<String,Rider> kafkaTemplate;

    public Producer(KafkaTemplate<String, Rider> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @PostMapping("/send")
        public String SendMessage(@RequestParam String message){
        Rider riderlocation = new Rider("rider123",10.22,65.77);
        kafkaTemplate.send("my-topic",riderlocation);
        return "Message Sent: " + riderlocation.getRiderId();
        }

}
