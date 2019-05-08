package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.GoodsImageRel;


@Repository
public interface GoodsImageRelDao extends JpaRepository<GoodsImageRel, Long>  {

	@Modifying
	@Query(value="delete from t_goods_image_rel where goods_id = :goodsId",nativeQuery=true)
	void deleteByGoodsId(Long goodsId);
	
}