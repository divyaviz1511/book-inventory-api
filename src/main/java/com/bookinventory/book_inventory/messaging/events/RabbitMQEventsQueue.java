package com.bookinventory.book_inventory.messaging.events;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQEventsQueue {
    public static final String QUEUE_NAME="book.cache";
    public static final String EXCHANGE_NAME = "book.events.exchange";

    @Bean
    public Queue bookEventsQueue() {
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    public TopicExchange bookEventTopicExchange() {
        return new TopicExchange(EXCHANGE_NAME);

    }

    @Bean
    public Binding bookEventsBinding( @Qualifier("bookEventsQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("book.#");
    }

    @Bean
    public MessageConverter jsonMessageConverterForEventQueue(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name="eventRabbitTemplate")
    public RabbitTemplate eventRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverterForEventQueue());
        return template;
    }
}
