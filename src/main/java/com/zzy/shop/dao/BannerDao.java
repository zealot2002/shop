package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Banner;
import com.zzy.shop.bean.Category;


@Repository
public interface BannerDao extends JpaRepository<Banner, Long>  {

	
}