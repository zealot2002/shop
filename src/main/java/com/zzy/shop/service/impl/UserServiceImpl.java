package com.zzy.shop.service.impl;

import com.zzy.shop.dao.UserDao;
import com.zzy.shop.model.User;
import com.zzy.shop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

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
