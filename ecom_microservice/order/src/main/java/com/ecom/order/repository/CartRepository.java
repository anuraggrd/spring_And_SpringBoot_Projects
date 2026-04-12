package com.ecom.order.repository;


import com.ecom.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserIdAndProductId(String userId, String productId);

    void deleteCartByUserIdAndProductId(String userId, String productId);

    List<Cart> findAllByUserId(String userId);

    void deleteByUserId(String userId);
}
