package com.gxf.demo.service;

import com.gxf.demo.enumeration.RabbitMqExchangeEnum;
import com.gxf.demo.enumeration.RoutingKeyEnum;

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
    void sendMessageWithRoutingKeyAndExchangeKey(RabbitMqExchangeEnum exchangeKey, RoutingKeyEnum routingKey, String message);

    /**
     * 发送消息到 Topic 交换机
     *
     * @param routingKey 路由键，支持通配符（如 *.info, log.#）
     * @param message 发送的消息内容
     */
    void sendTopicMessage(String routingKey, String message);

    /**
     * 发送消息到 Fanout 交换机（广播模式，忽略路由键）
     *
     * @param message 发送的消息内容
     */
    void sendFanoutMessage(String message);

    /**
     * 发送对象消息到指定交换机和路由键
     *
     * @param exchange 交换机
     * @param routingKey 路由键
     * @param object 发送的对象（需支持 JSON 序列化）
     */
    void sendObjectMessage(RabbitMqExchangeEnum exchange, RoutingKeyEnum routingKey, Object object);
}
