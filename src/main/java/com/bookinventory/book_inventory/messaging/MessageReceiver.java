package com.bookinventory.book_inventory.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookinventory.book_inventory.model.LowStockAlertEntity;
import com.bookinventory.book_inventory.service.AlertService;

@Component
public class MessageReceiver {
    @Autowired
    private AlertService alertService;
    
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive(LowStockAlertEntity lowStockAlertEntity) {
        System.out.println("Received: " + lowStockAlertEntity.getMessage());
        alertService.saveAlertMessage(lowStockAlertEntity);

    }
}
 