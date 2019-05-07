package com.zzy.shop.service;
import com.zzy.shop.bean.Order;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zzy.shop.core.Service;
import com.zzy.shop.dao.OrderDao;
import com.zzy.shop.dao.UserDao;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
public interface OrderService extends Service<Order> {
	List<Order> findAllByUserId(Long userId);
	void deleteAllByUserId(Long userId);
}
