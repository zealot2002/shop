package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.User;


@Repository
public interface UserDao extends JpaRepository<User, Long>  {
	User findByPhone(String phone);
	
}