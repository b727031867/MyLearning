//package com.gxf.demo.listener;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.impl.recovery.AutorecoveringChannel;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.core.Message;
//
//import java.nio.charset.StandardCharsets;
//
//class DirectQueueListenerTest {
//    @Test
//    void handleTheMessageOfDirectQueue() {
//        String test = "666";
//        DirectQueueListener listener = new DirectQueueListener();
////        listener.handleTheMessageOfDirectQueue(new Message(test.getBytes(StandardCharsets.UTF_8)));
//    }
//}