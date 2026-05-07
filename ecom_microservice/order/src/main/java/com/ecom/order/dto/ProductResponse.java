package com.ecom.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductResponse {
    private String name;
    private String description;

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

    private BigDecimal price;
    private String category;
    private Long stockQuantity;
    private String imageUrl;
    private Boolean active;
}
