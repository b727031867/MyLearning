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
    DIRECT_QUEUE("DIRECT_QUEUE", "直连模式"),

    /**
     * 主题类型的交换机
     */
    TOPIC_QUEUE("TOPIC_QUEUE", "主题模式"),

    /**
     * 广播类型的交换机 1号
     */
    FANOUT_QUEUE_ONE("FANOUT_QUEUE_1", "广播模式"),

    /**
     * 广播类型的交换机 2号
     */
    FANOUT_QUEUE_TWO("FANOUT_QUEUE_2", "广播模式"),
    ;

    private final String queueName;
    private final String name;

    RabbitMqQueueEnum(String queueName, String name) {
        this.queueName = queueName;
        this.name = name;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getName() {
        return name;
    }
}
