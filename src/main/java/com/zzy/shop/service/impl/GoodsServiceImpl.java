package com.zzy.shop.service.impl;
import com.zzy.shop.bean.Goods;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.zzy.shop.dao.GoodsDao;
import com.zzy.shop.service.GoodsService;


/**
 * Created by CodeGenerator on 2017/07/24.
 */
@Component
public class GoodsServiceImpl implements GoodsService {

	@Resource
    private GoodsDao dao;


	/*假删除*/
	@Override
	public void deleteById(Long id) {
		Optional<Goods> bean = dao.findById(id);
		if(bean.isPresent()) {
			bean.get().setInUsed(0);
			dao.save(bean.get());
		}
	}

	@Override
	public Goods save(Goods model) {
		return dao.save(model);
	}

	@Override
	public Goods findById(Long id) {
		Optional<Goods> bean = dao.findById(id);
		if(bean.isPresent()) {
			return bean.get();
		}
		return null;
	}

	@Override
	public List<Goods> findAll() {
		return dao.findAll();
	}

	@Override
	public Goods saveAndFlush(Goods model) {
		return dao.saveAndFlush(model);
	}

	@Override
	public boolean existsById(Long id) {
		return dao.existsById(id);
	}
	
}
