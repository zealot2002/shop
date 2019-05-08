package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Category;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.CategoryDao;
import com.zzy.shop.service.CategoryService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class CategoryServiceImpl implements CategoryService {

	@Resource
    private CategoryDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Category save(Category model) {
		return dao.save(model);
	}

	@Override
	public Category findById(Long id) {
		Optional<Category> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public Category saveAndFlush(Category model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public Category findByName(String name) {
		return dao.findByName(name);
	}
}
