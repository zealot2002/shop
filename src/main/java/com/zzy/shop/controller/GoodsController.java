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
import com.zzy.shop.bean.Category;
import com.zzy.shop.bean.Goods;
import com.zzy.shop.bean.GoodsImageRel;
import com.zzy.shop.bean.GoodsTagRel;
import com.zzy.shop.bean.Image;
import com.zzy.shop.bean.Tag;
import com.zzy.shop.bean.req.GoodsReq;
import com.zzy.shop.bean.req.IdReq;
import com.zzy.shop.bean.req.PageReq;
import com.zzy.shop.bean.req.PageWithIdReq;
import com.zzy.shop.bean.req.PageWithKeywordReq;
import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.service.CategoryService;
import com.zzy.shop.service.GoodsImageRelService;
import com.zzy.shop.service.GoodsService;
import com.zzy.shop.service.GoodsTagRelService;
import com.zzy.shop.service.ImageService;
import com.zzy.shop.service.TagService;
import com.zzy.shop.utils.PageUtil;

import io.swagger.annotations.ApiOperation;


/**
* Created by CodeGenerator on 2017/07/24.
*/
@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
    private GoodsService goodsService;
	@Autowired
    private CategoryService categoryService;
	@Autowired
    private GoodsImageRelService goodsImageRelService;
	@Autowired
    private GoodsTagRelService goodsTagRelService;
	@Autowired
    private ImageService imageService;
	@Autowired
    private TagService tagService;
	
