package com.zzy.shop.service.impl;
import com.zzy.shop.bean.ShopImageRel;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.ShopImageRelDao;
import com.zzy.shop.service.ShopImageRelService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class ShopImageRelServiceImpl implements ShopImageRelService {

	@Resource
    private ShopImageRelDao dao;

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public ShopImageRel save(ShopImageRel model) {
		return dao.save(model);
	}

	@Override
	public ShopImageRel findById(Long id) {
		Optional<ShopImageRel> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<ShopImageRel> findAll() {
		return dao.findAll();
	}

	@Override
	public ShopImageRel saveAndFlush(ShopImageRel model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public void deleteByShopId(Long shopId) {
		dao.deleteByShopId(shopId);
		
	}
}
