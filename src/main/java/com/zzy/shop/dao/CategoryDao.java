package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zzy.shop.bean.Category;


@Repository
public interface CategoryDao extends JpaRepository<Category, Long>  {
	
	
}