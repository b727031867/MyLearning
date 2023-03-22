package com.gxf.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author GXF
 */
@RabbitListener(queues = "directQueue")
@Service
@Slf4j
public class DirectQueueListener {
    @RabbitHandler
    public void handleTheMessageOfDirectQueue(Message message) {
        log.info("message = {}", message);
    }
}
