package com.zzy.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.zzy.shop.bean.Tag;


@Repository
public interface TagDao extends JpaRepository<Tag, Long>  {
	
	@Query(value="select * from t_tag where id "
			+ "in (select tag_id from t_goods_tag_rel where goods_id = :goodsId)",nativeQuery=true)
	List<Tag> findByGoodsId(Long goodsId);

	Tag findByName(String name);
	
	
}