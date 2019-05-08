package com.zzy.shop.service;
import com.zzy.shop.bean.Order;
import java.util.List;
import com.zzy.shop.core.Service;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
public interface OrderService extends Service<Order> {
	List<Order> findAllByUserId(Long userId);
	void deleteAllByUserId(Long userId);
}
