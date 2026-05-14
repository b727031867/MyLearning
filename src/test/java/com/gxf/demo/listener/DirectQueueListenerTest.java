package com.gxf.demo.listener;

import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

class DirectQueueListenerTest {

    @Test
    void handleMessageWithStringBody() throws IOException {
        DirectQueueListener listener = new DirectQueueListener();
        Channel channel = mock(Channel.class);

        MessageProperties props = new MessageProperties();
        props.setDeliveryTag(1L);
        Message message = new Message("test message".getBytes(), props);

        listener.handleMessage("test message", channel, message);

        verify(channel, times(1)).basicAck(1L, false);
    }

    @Test
    void handleMessageWithByteArrayBody() throws IOException {
        DirectQueueListener listener = new DirectQueueListener();
        Channel channel = mock(Channel.class);

        MessageProperties props = new MessageProperties();
        props.setDeliveryTag(2L);
        byte[] body = "byte array message".getBytes();
        Message message = new Message(body, props);

        listener.handleMessage(body, channel, message);

        verify(channel, times(1)).basicAck(2L, false);
    }
}