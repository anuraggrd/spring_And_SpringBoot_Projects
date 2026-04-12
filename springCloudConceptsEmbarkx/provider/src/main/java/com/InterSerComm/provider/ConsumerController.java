package com.InterSerComm.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Value("${server.port}")
    String port;

    String uuid = String.valueOf(java.util.UUID.randomUUID());
    @GetMapping("/getmsg")
    public String getInstancemsg(){
        System.out.println("Getting request From Consumer");
        return "MsgComming from port "+ port + "instance ID " + uuid;
    }
}
