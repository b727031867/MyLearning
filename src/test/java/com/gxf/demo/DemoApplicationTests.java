package com.gxf.demo;

import com.gxf.demo.controller.HelloController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Test
    void contextLoads() {
        HelloController helloController = new HelloController();
        String testValue = helloController.helloWorld();
        Assertions.assertNotNull(testValue);
    }

}
