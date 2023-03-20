package com.gxf.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GXF
 */
@RestController
public class HelloController {

    @GetMapping
    public String helloWorld() {
        return "Hello Word!";
    }
}
