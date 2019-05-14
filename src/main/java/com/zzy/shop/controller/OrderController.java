package com.zzy.shop.controller;
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
import com.zzy.shop.bean.Goods;
import com.zzy.shop.bean.GoodsImageRel;
import com.zzy.shop.bean.Image;
import com.zzy.shop.bean.Order;
import com.zzy.shop.bean.OrderGoodsRel;
import com.zzy.shop.bean.req.GoodsReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.OrderReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.service.GoodsService;
import com.zzy.shop.service.OrderGoodsRelService;
import com.zzy.shop.service.OrderService;
import com.zzy.shop.service.UserService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
    private OrderService orderService;
	@Autowired
    private GoodsService goodsService;
	@Autowired
    private UserService userService;
	@Autowired
    private OrderGoodsRelService orderGoodsRelService;
	
	
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idReq) {
		try {
			orderService.deleteById(idReq.getId());
			return ResultGenerator.genSuccessResult();
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idReq.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@Transactional
	@ApiOperation(value="新增", notes="新增")
	@PostMapping(path = "/add",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result add(@RequestBody OrderReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_ADD);
			Order bean = new Order();
			bean.setRemarks(req.getRemarks());
			bean.setUserId(req.getUserId());
			bean.setPhone(req.getPhone());
			bean.setAddress(req.getAddress());
			bean = orderService.saveAndFlush(bean);
			
			/*handle goodsIdList*/
			if(req.getGoodsIdList()!=null) {
				for(Long id:req.getGoodsIdList()) {
					OrderGoodsRel rel = new OrderGoodsRel();
					rel.setGoodsId(id);
					rel.setOrderId(bean.getId());
					orderGoodsRelService.save(rel);
				}
			}
			
			orderService.save(bean);
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }

	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody OrderReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_UPDATE);
			Order bean = orderService.findById(req.getId());
			bean.setState(req.getState());
			bean.setRemarks(req.getRemarks());
			bean.setPhone(req.getPhone());
			bean.setAddress(req.getAddress());
			
			/*handle goodsIdList*/
			if(req.getGoodsIdList()!=null) {
				orderGoodsRelService.deleteByOrderId(bean.getId());
				for(Long id:req.getGoodsIdList()) {
					OrderGoodsRel rel = new OrderGoodsRel();
					rel.setGoodsId(id);
					rel.setOrderId(bean.getId());
					orderGoodsRelService.save(rel);
				}
			}
			
			orderService.save(bean);
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
			Order bean = orderService.findById(idReq.getId());
			List<Goods> goodList = goodsService.findAllByOrderId(idReq.getId());
			Float totalPrice = 0f;
			for(Goods goods:goodList) {
				totalPrice+=goods.getPrice();
			}
			bean.setTotalPrice(totalPrice);
			bean.setGoodsList(goodList);
			return ResultGenerator.genSuccessResult(bean);
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idReq.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@ApiOperation(value="分页查询", notes="分页查询")
	@PostMapping(path = "/queryPage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryPage(@RequestBody PageReq pageReq) {
		try {
			List<Order> list = orderService.findAll();
			List<Order> targetList = new PageUtil<Order>().getList(list,
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
			List<Order> list = orderService.findAll();
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	private void checkValid(OrderReq req, int action) throws Exception{
		if(CommonConstants.ACTION_UPDATE == action) {
			if(!orderService.existsById(req.getId()))
				throw new Exception("id为‘"+req.getId()+"’的记录不存在!");
		}
		if(!userService.existsById(req.getUserId()))
			throw new Exception("user id不存在! id:"+req.getUserId());
		
		if(req.getGoodsIdList()!=null) {
			for(Long id:req.getGoodsIdList()) {
				if(!goodsService.existsById(id))
					throw new Exception("goods id不存在! id:"+id);
			}	
		}
	}
}
