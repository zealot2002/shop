package com.zzy.shop.service;
import com.zzy.shop.bean.GoodsImageRel;
import com.zzy.shop.core.Service;


public interface GoodsImageRelService extends Service<GoodsImageRel> {
	void deleteByGoodsId(Long goodsId);
}
