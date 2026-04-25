package com.ecom.product.dto;

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

    @Override
    public String toString() {
        return "ProductResponse{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", imageUrl='" + imageUrl + '\'' +
                ", active=" + active +
                '}';
    }

    private Long stockQuantity;
    private String imageUrl;
    private Boolean active;
}
