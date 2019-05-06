package com.zzy.shop.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_shop_info")
public class ShopInfo implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String address;
    private String phone;
/**********************************************************************/
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}