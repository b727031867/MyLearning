package com.gxf.demo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * MQ的路由KEY枚举类
 *
 * @author GXF
 */
@Slf4j
public enum RoutingKeyEnumeration {
    /**
     * 直连交换机的路由测试key
     */
    DIRECT_KEY("DIRECT_ROUTE_KEY", "test"),

    /**
     * 主题交换机的路由测试key
     */
    TOPIC_KEY("TOPIC_KEY", "test");

    private final String code;
    private final String name;

    RoutingKeyEnumeration(String code, String name) {
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
