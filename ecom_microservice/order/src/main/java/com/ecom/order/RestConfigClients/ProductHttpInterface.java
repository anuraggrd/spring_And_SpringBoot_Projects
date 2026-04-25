package com.ecom.order.RestConfigClients;

import com.ecom.order.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductHttpInterface {
    @GetExchange("/getmsg")
    String getInstanceInfo();

     @GetExchange("/api/product/{id}")
     ProductResponse getproductDetails(@PathVariable Long id);
}
