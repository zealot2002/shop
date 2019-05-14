package com.zzy.shop.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.bean.Address;
import com.zzy.shop.bean.req.AddressReq;
import com.zzy.shop.bean.req.CategoryReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.OrderReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.service.AddressService;
import com.zzy.shop.service.UserService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
    private AddressService addressService;
	@Autowired
    private UserService userService;
	
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idReq) {
		try {
			addressService.deleteById(idReq.getId());
			return ResultGenerator.genSuccessResult();
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idReq.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="新增", notes="新增")
	@PostMapping(path = "/add",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result add(@RequestBody AddressReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_ADD);
			Address bean = new Address();
			bean.setUserId(req.getUserId());
			bean.setDescription(req.getDescription());
			bean.setPhone(req.getPhone());
			addressService.save(bean);
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }

	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody AddressReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_UPDATE);
			Address bean = addressService.findById(req.getId());
			bean.setDescription(req.getDescription());
			bean.setPhone(req.getPhone());
			addressService.save(bean);
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="通过id查询", notes="通过id查询")
	@PostMapping(path = "/queryById",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryById(@RequestBody IdReq idReq) {
		try {
			Address bean = addressService.findById(idReq.getId());
			return ResultGenerator.genSuccessResult(bean);
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idReq.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="通过userId查询", notes="通过userId查询")
	@PostMapping(path = "/queryByUserId",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryByUserId(@RequestBody IdReq idReq) {
		try {
			if(!userService.existsById(idReq.getId()))
				throw new Exception("user id不存在! id:"+idReq.getId());
			List<Address> list = addressService.findAllByUserId(idReq.getId());
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="分页查询", notes="分页查询")
	@PostMapping(path = "/queryPage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryPage(@RequestBody PageReq pageReq) {
		try {
			List<Address> list = addressService.findAll();
			List<Address> targetList = new PageUtil<Address>().getList(list,
					pageReq.getPageNum(),pageReq.getPageSize());
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
			List<Address> list = addressService.findAll();
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	private void checkValid(AddressReq req, int action) throws Exception{
		if(CommonConstants.ACTION_UPDATE == action) {
			if(!addressService.existsById(req.getId()))
				throw new Exception("id为‘"+req.getId()+"’的记录不存在!");
		}
		if(!userService.existsById(req.getUserId()))
			throw new Exception("user id不存在! id:"+req.getUserId());
	}
}
