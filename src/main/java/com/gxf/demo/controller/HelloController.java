package com.gxf.demo.controller;

import com.gxf.demo.enumeration.RabbitMqExchangeEnumeration;
import com.gxf.demo.enumeration.RoutingKeyEnumeration;
import com.gxf.demo.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GXF
 */
@RestController
public class HelloController {

    @Autowired
    private MessageQueueService messageQueueService;

    @GetMapping
    public String helloWorld() {
        return "Hello Word!";
    }

    @GetMapping("/sendMsg")
    public String testMessageQueue() {
        messageQueueService.sendMessageWithRoutingKeyAndExchangeKey(RabbitMqExchangeEnumeration.DIRECT_EXCHANGE, RoutingKeyEnumeration.DIRECT_KEY, "Hello Word!");
        return "OK";
    }

    @GetMapping("/sendMsgDirect")
    public String testConvertAndSendDirectMessageQueue() {
        messageQueueService.sendDirectMessage("Hello Word! 666 ");
        return "OK";
    }
}
