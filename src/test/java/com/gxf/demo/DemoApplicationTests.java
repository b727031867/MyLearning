package com.gxf.demo;

import com.gxf.demo.controller.HelloController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        HelloController helloController = new HelloController();
        String testValue = helloController.helloWorld();
        Assertions.assertNotNull(testValue);
    }

}
