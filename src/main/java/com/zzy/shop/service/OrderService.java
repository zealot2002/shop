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
@Component
public class OrderService implements Service<Order> {

	@Resource
    private OrderDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void save(Order model) {
		dao.save(model);
	}

	@Override
	public Order findById(Long id) {
		Optional<Order> user = dao.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveAndFlush(Order model) {
		dao.saveAndFlush(model);
	}
}
