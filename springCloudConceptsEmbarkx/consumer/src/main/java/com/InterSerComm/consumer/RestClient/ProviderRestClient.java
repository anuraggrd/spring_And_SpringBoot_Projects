package com.InterSerComm.consumer.RestClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProviderRestClient {

    private final RestClient restClient;
    public String getMsg(){
        return restClient.get().uri("/getmsg")
                .retrieve()
                .body(String.class);
    }
}
