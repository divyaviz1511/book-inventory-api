package com.bookinventory.book_inventory.messaging.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bookinventory.book_inventory.dto.messagequeues.BookEventDTO;

@Component
public class BookEventsSender {
    @Qualifier("eventRabbitTemplate")
    private RabbitTemplate eventRabbitTemplate;

    public BookEventsSender(RabbitTemplate eventRabbitTemplate){
        this.eventRabbitTemplate = eventRabbitTemplate;
    }

    public void eventSend(String eventName, BookEventDTO bookEventDTO) {
        eventRabbitTemplate.convertAndSend(RabbitMQEventsQueue.EXCHANGE_NAME, eventName, bookEventDTO);
    }
}
