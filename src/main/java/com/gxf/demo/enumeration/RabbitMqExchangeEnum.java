package com.gxf.demo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义交换机类型的枚举类
 * @author GXF
 */
@Slf4j
public enum RabbitMqExchangeEnum {
    /**
     * 直连类型的交换
     */
    DIRECT_EXCHANGE("DIRECT_EXCHANGE"),

    /**
     * 主题类型的交换机
     */
    TOPIC_EXCHANGE("TOPIC_EXCHANGE"),

    /**
     * 广播类型的交换机
     */
    FANOUT_EXCHANGE("FANOUT_EXCHANGE"),
    ;

    private final String code;

    RabbitMqExchangeEnum(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
