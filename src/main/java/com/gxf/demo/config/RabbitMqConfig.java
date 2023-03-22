package com.gxf.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GXF
 */
@Configuration
@EnableRabbit
@Slf4j
public class RabbitMqConfig {

    @Bean
    public AmqpTemplate amqpTemplate(@Autowired RabbitTemplate rabbitTemplate) {
        //使用jackson 消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        // 开启returnCallback publisher-returns: true
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            String msg = returnedMessage.getMessage().toString();
            int replyCode = returnedMessage.getReplyCode();
            String replyText = returnedMessage.getReplyText();
            String exchange = returnedMessage.getExchange();
            String routingKey = returnedMessage.getRoutingKey();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", msg, replyCode, replyText, exchange, routingKey);
        });
        //开启消息确认 publisher-returns: true
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if (ack) {
                log.debug("消息发送到交换机成功,correlation:{}", correlationData);
            } else {
                log.info("消息发送到交换机失败,原因:{}", cause);
            }
        }));
        return rabbitTemplate;
    }

    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange", true, false);
    }

    @Bean
    public Binding bindingDirect(@Autowired Queue directQueue, @Autowired DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("testDirectRouting");
    }

}
