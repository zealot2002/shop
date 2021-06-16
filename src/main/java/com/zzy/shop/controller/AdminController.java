package com.zzy.shop.controller;

import com.zzy.shop.bean.Admin;
import com.zzy.shop.bean.req.AdminReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.zzy.shop.core.ServiceException;
import com.zzy.shop.service.AdminService;
import com.zzy.shop.utils.PageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idQuery) {
        try {
            adminService.deleteById(idQuery.getId());
            return ResultGenerator.genSuccessResult();
        } catch (EmptyResultDataAccessException e1) {
            e1.printStackTrace();
            return ResultGenerator.genFailResult("id为‘" + idQuery.getId() + "’的记录不存在!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result add(@RequestBody AdminReq req) {
        try {
            checkValid(req, CommonConstants.ACTION_ADD);
            Admin bean = new Admin();
            bean.setUsername(req.getUsername());
            bean.setPassword(req.getPassword());
            adminService.save(bean);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody AdminReq req) {
        try {
            checkValid(req, CommonConstants.ACTION_UPDATE);
            Admin bean = adminService.findById(req.getId());
            if (bean == null) {
                return ResultGenerator.genFailResult("id为‘" + req.getId() + "’的记录不存在!");
            }
            bean.setUsername(req.getUsername());
            bean.setPassword(req.getPassword());
            adminService.save(bean);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "login", notes = "login")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestBody AdminReq req) {
        try {
            Admin bean = adminService.findByUsername(req.getUsername());
            if (bean != null && bean.getPassword().equals(req.getPassword())) {
                return ResultGenerator.genSuccessResult(bean);
            }
            return ResultGenerator.genFailResult("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @PostMapping(path = "/queryById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result queryById(@RequestBody IdReq idReq) {
        try {
            Admin bean = adminService.findById(idReq.getId());
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
            List<Admin> list = adminService.findAll();
            List<Admin> targetList = new PageUtil<Admin>().getList(list,
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
            List<Admin> list = adminService.findAll();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
    }

    private void checkValid(AdminReq req, int action) throws Exception {
        Admin admin = adminService.findByUsername(req.getUsername());
        if (admin != null)
            throw new ServiceException("username 已经存在! username:" + req.getUsername());
    }
}
