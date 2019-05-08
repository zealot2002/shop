package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Tag;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.TagDao;
import com.zzy.shop.service.TagService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class TagServiceImpl implements TagService {

	@Resource
    private TagDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Tag save(Tag model) {
		return dao.save(model);
	}

	@Override
	public Tag findById(Long id) {
		Optional<Tag> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Tag> findAll() {
		return dao.findAll();
	}

	@Override
	public Tag saveAndFlush(Tag model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public List<Tag> findByGoodsId(Long goodsId) {
		return dao.findByGoodsId(goodsId);
	}

	@Override
	public Tag findByName(String name) {
		return dao.findByName(name);
	}
}
