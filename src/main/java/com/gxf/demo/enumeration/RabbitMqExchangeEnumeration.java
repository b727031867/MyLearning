package com.gxf.demo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义交换机类型的枚举类
 * @author GXF
 */
@Slf4j
public enum RabbitMqExchangeEnumeration {
    /**
     * 直连类型的交换
     */
    DIRECT_EXCHANGE("DIRECT_EXCHANGE", "直连模式"),

    /**
     * 主题类型的交换机
     */
    TOPIC_EXCHANGE("TOPIC_EXCHANGE", "主题模式"),

    /**
     * 广播类型的交换机
     */
    FANOUT_EXCHANGE("FANOUT_EXCHANGE", "广播模式"),
    ;

    private final String code;
    private final String name;

    RabbitMqExchangeEnumeration(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
