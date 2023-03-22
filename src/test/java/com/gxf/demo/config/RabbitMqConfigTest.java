package com.gxf.demo.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

class RabbitMqConfigTest {

    private static RabbitMqConfig rabbitMqConfig;

    @BeforeAll
    static void setUp() {
        rabbitMqConfig = new RabbitMqConfig();
    }

    @Test
    void directQueue() {
        Queue queue = rabbitMqConfig.directQueue();
        Assertions.assertNotNull(queue);
    }

    @Test
    void directExchange() {
        DirectExchange exchange = rabbitMqConfig.directExchange();
        Assertions.assertNotNull(exchange);
    }

    @Test
    void bindingDirect() {
        Binding binding = rabbitMqConfig.bindingDirect(rabbitMqConfig.directQueue(), rabbitMqConfig.directExchange());
        Assertions.assertNotNull(binding);
    }
}