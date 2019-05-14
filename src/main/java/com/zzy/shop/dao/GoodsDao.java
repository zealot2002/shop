package com.zzy.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Goods;


@Repository
public interface GoodsDao extends JpaRepository<Goods, Long>  {

	@Query(value="select * from t_goods where category_id = :categoryId",nativeQuery=true)
	List<Goods> findAllByCategoryId(@Param("categoryId") Long categoryId);
	
	@Query(value="select * from t_goods where id "
			+ "in (select goods_id from t_goods_tag_rel where tag_id = :tagId)",nativeQuery=true)
	List<Goods> findAllByTagId(@Param("tagId") Long tagId);
	

	@Query(value="select * from t_goods where id "
			+ "in (select goods_id from t_order_goods_rel where order_id = :orderId)",nativeQuery=true)
	List<Goods> findAllByOrderId(@Param("orderId") Long orderId);

	
	@Query(value="select * from t_goods where name like concat('%',?1,'%')",nativeQuery=true)
//	@Query("SELECT u.username FROM User u WHERE u.username LIKE CONCAT('%',:keyword,'%')")
	List<Goods> findAllByKeyword(@Param("keyword") String keyword);

	
}