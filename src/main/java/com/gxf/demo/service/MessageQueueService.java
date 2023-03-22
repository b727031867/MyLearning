package com.gxf.demo.service;

/**
 * @author GXF
 */
public interface MessageQueueService {

    /**
     * 发送MQ文本消息
     *
     * @param message 发送的文本消息内容
     */
    void sendMessage(String message);
}
