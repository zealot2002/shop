package com.zzy.shop.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_shop_info")
public class ShopInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 256)
    private String address;
    
    @Column(length = 32)
    private String phone;
    
    @OneToMany
    private Set<Image> imageList;
    

}