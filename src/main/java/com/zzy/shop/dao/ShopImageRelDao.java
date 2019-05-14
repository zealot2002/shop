package com.zzy.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zzy.shop.bean.ShopImageRel;


@Repository
public interface ShopImageRelDao extends JpaRepository<ShopImageRel, Long>  {

	@Modifying
	@Query(value="delete from t_shop_image_rel where shop_id = :shopId",nativeQuery=true)
	void deleteByShopId(@Param("shopId") Long shopId);
	
}