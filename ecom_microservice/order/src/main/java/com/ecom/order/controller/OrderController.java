package com.ecom.order.controller;


import com.ecom.order.dto.OrderResponse;
import com.ecom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    private final OrderService orderservice;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("x-user-id") String userId){
       log.info("placing order for user id {}", userId);
       return  orderservice.addOrder(userId).map(orderResponse ->
                 new ResponseEntity<>(orderResponse,HttpStatus.CREATED))
                 .orElse(ResponseEntity.badRequest().build());


    }
}
