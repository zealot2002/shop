package com.zzy.shop.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/html")
public class HtmlController {


    @RequestMapping("/ftlIndex")
    public String ftlIndex() {
        System.out.println("fff");
        return "index";
    }



}
