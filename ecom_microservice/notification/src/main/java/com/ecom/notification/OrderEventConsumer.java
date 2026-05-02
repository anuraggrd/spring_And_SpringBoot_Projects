package com.ecom.notification;

import com.ecom.notification.payloads.OrderCretedEvent;
import com.ecom.notification.payloads.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderEventConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderEvent(OrderCretedEvent orderEvents){
        System.out.println(orderEvents);
        long orderId = orderEvents.getOrderId();
        OrderStatus orderSatus = orderEvents.getStatus();
        /// order status
        System.out.println(orderId);
        System.out.println(orderSatus);
        //send Notification
        // Update database
        //send Emails
        // generate invoice
        //send sellar notification
    }
}
