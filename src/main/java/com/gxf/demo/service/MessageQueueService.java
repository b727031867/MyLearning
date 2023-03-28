package com.gxf.demo.service;

import com.gxf.demo.enumeration.RabbitMqExchangeEnumeration;
import com.gxf.demo.enumeration.RoutingKeyEnumeration;

/**
 * @author GXF
 */
public interface MessageQueueService {

    /**
     * 发送MQ文本消息 到直接交换机中
     *
     * @param message 发送的文本消息内容
     */
    void sendDirectMessage(String message);

    /**
     * 发送MQ文本消息，指定交换机的key，并且根据key过滤消费
     *
     * @param exchangeKey  交换机的key
     * @param routingKey     发到交换机路由的key
     * @param message 发送的文本消息内容
     */
    void sendMessageWithRoutingKeyAndExchangeKey(RabbitMqExchangeEnumeration exchangeKey, RoutingKeyEnumeration routingKey, String message);
}
