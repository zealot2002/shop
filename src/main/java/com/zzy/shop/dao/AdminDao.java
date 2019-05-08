package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zzy.shop.bean.Admin;


@Repository
public interface AdminDao extends JpaRepository<Admin, Long>  {

//	@Query(value="select * from t_admin where username = :username",nativeQuery=true)
	Admin findByUsername(String username);
	
	
}