package com.store.omega.api.middleware;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "sales-events";

    public static final String SALES_QUEUE = "sales-queue";

    public static final String SALES_QUEUE_KEY = "routingKey.sales";

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue newSaleQueue() {
        return new Queue(SALES_QUEUE);
    }

    @Bean
    public Binding BindingNewSaleQueue() {
        return BindingBuilder
                .bind(newSaleQueue())
                .to(getTopicExchange())
                .with(SALES_QUEUE_KEY);
    }
}
