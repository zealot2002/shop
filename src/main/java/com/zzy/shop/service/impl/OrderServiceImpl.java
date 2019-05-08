package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Order;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zzy.shop.core.Service;
import com.zzy.shop.dao.OrderDao;
import com.zzy.shop.dao.UserDao;
import com.zzy.shop.service.OrderService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class OrderServiceImpl implements OrderService {

	@Resource
    private OrderDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Order save(Order model) {
		return dao.save(model);
	}

	@Override
	public Order findById(Long id) {
		Optional<Order> model = dao.findById(id);
		if(model.isPresent()) {
			return model.get();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Order saveAndFlush(Order model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public List<Order> findAllByUserId(Long userId) {
		return dao.findAllByUserId(userId);
	}

	@Override
	public void deleteAllByUserId(Long userId) {
		 dao.deleteAllByUserId(userId);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}
}
