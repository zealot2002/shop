package com.zzy.shop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.bean.User;
import com.zzy.shop.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService service;
	
	@ApiOperation(value = "删除用户" ,  notes="新增注册")
    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
    	service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "修改用户" ,  notes="修改用户")
    @RequestMapping(value="/updateUser",method=RequestMethod.POST,
    							consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody User bean) {
    	service.save(bean);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "查询用户" ,  notes="查询用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户标识", required = true, 
        		paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value="/queryUser",method=RequestMethod.POST)
    public Result detail(@RequestParam Long id) {
    	User bean = service.findById(id);
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
