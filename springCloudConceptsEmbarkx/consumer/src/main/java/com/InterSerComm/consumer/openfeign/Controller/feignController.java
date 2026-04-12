package com.InterSerComm.consumer.openfeign.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/provider")
public class feignController {
    @Autowired
   private  providerFeign feignClient;

    @GetMapping("/promsg")
    public String getMsgInfo()
    {
        return feignClient.getInstancemsg();
    }
}
