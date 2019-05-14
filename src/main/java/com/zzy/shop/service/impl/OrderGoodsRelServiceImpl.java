package com.zzy.shop.service.impl;
import com.zzy.shop.bean.OrderGoodsRel;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.OrderGoodsRelDao;
import com.zzy.shop.service.OrderGoodsRelService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class OrderGoodsRelServiceImpl implements OrderGoodsRelService {

	@Resource
    private OrderGoodsRelDao dao;

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public OrderGoodsRel save(OrderGoodsRel model) {
		return dao.save(model);
	}

	@Override
	public OrderGoodsRel findById(Long id) {
		Optional<OrderGoodsRel> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<OrderGoodsRel> findAll() {
		return dao.findAll();
	}

	@Override
	public OrderGoodsRel saveAndFlush(OrderGoodsRel model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public void deleteByOrderId(Long orderId) {
		dao.deleteByOrderId(orderId);
		
	}

	
}
