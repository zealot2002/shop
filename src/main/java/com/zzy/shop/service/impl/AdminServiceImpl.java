package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Admin;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.AdminDao;
import com.zzy.shop.service.AdminService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class AdminServiceImpl implements AdminService {

	@Resource
    private AdminDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Admin save(Admin model) {
		return dao.save(model);
	}

	@Override
	public Admin findById(Long id) {
		Optional<Admin> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Admin> findAll() {
		return dao.findAll();
	}

	@Override
	public Admin saveAndFlush(Admin model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public Admin findByUsername(String username) {
		return dao.findByUsername(username);
	}
}
