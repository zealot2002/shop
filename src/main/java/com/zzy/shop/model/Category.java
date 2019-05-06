package com.zzy.shop.model;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_category")
public class Category implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
	private Image icon;
/**********************************************************************/
	
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
	public Image getIcon() {
		return icon;
	}
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
	
}
