package com.bookinventory.book_inventory.messaging.alerts;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookinventory.book_inventory.model.LowStockAlertEntity;
import com.bookinventory.book_inventory.service.AlertService;

@Component
public class AlertMessageReceiver {
    @Autowired
    private AlertService alertService;
    
    @RabbitListener(queues = RabbitMQAlertQueueConfig.QUEUE_NAME)
    public void receive(LowStockAlertEntity lowStockAlertEntity) {
        System.out.println("Received: " + lowStockAlertEntity.getMessage());
        alertService.saveAlertMessage(lowStockAlertEntity);

    }
}
 