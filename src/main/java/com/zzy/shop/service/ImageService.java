package com.zzy.shop.service;
import java.util.List;

import com.zzy.shop.bean.Image;
import com.zzy.shop.core.Service;


public interface ImageService extends Service<Image> {

	List<Image> findByGoodsId(Long goodsId);

	List<Image> findByShopId(Long id);
	
}
