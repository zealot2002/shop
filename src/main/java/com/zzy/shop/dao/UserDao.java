package com.zzy.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Order;
import com.zzy.shop.bean.User;


@Repository
public interface UserDao extends JpaRepository<User, Long>  {
	User findByPhone(String phone);

	
	@Query(value="select * from t_order" ,nativeQuery=true)
	List<Order> getOrders();

	
}