package com.InterSerComm.consumer.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="provider" , url ="http://localhost:8081")
public interface providerFeign {

    @GetMapping("/getmsg")
    String getInstancemsg();
}
