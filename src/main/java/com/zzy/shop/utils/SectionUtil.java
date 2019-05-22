package com.zzy.shop.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zzy.shop.bean.Banner;
import com.zzy.shop.bean.Category;
import com.zzy.shop.bean.Goods;
import com.zzy.shop.bean.Image;
import com.zzy.shop.bean.Shortcut;
import com.zzy.shop.bean.Tag;
import com.zzy.shop.service.BannerService;
import com.zzy.shop.service.GoodsService;
import com.zzy.shop.service.ImageService;
import com.zzy.shop.service.TagService;

public class SectionUtil{
	
	public static Object banner(BannerService bannerService) {
		List<Banner> bannerList = bannerService.findAll();
		JSONObject s = new JSONObject();
		s.put("templateId", 1);
		s.put("dataList", bannerList);
		return s;
	}
	public static Object miaopai(GoodsService service,
			ImageService imageService,TagService tagService) {
		List<Goods> list = service.findAllByTagId(5L);
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
		}
		
		JSONObject s = new JSONObject();
		s.put("templateId", 5);
		s.put("dataList", list);
		return s;
	}
	public static Object noticeList() {
		JSONObject s = new JSONObject();
		s.put("templateId", 2);
		s.put("dataList", new ArrayList());
		return s;
	}
	public static Object divider() {
		JSONObject s = new JSONObject();
		s.put("templateId", 3);
		s.put("dataList", new ArrayList());
		return s;
	}
	public static Object countDown() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(20400000);
		JSONObject s = new JSONObject();
		s.put("templateId", 4);
		s.put("dataList",list);
		return s;
	}
	public static Object title(String title) {
		ArrayList<String> list = new ArrayList<>();
		list.add(title);
		JSONObject s = new JSONObject();
		s.put("templateId", 6);
		s.put("dataList",list);
		return s;
	}
	public static Object shortcut(int index) {
		ArrayList<Shortcut> list = new ArrayList<>();
		Shortcut cut1 = new Shortcut();
		Shortcut cut2 = new Shortcut();
		Shortcut cut3 = new Shortcut();
		Shortcut cut4 = new Shortcut();
		if(index == 1) {
			cut1.setImageUrl("http://172.30.14.202/img/s1-1.jpg");
			cut2.setImageUrl("http://172.30.14.202/img/s1-2.jpg");
			cut3.setImageUrl("http://172.30.14.202/img/s1-3.jpg");
			cut4.setImageUrl("http://172.30.14.202/img/s1-4.jpg");
		}else if(index == 2) {
			cut1.setImageUrl("http://172.30.14.202/img/s2-1.jpg");
			cut2.setImageUrl("http://172.30.14.202/img/s2-2.jpg");
			cut3.setImageUrl("http://172.30.14.202/img/s2-3.jpg");
			cut4.setImageUrl("http://172.30.14.202/img/s2-4.jpg");
		}else if(index == 3) {
			cut1.setImageUrl("http://172.30.14.202/img/s3-1.jpg");
			cut2.setImageUrl("http://172.30.14.202/img/s3-2.jpg");
			cut3.setImageUrl("http://172.30.14.202/img/s3-3.jpg");
			cut4.setImageUrl("http://172.30.14.202/img/s3-4.jpg");
		}else if(index == 4) {
			cut1.setImageUrl("http://172.30.14.202/img/s4-1.jpg");
			cut2.setImageUrl("http://172.30.14.202/img/s4-2.jpg");
			cut3.setImageUrl("http://172.30.14.202/img/s4-3.jpg");
			cut4.setImageUrl("http://172.30.14.202/img/s4-4.jpg");
		}
		list.add(cut1);
		list.add(cut2);
		list.add(cut3);
		list.add(cut4);
		JSONObject s = new JSONObject();
		s.put("templateId", 7);
		s.put("dataList", list);
		return s;
	}
	
	
}