/********************************************************************************************/
	@Transactional
	@ApiOperation(value="删除", notes="删除")
	@PostMapping(path = "/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result delete(@RequestBody IdReq req) {
		try {
			goodsImageRelService.deleteByGoodsId(req.getId());
			goodsTagRelService.deleteByGoodsId(req.getId());
			goodsService.deleteById(req.getId());
			return ResultGenerator.genSuccessResult();
		}catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
			return ResultGenerator.genFailResult("id为‘"+req.getId()+"’的记录不存在!");
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
			checkValid(req,CommonConstants.ACTION_ADD);
			Goods bean = new Goods();
			bean.setName(req.getName());
			bean.setDesciption(req.getDesciption());
			bean.setPrice(req.getPrice());
			bean.setCategoryId(req.getCategoryId());
			bean = goodsService.saveAndFlush(bean);
			
			/*handle imageList*/
			if(req.getImageIdList()!=null) {
				for(Long id:req.getImageIdList()) {
					GoodsImageRel rel = new GoodsImageRel();
					rel.setGoodsId(bean.getId());
					rel.setImageId(id);
					goodsImageRelService.save(rel);
				}
			}
			/*handle tagList*/
			if(req.getTagIdList()!=null) {
				for(Long id:req.getTagIdList()) {
					GoodsTagRel rel = new GoodsTagRel();
					rel.setGoodsId(bean.getId());
					rel.setTagId(id);
					goodsTagRelService.save(rel);
				}
			}
	        return ResultGenerator.genSuccessResult();
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@Transactional
	@ApiOperation(value="修改", notes="修改")
	@PostMapping(path = "/update",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result update(@RequestBody GoodsReq req) {
		try{
			checkValid(req,CommonConstants.ACTION_UPDATE);
			Goods bean = goodsService.findById(req.getId());
			bean.setName(req.getName());
			bean.setDesciption(req.getDesciption());
			bean.setPrice(req.getPrice());
			bean.setInUsed(req.getInUsed());
			bean.setCategoryId(req.getCategoryId());
			goodsService.save(bean);
			
			if(req.getImageIdList()!=null) {
				/*handle imageList*/
				goodsImageRelService.deleteByGoodsId(bean.getId());
				for(Long id:req.getImageIdList()) {
					GoodsImageRel rel = new GoodsImageRel();
					rel.setGoodsId(bean.getId());
					rel.setImageId(id);
					goodsImageRelService.save(rel);
				}
			}
			/*handle tagList*/
			if(req.getTagIdList()!=null) {
				goodsTagRelService.deleteByGoodsId(bean.getId());
				for(Long id:req.getTagIdList()) {
					GoodsTagRel rel = new GoodsTagRel();
					rel.setGoodsId(bean.getId());
					rel.setTagId(id);
					goodsTagRelService.save(rel);
				}
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
			Category category = categoryService.findById(bean.getCategoryId());
			bean.setCategoryName(category.getName());
			List<Image> imageList = imageService.findByGoodsId(idReq.getId());
			List<String> imageUrlList = new ArrayList<>();
			if(imageList != null) {
				for(Image image:imageList) {
					imageUrlList.add(image.getUrl());
				}
			}
			bean.setImageUrlList(imageUrlList);
			List<Tag> tagList = tagService.findByGoodsId(idReq.getId());
			bean.setTagList(tagList);
			
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
			for(Goods bean:targetList) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList != null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
				List<Tag> tagList = tagService.findByGoodsId(bean.getId());
				bean.setTagList(tagList);
				Category category = categoryService.findById(bean.getCategoryId());
				if(category!=null) {
					bean.setCategoryName(category.getName());	
				}
			}
			return ResultGenerator.genSuccessResult(targetList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	@ApiOperation(value="根据tagId分页查询", notes="根据tagId分页查询")
	@PostMapping(path = "/queryPageByTagId",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryPageByTagId(@RequestBody PageWithIdReq req) {
		try {
			List<Goods> list = goodsService.findAllByTagId(req.getId());
			List<Goods> targetList = new PageUtil<Goods>().getList(list,
					req.getPageNum(),req.getPageSize());
			for(Goods bean:targetList) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList != null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
				List<Tag> tagList = tagService.findByGoodsId(bean.getId());
				bean.setTagList(tagList);
				Category category = categoryService.findById(bean.getCategoryId());
				bean.setCategoryName(category.getName());
			}
			return ResultGenerator.genSuccessResult(targetList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="根据categoryId分页查询", notes="根据categoryId分页查询")
	@PostMapping(path = "/queryPageByCategoryId",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryPageByCategoryId(@RequestBody PageWithIdReq req) {
		try {
			List<Goods> list = goodsService.findAllByCategoryId(req.getId());
			List<Goods> targetList = new PageUtil<Goods>().getList(list,
					req.getPageNum(),req.getPageSize());
			for(Goods bean:targetList) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList != null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
				List<Tag> tagList = tagService.findByGoodsId(bean.getId());
				bean.setTagList(tagList);
				Category category = categoryService.findById(bean.getCategoryId());
				bean.setCategoryName(category.getName());
			}
			return ResultGenerator.genSuccessResult(targetList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	@ApiOperation(value="根据Keyword分页查询", notes="根据Keyword分页查询")
	@PostMapping(path = "/queryPageByKeyword",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Result queryPageByKeyword(@RequestBody PageWithKeywordReq req) {
		try {
			List<Goods> list = goodsService.findAllByKeyword(req.getKeyword());
			List<Goods> targetList = new PageUtil<Goods>().getList(list,
					req.getPageNum(),req.getPageSize());
			for(Goods bean:targetList) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList != null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
				List<Tag> tagList = tagService.findByGoodsId(bean.getId());
				bean.setTagList(tagList);
				Category category = categoryService.findById(bean.getCategoryId());
				bean.setCategoryName(category.getName());
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
			List<Goods> list = goodsService.findAll();
			for(Goods bean:list) {
				List<Image> imageList = imageService.findByGoodsId(bean.getId());
				List<String> imageUrlList = new ArrayList<>();
				if(imageList != null) {
					for(Image image:imageList) {
						imageUrlList.add(image.getUrl());
					}
				}
				bean.setImageUrlList(imageUrlList);
				List<Tag> tagList = tagService.findByGoodsId(bean.getId());
				bean.setTagList(tagList);
				Category category = categoryService.findById(bean.getCategoryId());
				bean.setCategoryName(category.getName());
			}
			return ResultGenerator.genSuccessResult(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult(e.toString());
		}
    }
	
	private void checkValid(GoodsReq req, int action) throws Exception{
		if(CommonConstants.ACTION_UPDATE == action) {
			if(!goodsService.existsById(req.getId()))
				throw new Exception("id为‘"+req.getId()+"’的记录不存在!");
		}
		if(!categoryService.existsById(req.getCategoryId()))
			throw new Exception("类别id不存在! id:"+req.getCategoryId());
		
		for(Long id:req.getImageIdList()) {
			if(!imageService.existsById(id))
				throw new Exception("image id不存在! id:"+id);
		}
		for(Long id:req.getTagIdList()) {
			if(!tagService.existsById(id))
				throw new Exception("tag id不存在! id:"+id);
		}
	}
}
