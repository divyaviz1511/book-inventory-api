package com.bookinventory.book_inventory.messaging.alerts;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bookinventory.book_inventory.model.LowStockAlertEntity;

@Component
public class AlertMessageSender {
    @Qualifier("alertRabbitTemplate")
    private final RabbitTemplate alertRabbitTemplate;

    public AlertMessageSender(RabbitTemplate alertRabbitTemplate) {
        this.alertRabbitTemplate = alertRabbitTemplate;
    }

    public void send(LowStockAlertEntity lowStockAlertEntity) {
        alertRabbitTemplate.convertAndSend(RabbitMQAlertQueueConfig.QUEUE_NAME, lowStockAlertEntity);
        System.out.println("Sent: " + lowStockAlertEntity.getMessage());
    }
}
