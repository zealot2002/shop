package com.zzy.shop.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzy.shop.core.Result;
import com.zzy.shop.core.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import com.zzy.shop.bean.Goods;
import com.zzy.shop.bean.Image;
import com.zzy.shop.bean.Shop;
import com.zzy.shop.bean.Tag;
import com.zzy.shop.bean.User;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.bean.req.UserReq;
import com.zzy.shop.service.GoodsService;
import com.zzy.shop.service.ImageService;
import com.zzy.shop.service.ShopService;
import com.zzy.shop.service.UserService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/miaozu")
public class App1Controller {
	@Autowired
    private UserService userService;
	@Autowired
    private ImageService imageService;
	@Autowired
    private ShopService shopService;
	
	@Autowired
    private GoodsService goodsService;
/*****************************************************************************************/
	
	@ApiOperation(value="bannerList", notes="bannerList")
	@PostMapping(path = "/bannerList",consumes= MediaType.ALL_VALUE)
    public Result bannerList() {
		try {
			List<Image> list = imageService.findAll();
			List<Image> list2 = new ArrayList<>();
			for(int i=0;i<5;i++) {
				list2.add(list.get(i));
			}
			return ResultGenerator.genSuccessResult(list2);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="登录", notes="登录")
	@PostMapping(path = "/login",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestBody UserReq req) {
		try{
			User bean = userService.findByUsername(req.getUsername());
			if(bean == null) {
				bean = new User();
				bean.setUsername(req.getUsername());
				userService.save(bean);
			}
			JSONObject json = new JSONObject();
			json.put("token", bean.getId());
	        return ResultGenerator.genSuccessResult(json);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="推荐商品列表", notes="推荐商品列表")
	@PostMapping(path = "/suggestedGoodsList",consumes= MediaType.APPLICATION_JSON_VALUE)
	public Result suggestedGoodsList(@RequestBody PageReq pageReq) {
		try {
			List<Goods> list = goodsService.findAll();
			List<Goods> targetList = new PageUtil<Goods>().getList(list,
					pageReq.getPageNum(),pageReq.getPageSize());
			
			for(Goods bean:targetList) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList != null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
			}
			return ResultGenerator.genSuccessResult(targetList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="店铺信息", notes="店铺信息")
	@PostMapping(path = "/shopInfo",consumes= MediaType.ALL_VALUE)
	public Result shopInfo() {
		try {
			Shop bean = shopService.findById(1L);
			return ResultGenerator.genSuccessResult(bean);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
}
