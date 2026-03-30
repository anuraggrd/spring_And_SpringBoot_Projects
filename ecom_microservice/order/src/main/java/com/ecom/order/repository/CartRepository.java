package com.ecom.order.repository;

import com.app.ecom.entity.Cart;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserAndProduct(User user, Product product);

    void deleteCartByUserAndProduct(User user, Product product);

    List<Cart> findAllByUser(User user);

    void deleteByUser(User user);
}
