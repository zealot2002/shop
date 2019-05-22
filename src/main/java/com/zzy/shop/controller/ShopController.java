package com.zzy.shop.controller;
import java.util.ArrayList;
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
import com.zzy.shop.core.ServiceException;
import com.zzy.shop.bean.Address;
import com.zzy.shop.bean.Admin;
import com.zzy.shop.bean.Goods;
import com.zzy.shop.bean.GoodsImageRel;
import com.zzy.shop.bean.Image;
import com.zzy.shop.bean.Shop;
import com.zzy.shop.bean.ShopImageRel;
import com.zzy.shop.bean.Tag;
import com.zzy.shop.bean.req.AddressReq;
import com.zzy.shop.bean.req.AdminReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.bean.req.ShopReq;
import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.service.ImageService;
import com.zzy.shop.service.ShopImageRelService;
import com.zzy.shop.service.ShopService;
import com.zzy.shop.service.UserService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/shop")
public class ShopController {
	@Autowired
    private ShopService shopService;
	@Autowired
    private ImageService imageService;
	@Autowired
    private ShopImageRelService shopImageRelService;
	
/******************************************************************************/
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idReq) {
		try {
//			shopImageRelService.deleteByShopId(idReq.getId());
			shopService.deleteById(idReq.getId());
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
    public Result add(@RequestBody ShopReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_ADD);
			Shop bean = new Shop();
			bean.setName(req.getName());
			bean.setAddress(req.getAddress());
			bean.setDescription(req.getDescription());
			bean.setPhone(req.getPhone());
			bean = shopService.saveAndFlush(bean);
			
			/*handle imageList*/
			if(req.getImageIdList()!=null) {
				for(Long id:req.getImageIdList()) {
					ShopImageRel rel = new ShopImageRel();
					rel.setShopId(bean.getId());
					rel.setImageId(id);
					shopImageRelService.save(rel);
				}
			}
			shopService.save(bean);
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@Transactional
	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody ShopReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_UPDATE);
			Shop bean = shopService.findById(req.getId());
			bean.setAddress(req.getAddress());
			bean.setDescription(req.getDescription());
			bean.setPhone(req.getPhone());
			if(req.getImageIdList()!=null) {
				/*handle imageList*/
				shopImageRelService.deleteByShopId(bean.getId());
				for(Long id:req.getImageIdList()) {
					ShopImageRel rel = new ShopImageRel();
					rel.setShopId(bean.getId());
					rel.setImageId(id);
					shopImageRelService.save(rel);
				}
			}
			shopService.save(bean);
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
			Shop bean = shopService.findById(idReq.getId());
			List<Image> imageList = imageService.findByGoodsId(idReq.getId());
			List<String> imageUrlList = new ArrayList<>();
			if(imageList!=null) {
				for(Image image:imageList) {
					imageUrlList.add(image.getUrl());
				}
			}
			bean.setImageUrlList(imageUrlList);
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
			List<Shop> list = shopService.findAll();
			List<Shop> targetList = new PageUtil<Shop>().getList(list,
					pageReq.getPageNum(),pageReq.getPageSize());
			
			for(Shop bean:targetList) {
				List<Image> imageList = imageService.findByShopId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList!=null) {
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
	@ApiOperation(value="查询所有", notes="查询所有")
	@PostMapping(path = "/queryAll",consumes= MediaType.ALL_VALUE)
    public Result queryAll() {
		try {
			List<Shop> list = shopService.findAll();
			for(Shop bean:list) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList!=null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
			}
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	private void checkValid(ShopReq req, int action) throws Exception{
		if(CommonConstants.ACTION_UPDATE == action) {
			if(!shopService.existsById(req.getId()))
				throw new Exception("id为‘"+req.getId()+"’的记录不存在!");
		}else if(CommonConstants.ACTION_ADD == action) {
			if(shopService.findByName(req.getName())!=null)
				throw new ServiceException("name 已经存在! name:"+req.getName());
		}
	}
}
