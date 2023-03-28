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
 * @author GXF
 */

@Component
@Slf4j
@RabbitListener(queues = "DIRECT_QUEUE")
public class DirectQueueListener {

    @RabbitHandler
    public void handleMessage(@Payload byte[] body, Channel channel, Message message) throws IOException {
        log.info("body = {} , \n message = {}", new String(body), message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitHandler
    public void handleMessage(@Payload String body, Channel channel, Message message) throws IOException {
        log.info("body = {} , \n message = {}", body, message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
