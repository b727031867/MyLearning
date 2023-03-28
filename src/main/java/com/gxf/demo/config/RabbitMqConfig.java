package com.gxf.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @author GXF
 */
@Configuration
@EnableRabbit
@Slf4j
public class RabbitMqConfig {

    /**
     * RabbitMQ监听器的工厂bean
     *
     * @param rabbitConnectionFactory mq的连接工厂
     * @return mq监听器工厂
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        //设置并发
        factory.setConcurrentConsumers(1);
        //最大并发
        factory.setMaxConcurrentConsumers(1);
        //消息接收需手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置超时
        factory.setReceiveTimeout(2000L);
        //设置重试间隔
        factory.setFailedDeclarationRetryInterval(3000L);

        return factory;
    }

    /**
     * mq的模板操作类
     *
     * @param rabbitTemplate mq的默认操作类
     * @return 配置后mq的操作类
     */
    @Bean
    public AmqpTemplate amqpTemplate(@Autowired RabbitTemplate rabbitTemplate, @Autowired ConfirmCallbackHandler confirmCallbackHandler,
                                     @Autowired FailedCallBackHandler failedCallBackHandler) {
        //使用jackson 消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding(StandardCharsets.UTF_8.name());
        // 需手动ack
        rabbitTemplate.setMandatory(true);
        // 消息发送失败后回调 需开启配置 publisher-returns: true
        rabbitTemplate.setReturnsCallback(failedCallBackHandler);
        //消息发送到交换机（broker）后回调 需开启配置 publisher-returns: true
        rabbitTemplate.setConfirmCallback(confirmCallbackHandler);
        return rabbitTemplate;
    }

}
