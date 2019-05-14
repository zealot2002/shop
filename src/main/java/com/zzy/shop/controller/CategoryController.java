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
import com.zzy.shop.core.ServiceException;
import com.zzy.shop.bean.Category;
import com.zzy.shop.bean.Image;
import com.zzy.shop.bean.req.CategoryReq;
import com.zzy.shop.bean.req.GoodsReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.service.CategoryService;
import com.zzy.shop.service.ImageService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
    private CategoryService categoryService;
	@Autowired
    private ImageService imageService;
	
	
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq idQuery) {
		try {
			categoryService.deleteById(idQuery.getId());
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
    public Result add(@RequestBody CategoryReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_ADD);
			Category bean = new Category();
			bean.setName(req.getName());
			bean.setImageId(req.getImageId());
			categoryService.save(bean);
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }

	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody CategoryReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_UPDATE);
			Category bean = categoryService.findById(req.getId());
			bean.setName(req.getName());
			bean.setImageId(req.getImageId());
			bean.setInUsed(req.getInUsed());
			categoryService.save(bean);
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
			Category bean = categoryService.findById(idReq.getId());
			if(bean.getImageId()!=null) {
				Image image = imageService.findById(bean.getImageId());
				bean.setImageUrl(image!=null?image.getUrl():"");
			}
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
			List<Category> list = categoryService.findAll();
			List<Category> targetList = new PageUtil<Category>().getList(list,
					pageReq.getPageNum(),pageReq.getPageSize());
			for(Category bean:targetList) {
				if(bean.getImageId()!=null) {
					Image image = imageService.findById(bean.getImageId());
					bean.setImageUrl(image!=null?image.getUrl():"");
				}
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
			List<Category> list = categoryService.findAll();
			for(Category bean:list) {
				if(bean.getImageId()!=null) {
					Image image = imageService.findById(bean.getImageId());
					bean.setImageUrl(image!=null?image.getUrl():"");
				}
			}
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	private void checkValid(CategoryReq req, int action) throws Exception{
		if(CommonConstants.ACTION_UPDATE == action) {
			if(!categoryService.existsById(req.getId()))
				throw new Exception("id为‘"+req.getId()+"’的记录不存在!");
			
			Category bean = categoryService.findByName(req.getName());
			if(bean!=null&&bean.getId()!=req.getId()) {
				throw new ServiceException("name 已经存在! name:"+req.getName());
			}
		}else if(CommonConstants.ACTION_ADD == action){
			Category bean = categoryService.findByName(req.getName());
			if(bean!=null){
				throw new ServiceException("name 已经存在! name:"+req.getName());
			}
		}
		
			
	}
}
