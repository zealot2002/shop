package com.zzy.shop.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_shop_image_rel")
public class ShopImageRel implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "shop_id")
	private Long shopId;
	@Column(name = "image_id")
	private Long imageId;
/**********************************************************************/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
}