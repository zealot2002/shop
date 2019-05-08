package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zzy.shop.bean.Tag;


@Repository
public interface TagDao extends JpaRepository<Tag, Long>  {
	
	
}