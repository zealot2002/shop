package com.zzy.shop.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32)
    private String code;
    @Column(name = "create_time")
    private String createTime;
    
    @OneToMany
    private Set<Content> contentList;
    


}