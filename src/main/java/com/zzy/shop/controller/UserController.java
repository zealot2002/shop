package com.zzy.shop.controller;

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
import com.alibaba.fastjson.JSONObject;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.service.AddressService;
import com.zzy.shop.service.OrderService;
import com.zzy.shop.service.UserService;
import com.zzy.shop.bean.Address;
import com.zzy.shop.bean.Order;
import com.zzy.shop.bean.User;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.bean.req.UserReq;
import com.zzy.shop.constants.CommonConstants;
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

    @Autowired
    private AddressService addressService;

    @Transactional
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idReq) {
        try {
            orderService.deleteAllByUserId(idReq.getId());
            addressService.deleteAllByUserId(idReq.getId());
            userService.deleteById(idReq.getId());
            return ResultGenerator.genSuccessResult();
        } catch (EmptyResultDataAccessException e1) {
            e1.printStackTrace();
            return ResultGenerator.genFailResult("id为‘" + idReq.getId() + "’的记录不存在!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result add(@RequestBody UserReq req) {
        try {
            checkValid(req, CommonConstants.ACTION_ADD);
            User bean = new User();
            bean.setUsername(req.getUsername());
            bean.setPassword(req.getPassword());
            bean.setPhone(req.getPhone());
            bean.setAvatar(req.getAvatar());
            bean.setDeviceId(req.getDeviceId());
            bean.setExpiredTime(req.getExpiredTime());
            userService.save(bean);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody UserReq req) {
        try {
            checkValid(req, CommonConstants.ACTION_UPDATE);
            User bean = userService.findById(req.getId());
            if (bean == null) {
                return ResultGenerator.genFailResult("id为‘" + req.getId() + "’的记录不存在!");
            } else {
                bean.setUsername(req.getUsername());
                bean.setPassword(req.getPassword());
                bean.setPhone(req.getPhone());
                bean.setAvatar(req.getAvatar());
                bean.setDeviceId(req.getDeviceId());
                bean.setExpiredTime(req.getExpiredTime());
                userService.save(bean);
            }
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @PostMapping(path = "/queryById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result queryById(@RequestBody IdReq idReq) {
        try {
            User bean = userService.findById(idReq.getId());
            return ResultGenerator.genSuccessResult(bean);
        } catch (EmptyResultDataAccessException e1) {
            e1.printStackTrace();
            return ResultGenerator.genFailResult("id为‘" + idReq.getId() + "’的记录不存在!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping(path = "/queryPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result queryPage(@RequestBody PageReq pageReq) {
        try {
            List<User> list = userService.findAll();
            List<User> targetList = new PageUtil<User>().getList(list,
                    pageReq.getPageNum(), pageReq.getPageSize());
            return ResultGenerator.genSuccessResult(targetList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "查询所有", notes = "查询所有")
    @PostMapping(path = "/queryAll", consumes = MediaType.ALL_VALUE)
    public Result queryAll() {
        try {
            List<User> list = userService.findAll();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "校验", notes = "校验")
    @PostMapping(path = "/validation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result validation(UserReq req) {
        try {
            User user = userService.findByUsername(req.getUsername());
            if (user == null) {
                return ResultGenerator.genFailResult("名为‘" + req.getUsername() + "’的用户不存在");
            }

            if (!user.getPassword().equals(req.getPassword())) {
                return ResultGenerator.genFailResult("密码错误");
            }
            if (user.getDeviceId() != null && !user.getDeviceId().equals(req.getDeviceId())) {
                //有设备号，并且不相等
                return ResultGenerator.genFailResult("请使用同一设备登录 (一个账号绑定一个设备)");
            }
            if (user.getExpiredTime().compareTo(new Date()) < 0){
                //过期了
                return ResultGenerator.genFailResult("当前账号已过期，("+user.getExpiredTime().toString()+")");
            }
            if(user.getDeviceId() == null){
                //这是一个新用户、更新它的deviceId
                user.setDeviceId(req.getDeviceId());
                userService.save(user);
            }

            return ResultGenerator.genSuccessResult(user.getExpiredTime());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    private void checkValid(UserReq req, int action) throws Exception {
        if (CommonConstants.ACTION_UPDATE == action) {
            if (!userService.existsById(req.getId()))
                throw new Exception("id为‘" + req.getId() + "’的记录不存在!");
        }
    }
}
