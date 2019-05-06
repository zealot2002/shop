package com.zzy.shop.service;
import com.zzy.shop.bean.User;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zzy.shop.core.Service;
import com.zzy.shop.dao.UserDao;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
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
	public void saveAndFlush(User model) {
		dao.saveAndFlush(model);
	}
}
