package com.zzy.shop.service.impl;
import com.zzy.shop.bean.GoodsTagRel;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.GoodsTagRelDao;
import com.zzy.shop.service.GoodsTagRelService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class GoodsTagRelServiceImpl implements GoodsTagRelService {

	@Resource
    private GoodsTagRelDao dao;

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public GoodsTagRel save(GoodsTagRel model) {
		return dao.save(model);
	}

	@Override
	public GoodsTagRel findById(Long id) {
		Optional<GoodsTagRel> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<GoodsTagRel> findAll() {
		return dao.findAll();
	}

	@Override
	public GoodsTagRel saveAndFlush(GoodsTagRel model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}

	@Override
	public void deleteByGoodsId(Long goodsId) {
		dao.deleteByGoodsId(goodsId);
		
	}
}
