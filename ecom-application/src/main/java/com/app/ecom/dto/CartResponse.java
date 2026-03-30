package com.app.ecom.dto;

import com.app.ecom.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartResponse {
    private Integer quantity;
    private Product product;
    private BigDecimal amount;
}
