package com.ecomerce.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ProductResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Long stockQuantity;
    private String imageUrl;
    private Boolean active;
}
