package com.app.ecom.service;

import com.app.ecom.dto.CartResponse;
import com.app.ecom.dto.OrderItemDto;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.entity.*;
import com.app.ecom.repository.CartRepository;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderrepo;
    private final CartService cartservice;
    private final UserRepository userrepo;


    public Optional<OrderResponse> addOrder(String userId) {
        //validate the cartItem
        List<CartResponse> cartItems = cartservice.fetchCartItems(Long.valueOf(userId));
        if (cartItems.isEmpty()){
            return Optional.empty();
        }
        //validate the user
        Optional<User> useropt = userrepo.findById(Long.valueOf(userId));
        if(useropt.isEmpty()){
            return Optional.empty();
        }
        User user = useropt.get();
        // calculate the price
        BigDecimal totalprice = cartItems.stream().map(CartResponse::getAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        //create the order.
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRM);
        order.setTotalAmount(totalprice);
        List<OrderItem> orderItems = cartItems.stream().map(
                i ->
                        new OrderItem(
                                null,
                                i.getProduct(),
                                i.getQuantity(),
                                i.getAmount(),
                                order)

        ).collect(Collectors.toList());
        order.setItems(orderItems);
        Order savedOrder = orderrepo.save(order);
        //clear the cart
        cartservice.clearCart(userId);

        return Optional.of(maptoOrderResponse(savedOrder));

    }

    private OrderResponse maptoOrderResponse(Order savedOrder) {

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getItems().stream().map(
                        item-> new OrderItemDto(
                         item.getId(),
                         item.getProduct().getId(),
                         item.getQuantity(),
                         item.getPrice(),
                         item.getPrice().
                                 multiply(new BigDecimal(item.getQuantity()))
                        )
                ).toList(),
                savedOrder.getCreatedAt()
                );
    }
}
