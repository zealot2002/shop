package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Goods;


@Repository
public interface GoodsDao extends JpaRepository<Goods, Long>  {

	
	
	
}