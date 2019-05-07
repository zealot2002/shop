package com.zzy.shop.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.bean.User;
import com.zzy.shop.common.IdQuery;
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
	
	@ApiOperation(value = "删除用户" ,  notes="删除用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "用户标识", required = true, 
        		paramType = "delete", dataType = "Integer")
    })
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Result delete(@RequestParam Long id) {
    	service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

//	@ApiOperation(value="更新用户名称", notes="更新指定用户的名称")
//	@RequestMapping(value="/users/{id}", method= RequestMethod.POST)
//	@ApiImplicitParams({  （1）
//	        @ApiImplicitParam(name = "id",value = "用户ID",paramType = "path",dataType = "int"),  (2)
//	        @ApiImplicitParam(name = "userName",value = "用户名称",paramType = "form",dataType = "string")
//	})
//	public void updateUserName(@PathVariable Integer id,@RequestParam String userName){
//	    User u = users.get(id);
//	    u.setName(userName);
//	}
	
	@ApiOperation(value="新增或修改用户", notes="新增或修改用户")
	@PostMapping(path = "/addOrUpdate",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result addOrUpdate(@RequestBody User _user) {
    	User user = new User();
    	user.setCreatedTime(new Date());
    	user.setUsername(_user.getUsername());
    	user.setPhone(_user.getPhone());
		service.save(user);
        return ResultGenerator.genSuccessResult();
    }
	
//	@ApiOperation(value = "修改用户" ,  notes="修改用户")
//    @RequestMapping(value="/updateUser",method=RequestMethod.POST,
//    							consumes= MediaType.APPLICATION_JSON_VALUE)
//    public Result update(@RequestBody User bean) {
//    	service.save(bean);
//        return ResultGenerator.genSuccessResult();
//    }

	@ApiOperation(value="查询用户", notes="查询用户")
	@PostMapping(path = "/query",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result query(@RequestBody IdQuery idQuery) {
    	User bean = service.findById(idQuery.getId());
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
