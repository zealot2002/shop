package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Address;
import com.zzy.shop.bean.Order;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zzy.shop.core.Service;
import com.zzy.shop.dao.AddressDao;
import com.zzy.shop.dao.OrderDao;
import com.zzy.shop.dao.UserDao;
import com.zzy.shop.service.AddressService;
import com.zzy.shop.service.OrderService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class AddressServiceImpl implements AddressService {

	@Resource
    private AddressDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void save(Address model) {
		dao.save(model);
	}

	@Override
	public Address findById(Long id) {
		Optional<Address> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Address> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveAndFlush(Address model) {
		dao.saveAndFlush(model);
	}

	@Override
	public List<Address> findAllByUserId(Long userId) {
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
