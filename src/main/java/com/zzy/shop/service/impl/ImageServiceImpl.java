package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Image;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.ImageDao;
import com.zzy.shop.service.ImageService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class ImageServiceImpl implements ImageService {

	@Resource
    private ImageDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Image save(Image model) {
		return dao.save(model);
	}

	@Override
	public Image findById(Long id) {
		Optional<Image> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Image> findAll() {
		return dao.findAll();
	}

	@Override
	public Image saveAndFlush(Image model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public List<Image> findByGoodsId(Long goodsId) {
		return dao.findByGoodsId(goodsId);
	}
}
