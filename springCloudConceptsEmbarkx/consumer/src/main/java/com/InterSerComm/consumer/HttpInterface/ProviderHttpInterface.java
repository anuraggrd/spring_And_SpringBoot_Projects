package com.InterSerComm.consumer.HttpInterface;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProviderHttpInterface {
    @GetExchange("/getmsg")
    String getInstanceInfo();
}
