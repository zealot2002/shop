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
import com.zzy.shop.bean.req.GoodsReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.service.GoodsImageRelService;
import com.zzy.shop.service.GoodsService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/Goods")
public class GoodsController {
	@Autowired
    private GoodsService goodsService;
	@Autowired
    private GoodsImageRelService goodsImageRelService;
	
/********************************************************************************************/
	@Transactional
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idQuery) {
		try {
			goodsService.deleteById(idQuery.getId());
			return ResultGenerator.genSuccessResult();
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+idQuery.getId()+"’的记录不存在!");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@Transactional
	@ApiOperation(value="新增", notes="新增")
	@PostMapping(path = "/add",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result add(@RequestBody GoodsReq req) {
		try{
			Goods bean = new Goods();
			bean.setName(req.getName());
			bean.setDesciption(req.getDesciption());
			bean.setPrice(req.getPrice());
			bean = goodsService.saveAndFlush(bean);
			
			/*handle imageList*/
			for(Long id:req.getImageIdList()) {
				GoodsImageRel rel = new GoodsImageRel();
				rel.setGoodsId(bean.getId());
				rel.setImageId(id);
				goodsImageRelService.save(rel);
			}
			/*handle tagList*/
//			for(Long id:req.getImageIdList()) {
//				GoodsImageRel rel = new GoodsImageRel();
//				rel.setGoodsId(bean.getId());
//				rel.setImageId(id);
//				goodsImageRelService.save(rel);
//			}
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }

	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody GoodsReq req) {
		try{
			Goods bean = goodsService.findById(req.getId());
			if(bean == null) {
				return ResultGenerator.genFailResult("id为‘"+req.getId()+"’的记录不存在!");
			}
			bean.setName(req.getName());
			bean.setDesciption(req.getDesciption());
			bean.setPrice(req.getPrice());
			goodsService.save(bean);
			
			/*handle imageList*/
			goodsImageRelService.deleteByGoodsId(bean.getId());
			for(Long id:req.getImageIdList()) {
				GoodsImageRel rel = new GoodsImageRel();
				rel.setGoodsId(bean.getId());
				rel.setImageId(id);
				goodsImageRelService.save(rel);
			}
			
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
			Goods bean = goodsService.findById(idReq.getId());
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
			List<Goods> list = goodsService.findAll();
			List<Goods> targetList = new PageUtil<Goods>().getList(list,
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
			List<Goods> list = goodsService.findAll();
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
}
