package com.zzy.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Order;


@Repository
public interface OrderDao extends JpaRepository<Order, Long>  {
	@Query(value="select * from t_order where user_id = :userId",nativeQuery=true)
	public List<Order> findAllByUserId(@Param("userId") Long userId);

	@Modifying
	@Query(value="delete from t_order where user_id = :userId",nativeQuery=true)
	public void deleteAllByUserId(@Param("userId") Long userId);
	
}