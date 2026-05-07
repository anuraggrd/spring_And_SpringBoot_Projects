package com.ecom.product.repository;


import com.ecom.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    @Query("select p from products p where p.active=true and p.stockQuantity>0 " +
            "and LOWER(p.name) like lower(concat('%', :keyword , '%')) ")
    List<Product> searchProductBykeyword(@Param("keyword") String keyword);

    Optional<Product> findByIdAndActiveTrue(Long id);
}
