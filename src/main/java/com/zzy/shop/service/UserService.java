package com.zzy.shop.service;
import com.zzy.shop.model.User;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.zzy.shop.core.Service;
import com.zzy.shop.dao.UserDao;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
public class UserService implements Service<User> {

	@Resource
    private UserDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void save(User model) {
		dao.save(model);
		
	}

	@Override
	public Optional<User> findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}
}
