package com.zzy.shop.service;
import java.util.List;

import com.zzy.shop.bean.Goods;
import com.zzy.shop.core.Service;


public interface GoodsService extends Service<Goods> {

	List<Goods> findAllByTagId(Long tagId);
	List<Goods> findAllByCategoryId(Long categoryId);
	List<Goods> findAllByOrderId(Long orderId);
	List<Goods> findAllByKeyword(String keyword);
	
	
	
}
