package com.gxf.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @Test
    void helloWorld() {
        String s = helloController.helloWorld();
        Assertions.assertEquals("Hello Word!", s);
    }

    @Test
    void testMessageQueue() {
        String s = helloController.testMessageQueue();
        Assertions.assertEquals("OK", s);
    }

    @Test
    void testConvertAndSendDirectMessageQueue() {
        String s = helloController.testConvertAndSendDirectMessageQueue();
        Assertions.assertEquals("OK", s);
    }
}