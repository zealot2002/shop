package com.zzy.shop.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.zzy.shop.utils.DateUtils;
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
import com.zzy.shop.service.AddressService;
import com.zzy.shop.service.OrderService;
import com.zzy.shop.service.UserService;
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
                bean.setExpiredTime(req.getExpiredTime());
                userService.save(bean);
            }
            return ResultGenerator.genSuccessResult("修改成功");
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

    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result register(@RequestBody UserReq req) {
        try {
            User user = userService.findByUsername(req.getUsername());
            if (user != null) {
                return ResultGenerator.genFailResult("名为‘" + req.getUsername() + "’的用户已经存在");
            }

            User newUser = new User();
            newUser.setUsername(req.getUsername());
            newUser.setPassword(req.getPassword());
            newUser.setDeviceId(req.getDeviceId());
            newUser.setCreatedTime(new Date());
            newUser.setExpiredTime(getTheDayAfterTomorrow());

            userService.save(newUser);

            System.out.println("新用户注册 "+user.getUsername()+" 有效期至:  -------"+user.getExpiredTime());

            return ResultGenerator.genSuccessResult(newUser.getExpiredTime());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    private String getTheDayAfterTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(calendar.getTime());
        return dateStr;
    }

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestBody UserReq req) {
        try {
            System.out.println("req.getUsername():  -------"+req.getUsername());
            User user = userService.findByUsername(req.getUsername());
            if (user == null) {
                return ResultGenerator.genFailResult("名为‘" + req.getUsername() + "’的用户不存在");
            }

            if (!user.getPassword().equals(req.getPassword())) {
                return ResultGenerator.genFailResult("密码错误");
            }
            if (user.getDeviceId() != null && !user.getDeviceId().equals(req.getDeviceId())) {
                //有设备号，并且不相等
                return ResultGenerator.genFailResult("请使用注册时绑定的设备 (一个账号绑定一个设备)");
            }
            if (DateUtils.string2Date(user.getExpiredTime()).compareTo(new Date()) < 0){
                //过期了
                return ResultGenerator.genFailResult("当前账号已过期，("+user.getExpiredTime().toString()+")");
            }
            System.out.println("用户 "+user.getUsername()+" 已登录 "+"有效期至:  -------"+user.getExpiredTime());

            return ResultGenerator.genSuccessResult(user.getExpiredTime());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping(path = "/resetPw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result resetPw(@RequestBody UserReq req) {
        try {
            System.out.println("req.getUsername():  -------"+req.getUsername());
            User user = userService.findByUsername(req.getUsername());
            if (user == null) {
                return ResultGenerator.genFailResult("名为‘" + req.getUsername() + "’的用户不存在");
            }

            if (user.getDeviceId() != null && !user.getDeviceId().equals(req.getDeviceId())) {
                //有设备号，并且不相等
                return ResultGenerator.genFailResult("请使用注册时绑定的设备 (一个账号绑定一个设备)");
            }

            if (DateUtils.string2Date(user.getExpiredTime()).compareTo(new Date()) < 0){
                //过期了
                return ResultGenerator.genFailResult("当前账号已过期，("+user.getExpiredTime().toString()+")");
            }

            user.setPassword(req.getPassword());
            userService.save(user);
            return ResultGenerator.genSuccessResult("重置密码成功");
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
