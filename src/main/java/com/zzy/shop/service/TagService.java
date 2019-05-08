package com.zzy.shop.service;
import java.util.List;

import com.zzy.shop.bean.Tag;
import com.zzy.shop.core.Service;


public interface TagService extends Service<Tag> {

	List<Tag> findByGoodsId(Long goodsId);

	Tag findByName(String name);
}
