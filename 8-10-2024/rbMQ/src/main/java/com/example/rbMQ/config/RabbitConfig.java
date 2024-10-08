package com.example.rbMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "messageQueue";
    public static final String EXCHANGE_NAME = "messageExchange";
    public static final String ROUTING_KEY = "messageRoutingKey";

    @Bean
    public Queue messageQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange messageExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue messageQueue, TopicExchange messageExchange) {
        return BindingBuilder.bind(messageQueue).to(messageExchange).with(ROUTING_KEY);
    }
}
