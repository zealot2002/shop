package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Shop;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.ShopDao;
import com.zzy.shop.service.ShopService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class ShopServiceImpl implements ShopService {

	@Resource
    private ShopDao dao;


	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Shop save(Shop model) {
		return dao.save(model);
	}

	@Override
	public Shop findById(Long id) {
		Optional<Shop> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Shop> findAll() {
		return dao.findAll();
	}

	@Override
	public Shop saveAndFlush(Shop model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}
}
