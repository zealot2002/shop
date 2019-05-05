package com.zzy.shop.model;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_tag")
public class Tag implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 32)
    private String name;
	@ManyToOne
    private Content content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
