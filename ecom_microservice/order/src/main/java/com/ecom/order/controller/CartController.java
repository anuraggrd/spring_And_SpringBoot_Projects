package com.ecom.order.controller;



import com.ecom.order.dto.CartRequest;
import com.ecom.order.dto.CartResponse;
import com.ecom.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartservice;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("x-user-id") Long userId,
            @RequestBody CartRequest request
    ) {
        return cartservice.addItemToCart(userId, request) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteCart(
            @RequestHeader("x-user-id") Long userId,
            @PathVariable Long productId
    ) {
        return cartservice.deletecartItem(userId, productId)
                ? ResponseEntity.ok("Deleted cart")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such cart");
    }


    @GetMapping
    public ResponseEntity<List<CartResponse>> getCartitems(
            @RequestHeader("x-user-id") Long userId
    ) {
        List<CartResponse> listCartItems = cartservice.fetchCartItems(userId);
        return ResponseEntity.ok(listCartItems) ;
    }

}
