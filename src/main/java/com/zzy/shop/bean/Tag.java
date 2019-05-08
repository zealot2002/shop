package com.zzy.shop.bean;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_tag")
public class Tag implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    @Column(name = "in_used")
    private Integer inUsed;
	
/**********************************************************************/
	
    public Tag() {
    	inUsed = 1;
    }
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
	public Integer getInUsed() {
		return inUsed;
	}
	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}
	
	
}
