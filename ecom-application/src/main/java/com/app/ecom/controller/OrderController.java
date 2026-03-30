package com.app.ecom.controller;

import com.app.ecom.dto.OrderResponse;
import com.app.ecom.entity.Order;
import com.app.ecom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderservice;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("x-user-id") String userId){

       return  orderservice.addOrder(userId).map(orderResponse ->
                 new ResponseEntity<>(orderResponse,HttpStatus.CREATED))
                 .orElse(ResponseEntity.badRequest().build());


    }
}
