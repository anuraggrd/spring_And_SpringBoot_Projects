package com.ecomerce.order.dto;

import lombok.Data;

@Data
public class CartRequest {
    private Integer quantity;
    private Long productId;
}
