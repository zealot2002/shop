package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Order;


@Repository
public interface OrderDao extends JpaRepository<Order, Long>  {
	
}