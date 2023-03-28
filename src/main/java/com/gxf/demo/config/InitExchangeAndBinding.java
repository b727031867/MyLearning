package com.gxf.demo.config;

import com.gxf.demo.enumeration.RabbitMqExchangeEnumeration;
import com.gxf.demo.enumeration.RabbitMqQueueEnum;
import com.gxf.demo.enumeration.RoutingKeyEnumeration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ的交换机和队列绑定关系
 *
 * @author GXF
 */
@Slf4j
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class InitExchangeAndBinding {

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 直连交换机
     */
    @Bean("rabbitMqDirectExchange")
    protected DirectExchange createRabbitMqDirectExchange() {
        // 持久交换机，在应用重启后依旧可以保留此交换机
        DirectExchange exchange = new DirectExchange(RabbitMqExchangeEnumeration.DIRECT_EXCHANGE.getCode(), true, false);
        amqpAdmin.declareExchange(exchange);
        log.info("rabbitMq direct exchange created successfully!");
        return exchange;
    }

    /**
     * 主题交换机
     */
    @Bean("rabbitMqTopicExchange")
    protected TopicExchange createRabbitMqTopicExchange() {
        TopicExchange exchange = new TopicExchange(RabbitMqExchangeEnumeration.TOPIC_EXCHANGE.getCode(), true, false);
        amqpAdmin.declareExchange(exchange);
        log.info("rabbitMq topic exchange created successfully!");
        return exchange;
    }

    /**
     * 广播交换机
     */
    @Bean("rabbitMqFanoutExchange")
    protected FanoutExchange createRabbitMqFanoutExchange() {
        FanoutExchange exchange = new FanoutExchange(RabbitMqExchangeEnumeration.FANOUT_EXCHANGE.getCode(), true, false);
        amqpAdmin.declareExchange(exchange);
        log.info("rabbitMq fanout exchange created successfully!");
        return exchange;
    }


    @Bean("rabbitMqDirectQueue")
    protected Queue rabbitMqDirectQueue() {
        Queue queue = new Queue(RabbitMqQueueEnum.DIRECT_QUEUE.getQueueName(), true, false, false);
        amqpAdmin.declareQueue(queue);
        log.debug("rabbitMq direct queue created successfully!");
        return queue;
    }

    @Bean("rabbitMqTopicQueue")
    protected Queue rabbitMqTopicQueue() {
        Queue queue = new Queue(RabbitMqQueueEnum.TOPIC_QUEUE.getQueueName(), true, false, false);
        amqpAdmin.declareQueue(queue);
        log.debug("rabbitMq topic queue created successfully!");
        return queue;
    }

    @Bean("rabbitMqFanoutQueue1")
    protected Queue rabbitMqFanoutQueue1() {
        Queue queue = new Queue(RabbitMqQueueEnum.FANOUT_QUEUE_ONE.getQueueName(), true, false, false);
        amqpAdmin.declareQueue(queue);
        log.debug("rabbitMq fanout queue 1 created successfully!");
        return queue;
    }

    @Bean("rabbitMqFanoutQueue2")
    protected Queue rabbitMqFanoutQueue2() {
        Queue queue = new Queue(RabbitMqQueueEnum.FANOUT_QUEUE_TWO.getQueueName(), true, false, false);
        amqpAdmin.declareQueue(queue);
        log.debug("rabbitMq fanout queue 2 created successfully!");
        return queue;
    }

    @Bean
    protected Binding bindingDirectQueue(Queue rabbitMqDirectQueue, DirectExchange rabbitMqDirectExchange) {
        //绑定结构：队列-交换机-路由key
        Binding binding = BindingBuilder.bind(rabbitMqDirectQueue).to(rabbitMqDirectExchange).with(RoutingKeyEnumeration.DIRECT_KEY.getCode());
        amqpAdmin.declareBinding(binding);
        log.debug("direct queue binding created successfully!");
        return binding;
    }

    @Bean
    protected Binding bindingTopicQueue(Queue rabbitMqTopicQueue, TopicExchange rabbitMqTopicExchange) {
        Binding binding = BindingBuilder.bind(rabbitMqTopicQueue).to(rabbitMqTopicExchange).with(RoutingKeyEnumeration.TOPIC_KEY.getCode());
        amqpAdmin.declareBinding(binding);
        log.debug("topic queue binding created successfully!");
        return binding;
    }

    @Bean
    protected Binding bindingFanoutQueue1(Queue rabbitMqFanoutQueue1, FanoutExchange rabbitMqFanoutExchange) {
        Binding binding = BindingBuilder.bind(rabbitMqFanoutQueue1).to(rabbitMqFanoutExchange);
        amqpAdmin.declareBinding(binding);
        log.debug("fanout queue 1 binding created successfully!");
        return binding;
    }

    @Bean
    protected Binding bindingFanoutQueue2(Queue rabbitMqFanoutQueue2, FanoutExchange rabbitMqFanoutExchange) {
        Binding binding = BindingBuilder.bind(rabbitMqFanoutQueue2).to(rabbitMqFanoutExchange);
        amqpAdmin.declareBinding(binding);
        log.debug("fanout queue 2 binding created successfully!");
        return binding;
    }
}
