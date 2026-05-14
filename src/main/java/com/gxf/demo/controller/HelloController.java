package com.gxf.demo.controller;

import com.gxf.demo.entity.UserMessage;
import com.gxf.demo.enumeration.RabbitMqExchangeEnum;
import com.gxf.demo.enumeration.RoutingKeyEnum;
import com.gxf.demo.service.MessageQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author GXF
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private MessageQueueService messageQueueService;

    @GetMapping
    public String helloWorld() {
        return "Hello Word!";
    }

    @GetMapping("/testLogstash")
    public String testLogstash() {
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        return "Hello World!";
    }

    @GetMapping("/sendMsg")
    public String testMessageQueue() {
        messageQueueService.sendMessageWithRoutingKeyAndExchangeKey(RabbitMqExchangeEnum.DIRECT_EXCHANGE, RoutingKeyEnum.DIRECT_KEY, "Hello Word!");
        return "OK";
    }

    @GetMapping("/sendMsgDirect")
    public String testConvertAndSendDirectMessageQueue() {
        messageQueueService.sendDirectMessage("Hello Word! 666 ");
        return "OK";
    }

    @GetMapping("/sendTopicMsg")
    public String testTopicMessage(@RequestParam(defaultValue = "topic.info") String routingKey) {
        messageQueueService.sendTopicMessage(routingKey, "Topic消息测试");
        return "OK - routingKey: " + routingKey;
    }

    @GetMapping("/sendFanoutMsg")
    public String testFanoutMessage() {
        messageQueueService.sendFanoutMessage("Fanout广播消息测试");
        return "OK";
    }

    @GetMapping("/sendObjectMsg")
    public String testObjectMessage() {
        UserMessage userMessage = new UserMessage(1L, "张三", "测试内容", new Date());
        messageQueueService.sendObjectMessage(RabbitMqExchangeEnum.DIRECT_EXCHANGE, RoutingKeyEnum.DIRECT_KEY, userMessage);
        return "OK - 对象消息已发送";
    }
}
