package com.zzy.shop.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    @RequestMapping("/say/{name}")
    String hhh(@PathVariable String name) {
        return "Hello," + name + "!";
    }
    
    @RequestMapping("/kkk")
    String sayKkk() {
        return "Hello!";
    }
}