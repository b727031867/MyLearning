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
 * Topic 交换机消息监听器
 * 监听 TOPIC_QUEUE，支持通配符路由键匹配（如 *.info, log.#）
 */
@Component
@Slf4j
@RabbitListener(queues = "TOPIC_QUEUE")
public class TopicQueueListener {

    @RabbitHandler
    public void handleMessage(@Payload String body, Channel channel, Message message) throws IOException {
        log.info("[TopicListener] 收到消息: {}, 路由键: {}", body, message.getMessageProperties().getReceivedRoutingKey());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}