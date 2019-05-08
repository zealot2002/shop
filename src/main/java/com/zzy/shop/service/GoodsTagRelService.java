package com.zzy.shop.service;
import com.zzy.shop.bean.GoodsTagRel;
import com.zzy.shop.core.Service;


public interface GoodsTagRelService extends Service<GoodsTagRel> {
	void deleteByGoodsId(Long goodsId);
}
