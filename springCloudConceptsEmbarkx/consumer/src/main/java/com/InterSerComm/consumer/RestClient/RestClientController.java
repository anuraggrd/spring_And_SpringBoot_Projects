package com.InterSerComm.consumer.RestClient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/rest-client")
@RequiredArgsConstructor
public class RestClientController {

    private final ProviderRestClient providerRestClient;
    @GetMapping("/msg")
    public String getMsg() {
// RestClient restclient =  RestClient.create();
//        String response = restclient.get().uri("http://localhost:8081/getmsg")
//                .retrieve()
//                .body(String.class);
//        return response;

       return providerRestClient.getMsg();
    }
}
