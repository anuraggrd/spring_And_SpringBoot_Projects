package com.ecom.order.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartResponse {
    private Integer quantity;
    private String productId;
    private BigDecimal amount;
}
