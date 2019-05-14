package com.zzy.shop.service;
import com.zzy.shop.bean.ShopImageRel;
import com.zzy.shop.core.Service;


public interface ShopImageRelService extends Service<ShopImageRel> {
	void deleteByShopId(Long ShopId);
}
