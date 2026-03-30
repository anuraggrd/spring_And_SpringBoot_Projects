package com.ecom.order.service;

import com.app.ecom.dto.CartRequest;
import com.app.ecom.dto.CartResponse;
import com.app.ecom.entity.Cart;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import com.app.ecom.repository.CartRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public Boolean addItemToCart(Long userId, CartRequest request) {

        Optional<Product> productopt = productRepo.findById(request.getProductId());
        if (productopt.isEmpty())
            return false;
        Product existingProduct = productopt.get();
        if (existingProduct.getStockQuantity() < (request.getQuantity() * 1L))
            return false;

        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty())
            return false;

        User existingUser = userOpt.get();
        Cart existingCart = cartRepo.findByUserAndProduct(existingUser, existingProduct);
        if (existingCart != null) {
            int quantity = existingCart.getQuantity() + request.getQuantity();
            existingCart.setQuantity(quantity);
            BigDecimal cartprice = existingProduct.getPrice().multiply(BigDecimal.valueOf(existingCart.getQuantity()));
            existingCart.setPrice(cartprice);
            cartRepo.save(existingCart);
        } else {
            Cart newCart = new Cart();
            newCart.setPrice(existingProduct.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            newCart.setQuantity(request.getQuantity());
            newCart.setProduct(existingProduct);
            newCart.setUser(existingUser);
            cartRepo.save(newCart);
        }
       // existingProduct.setStockQuantity(existingProduct.getStockQuantity() - request.getQuantity());
       // productRepo.save(existingProduct);
        return true;
    }

    public boolean deletecartItem(Long userId, Long productId){
        Optional<Product> productopt = productRepo.findById(productId);
        if (productopt.isEmpty())
            return false;
        Product existingProduct = productopt.get();

        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty())
            return false;

        User existingUser = userOpt.get();
        cartRepo.deleteCartByUserAndProduct(existingUser,existingProduct);

        return true;
    }

    public List<CartResponse> fetchCartItems(Long userId) {

        Optional<User> userOpt = userRepo.findById(userId);
        User existingUser = userOpt.get();
        return cartRepo.findAllByUser(existingUser).stream().map(CartService::mapToResponse).collect(Collectors.toList());
    }

    public static CartResponse mapToResponse(Cart cart){
        CartResponse response = new CartResponse();
        response.setQuantity(cart.getQuantity());
        response.setProduct(cart.getProduct());
        response.setAmount(cart.getPrice());
        return response ;

    }

    public void clearCart(String userId) {
        userRepo.findById(Long.valueOf(userId)).ifPresent(cartRepo::deleteByUser);
    }
}