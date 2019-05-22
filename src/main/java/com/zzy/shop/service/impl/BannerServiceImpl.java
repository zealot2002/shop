package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Banner;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zzy.shop.dao.BannerDao;
import com.zzy.shop.service.BannerService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class BannerServiceImpl implements BannerService {

	@Resource
    private BannerDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Banner save(Banner model) {
		return dao.save(model);
	}

	@Override
	public Banner findById(Long id) {
		Optional<Banner> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Banner> findAll() {
		return dao.findAll();
	}

	@Override
	public Banner saveAndFlush(Banner model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}
}
