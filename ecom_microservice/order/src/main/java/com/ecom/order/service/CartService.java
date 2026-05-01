package com.ecom.order.service;


import com.ecom.order.RestConfigClients.ProductHttpInterface;
import com.ecom.order.RestConfigClients.UserHttpInterface;
import com.ecom.order.dto.CartRequest;
import com.ecom.order.dto.CartResponse;
import com.ecom.order.dto.ProductResponse;
import com.ecom.order.dto.UserResponse;
import com.ecom.order.entity.Cart;
import com.ecom.order.repository.CartRepository;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {

    private final CartRepository cartRepo;
    private final ProductHttpInterface productClient;
    private final UserHttpInterface userClient;
    int attempt = 0;


//    @CircuitBreaker(name = "productService", fallbackMethod = "addToCartFallback")
   @Retry(name = "retryBreaker", fallbackMethod = "addToCartFallback")
    public Boolean addItemToCart(String userId, CartRequest request) {
        log.info("userid :-" + userId);
        log.info("productId :-" + request.getProductId());
       System.out.println("ATTEMPT COUNT: " + ++attempt);
        ProductResponse productopt = productClient.getproductDetails(request.getProductId());
        if (productopt == null)
            return false;
            System.out.println(productopt);
        if (productopt.getStockQuantity() < (request.getQuantity() * 1L))
            return false;

        UserResponse  userOpt = userClient.getUser(String.valueOf(userId));
        if (userOpt== null)
            return false;

        String existingUserId = String.valueOf(userId);
        String existingProductId = String.valueOf(request.getProductId());
        Cart existingCart = cartRepo.findByUserIdAndProductId(existingUserId, existingProductId);
        if (existingCart != null) {
            int quantity = existingCart.getQuantity() + request.getQuantity();
            existingCart.setQuantity(quantity);
            BigDecimal cartprice = BigDecimal.valueOf(1000.00);
            existingCart.setPrice(cartprice);
            cartRepo.save(existingCart);
        } else {
            Cart newCart = new Cart();
            newCart.setPrice(BigDecimal.valueOf(1000.00));
            newCart.setQuantity(request.getQuantity());
            newCart.setProductId(String.valueOf(request.getProductId()));
            newCart.setUserId(existingUserId);
            cartRepo.save(newCart);
        }
       // existingProduct.setStockQuantity(existingProduct.getStockQuantity() - request.getQuantity());
       // productRepo.save(existingProduct);
        return true;
    }
    public Boolean addToCartFallback(String userId,
                                     CartRequest request,
                                     Throwable throwable) {
        log.error("Fallback while adding product {} to cart for user {}",
                request.getProductId(), userId, throwable);
        return false;
    }

    public boolean deletecartItem(Long userId, Long productId){

        cartRepo.deleteCartByUserIdAndProductId(String.valueOf(userId), String.valueOf(productId));

        return true;
    }

    public List<CartResponse> fetchCartItems(String userId) {

      //  Optional<User> userOpt = userRepo.findById(userId);
      //  User existingUser = userOpt.get();
        return cartRepo.findAllByUserId(String.valueOf(userId)).stream().map(CartService::mapToResponse).collect(Collectors.toList());
    }

    public static CartResponse mapToResponse(Cart cart){
        CartResponse response = new CartResponse();
        response.setQuantity(cart.getQuantity());
        response.setProductId(cart.getProductId());
        response.setAmount(cart.getPrice());
        return response ;

    }

    public void clearCart(String userId) {
        cartRepo.deleteByUserId(userId);
       // userRepo.findById(Long.valueOf(userId)).ifPresent(cartRepo::deleteByUserId);
    }



}
