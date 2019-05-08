package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Image;


@Repository
public interface ImageDao extends JpaRepository<Image, Long>  {
	
	
}