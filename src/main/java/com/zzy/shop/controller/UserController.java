package com.zzy.shop.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.service.OrderService;
import com.zzy.shop.service.UserService;
import com.zzy.shop.bean.Order;
import com.zzy.shop.bean.User;
import com.zzy.shop.common.bean.IdQuery;
import com.zzy.shop.common.bean.PageQuery;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService userService;
	
	@Autowired
    private OrderService orderService;
	
	@Transactional
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdQuery idQuery) {
		try {
			orderService.deleteAllByUserId(idQuery.getId());
			userService.deleteById(idQuery.getId());
			return ResultGenerator.genSuccessResult();
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idQuery.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="新增", notes="新增")
	@PostMapping(path = "/add",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result add(@RequestBody User model) {
		try{
			model.setCreatedTime(new Date());
			userService.save(model);
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody User model) {
		try{
			User bean = userService.findById(model.getId());
			if(bean == null) {
				return ResultGenerator.genFailResult("id为‘"+model.getId()+"’的记录不存在!");
			}else {
				bean.replace(model);
				userService.save(bean);
			}
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@ApiOperation(value="通过id查询", notes="通过id查询")
	@PostMapping(path = "/queryById",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryById(@RequestBody IdQuery idQuery) {
		try {
			User bean = userService.findById(idQuery.getId());
			setOrderList(bean);
			return ResultGenerator.genSuccessResult(bean);
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idQuery.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@ApiOperation(value="分页查询", notes="分页查询")
	@PostMapping(path = "/queryPage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryPage(@RequestBody PageQuery pageQuery) {
		try {
			List<User> list = userService.findAll();
			List<User> targetList = new PageUtil<User>().getList(list,
					pageQuery.getPageNum(),pageQuery.getPageSize());
			setOrderList(targetList);
			return ResultGenerator.genSuccessResult(targetList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@ApiOperation(value="查询所有", notes="查询所有")
	@PostMapping(path = "/queryAll",consumes= MediaType.ALL_VALUE)
    public Result queryAll() {
		try {
			List<User> list = userService.findAll();
			setOrderList(list);
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	private void setOrderList(List<User> list) {
		for(User user:list) {
			List<Order> orders = orderService.findAllByUserId(user.getId());
			user.setOrders(orders);
		}
	}
	private void setOrderList(User user) {
		List<Order> orders = orderService.findAllByUserId(user.getId());
		user.setOrders(orders);
	}
}
