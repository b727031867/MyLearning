package com.gxf.demo.service;

import com.gxf.demo.enumeration.RabbitMqExchangeEnumeration;
import com.gxf.demo.enumeration.RoutingKeyEnumeration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author GXF
 */
@Service
public class MessageQueueServiceImpl implements MessageQueueService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public MessageQueueServiceImpl(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void sendDirectMessage(String message) {
        amqpTemplate.convertAndSend(RabbitMqExchangeEnumeration.DIRECT_EXCHANGE.getCode(), RoutingKeyEnumeration.DIRECT_KEY.getCode(), message);
    }

    @Override
    public void sendMessageWithRoutingKeyAndExchangeKey(RabbitMqExchangeEnumeration exchangeKey, RoutingKeyEnumeration routingKey, String message) {
        amqpTemplate.send(exchangeKey.getCode(), routingKey.getCode(), new Message(message.getBytes(StandardCharsets.UTF_8)));
    }
}
