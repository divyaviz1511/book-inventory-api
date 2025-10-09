package com.bookinventory.book_inventory.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.bookinventory.book_inventory.model.LowStockAlertEntity;

@Component
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(LowStockAlertEntity lowStockAlertEntity) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, lowStockAlertEntity);
        System.out.println("Sent: " + lowStockAlertEntity.getMessage());
    }
}
