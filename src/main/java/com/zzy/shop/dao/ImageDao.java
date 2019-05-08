package com.zzy.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Image;


@Repository
public interface ImageDao extends JpaRepository<Image, Long>  {

	@Query(value="select * from t_image where id "
			+ "in (select image_id from t_goods_image_rel where goods_id = :goodsId)",nativeQuery=true)
	List<Image> findByGoodsId(@Param("goodsId") Long goodsId);
	
	
}