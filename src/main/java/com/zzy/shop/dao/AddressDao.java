package com.zzy.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.Address;
import com.zzy.shop.bean.Order;


@Repository
public interface AddressDao extends JpaRepository<Address, Long>  {
	@Query(value="select * from t_address where user_id = :userId",nativeQuery=true)
	public List<Address> findAllByUserId(@Param("userId") Long userId);

	@Modifying
	@Query(value="delete from t_address where user_id = :userId",nativeQuery=true)
	public void deleteAllByUserId(@Param("userId") Long userId);
	
}