package com.gxf.demo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * MQ的路由KEY枚举类
 *
 * @author GXF
 */
@Slf4j
public enum RoutingKeyEnum {
    /**
     * 直连交换机的路由测试key
     */
    DIRECT_KEY("DIRECT_ROUTE_KEY"),

    /**
     * 主题交换机的路由测试key
     */
    TOPIC_KEY("TOPIC_KEY");

    private final String code;

    RoutingKeyEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
