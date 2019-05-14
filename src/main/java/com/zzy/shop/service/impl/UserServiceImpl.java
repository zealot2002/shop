package com.zzy.shop.service.impl;
import com.zzy.shop.bean.User;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zzy.shop.dao.UserDao;
import com.zzy.shop.service.UserService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class UserServiceImpl implements UserService {

	@Resource
    private UserDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public User save(User model) {
		return dao.save(model);
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = dao.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public User saveAndFlush(User model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

}
