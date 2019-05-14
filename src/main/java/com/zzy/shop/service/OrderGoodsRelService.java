package com.zzy.shop.service;
import com.zzy.shop.bean.OrderGoodsRel;
import com.zzy.shop.core.Service;


public interface OrderGoodsRelService extends Service<OrderGoodsRel> {

	void deleteByOrderId(Long orderId);
}
