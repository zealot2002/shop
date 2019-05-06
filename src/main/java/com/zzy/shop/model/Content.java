package com.zzy.shop.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_content")
public class Content implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String desciption;
	private Float price;
	private Category category;
    private Order order;
	private Set<Tag> tagList;
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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	
	
}
