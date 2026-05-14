package com.gxf.demo.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Fanout 交换机消息监听器 - 队列1
 * 监听 FANOUT_QUEUE_1，接收广播消息
 */
@Component
@Slf4j
@RabbitListener(queues = "FANOUT_QUEUE_1")
public class FanoutQueueListener {

    @RabbitHandler
    public void handleMessage(@Payload String body, Channel channel, Message message) throws IOException {
        log.info("[FanoutListener-1] 收到广播消息: {}", body);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}