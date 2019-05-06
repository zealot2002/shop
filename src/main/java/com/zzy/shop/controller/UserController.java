package com.zzy.shop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.model.User;
import com.zzy.shop.service.UserService;

import java.util.Optional;

/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService service;

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
    	service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(User bean) {
    	service.save(bean);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
    	Optional<User> bean = service.findById(id);
        return ResultGenerator.genSuccessResult(bean);
    }

//    @PostMapping("/list")
//    public Result list(@RequestParam(defaultValue = "0") Integer page, 
//    		@RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<User> list = service.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
}
