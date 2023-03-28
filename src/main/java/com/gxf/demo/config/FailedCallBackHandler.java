package com.gxf.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author GXF
 */
@Slf4j
@Component
public class FailedCallBackHandler implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        String msg = returned.getMessage().toString();
        int replyCode = returned.getReplyCode();
        String replyText = returned.getReplyText();
        String exchange = returned.getExchange();
        String routingKey = returned.getRoutingKey();
        log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", msg, replyCode, replyText, exchange, routingKey);

    }
}
