package com.zzy.shop.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
    @RequestMapping("/say/{name}")
    String sayHello(@PathVariable String name) {
        return "Hello," + name + "!";
    }
    
    @RequestMapping("/test")
    String test() {
        return "Hello!";
    }
}