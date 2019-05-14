package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.zzy.shop.bean.OrderGoodsRel;


@Repository
public interface OrderGoodsRelDao extends JpaRepository<OrderGoodsRel, Long>  {

	@Modifying
	@Query(value="delete from t_order_goods_rel where order_id = :orderId",nativeQuery=true)
	void deleteByOrderId(@Param("orderId") Long orderId);

	
	
}