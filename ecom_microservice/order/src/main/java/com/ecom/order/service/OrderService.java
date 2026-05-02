package com.ecom.order.service;



import com.ecom.order.dto.CartResponse;
import com.ecom.order.dto.OrderCretedEvent;
import com.ecom.order.dto.OrderItemDto;
import com.ecom.order.dto.OrderResponse;
import com.ecom.order.entity.Order;

import com.ecom.order.entity.OrderItem;
import com.ecom.order.entity.OrderStatus;
import com.ecom.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderrepo;
    private final CartService cartservice;
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.queue.name}")
    private String queuename;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

   // private final UserRepository userrepo;


    public Optional<OrderResponse> addOrder(String userId) {
        //validate the cartItem
        List<CartResponse> cartItems = cartservice.fetchCartItems(userId);
        if (cartItems.isEmpty()){
            return Optional.empty();
        }
//        //validate the user
//        Optional<User> useropt = userrepo.findById(Long.valueOf(userId));
//        if(useropt.isEmpty()){
//            return Optional.empty();
//        }
//        User user = useropt.get();
//        // calculate the price
        BigDecimal totalprice = cartItems.stream().map(CartResponse::getAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        //create the order.
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRM);
        order.setTotalAmount(totalprice);
        List<OrderItem> orderItems = cartItems.stream().map(
                i ->
                        new OrderItem(
                                null,
                                i.getProductId(),
                                i.getQuantity(),
                                i.getAmount(),
                                order)

        ).collect(Collectors.toList());
        order.setItems(orderItems);
        Order savedOrder = orderrepo.save(order);
        //clear the cart
        cartservice.clearCart(userId);

        OrderCretedEvent event = new OrderCretedEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getStatus(),
                mapToOrderItemDTOs(savedOrder.getItems()),
                savedOrder.getTotalAmount(),
                savedOrder.getCreatedAt()
        );

        rabbitTemplate.convertAndSend(exchangeName,routingKey ,event);
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
                         item.getProductId(),
                         item.getQuantity(),
                         item.getPrice(),
                         item.getPrice().
                                 multiply(new BigDecimal(item.getQuantity()))
                        )
                ).toList(),
                savedOrder.getCreatedAt()
                );
    }

    private List<OrderItemDto> mapToOrderItemDTOs(List<OrderItem> items){
       return items.stream()
                .map(item -> new OrderItemDto(
                        item.getId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getPrice().multiply(new BigDecimal(item.getQuantity()))
                )).collect(Collectors.toList());


    }
}
