package com.gxf.demo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author GXF
 */
@Slf4j
public enum RabbitMqQueueEnum {
    /**
     * 直连类型的交换
     */
    DIRECT_QUEUE("DIRECT_QUEUE"),

    /**
     * 主题类型的交换机
     */
    TOPIC_QUEUE("TOPIC_QUEUE"),

    /**
     * 广播类型的交换机 1号
     */
    FANOUT_QUEUE_ONE("FANOUT_QUEUE_1"),

    /**
     * 广播类型的交换机 2号
     */
    FANOUT_QUEUE_TWO("FANOUT_QUEUE_2"),
    ;

    private final String queueName;

    RabbitMqQueueEnum(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

}
