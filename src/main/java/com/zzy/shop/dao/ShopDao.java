package com.zzy.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zzy.shop.bean.Shop;


@Repository
public interface ShopDao extends JpaRepository<Shop, Long>  {

	Shop findByName(String name);
	
	
}