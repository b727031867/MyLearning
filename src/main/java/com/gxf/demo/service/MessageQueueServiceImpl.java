package com.gxf.demo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author GXF
 */
@Service
public class MessageQueueServiceImpl implements MessageQueueService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String message) {
        String test = "test_massage";
        amqpTemplate.send(new Message(test.getBytes(StandardCharsets.UTF_8)));
    }

    public void sendMessageWithRoutingKey(String routingKey, String message) {
        String test = "test_massage";
        amqpTemplate.send(routingKey, new Message(test.getBytes(StandardCharsets.UTF_8)));
    }
}
