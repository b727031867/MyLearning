package com.gxf.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author GXF
 */
@Slf4j
@Component
public class ConfirmCallbackHandler implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.debug("消息发送到 broker服务 成功,correlation:{}", correlationData);
        } else {
            log.info("消息发送到 broker服务 失败,原因:{}", cause);
        }
    }
}
