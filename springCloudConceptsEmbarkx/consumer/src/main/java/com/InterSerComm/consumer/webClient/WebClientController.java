package com.InterSerComm.consumer.webClient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/web-client/")
@RequiredArgsConstructor
public class WebClientController {

    private final ProviderWebClient providerwebClient;


    @GetMapping("/msg")
    public Mono<String> getMsg() {
//        WebClient webclient = WebClient.create();
//        Mono<String> response = webclient.get()
//                .uri("http://localhost:8081/getmsg")
//                .retrieve()
//                .bodyToMono(String.class);
//        return response;

        return providerwebClient.getMsg();
    }

}
