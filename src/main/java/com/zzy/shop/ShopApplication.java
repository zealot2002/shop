package com.zzy.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.zzy.shop.dao",
		"com.zzy.shop.service.impl",
		"com.zzy.shop.controller",
		"com.zzy.shop.appcontroller",		
		"com.zzy.shop.config"})
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
