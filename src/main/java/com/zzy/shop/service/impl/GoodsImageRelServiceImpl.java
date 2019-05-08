package com.zzy.shop.service.impl;
import com.zzy.shop.bean.GoodsImageRel;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.GoodsImageRelDao;
import com.zzy.shop.service.GoodsImageRelService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class GoodsImageRelServiceImpl implements GoodsImageRelService {

	@Resource
    private GoodsImageRelDao dao;

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public GoodsImageRel save(GoodsImageRel model) {
		return dao.save(model);
	}

	@Override
	public GoodsImageRel findById(Long id) {
		Optional<GoodsImageRel> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<GoodsImageRel> findAll() {
		return dao.findAll();
	}

	@Override
	public GoodsImageRel saveAndFlush(GoodsImageRel model) {
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
