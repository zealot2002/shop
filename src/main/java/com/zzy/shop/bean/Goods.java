package com.zzy.shop.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_goods")
public class Goods implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "category_id")
	private Long categoryId;
    private String name;
    private String desciption;
	private Float price;
	@Column(name = "old_price")
	private Float oldPrice;
	@Column(name = "in_used")
	private Integer inUsed;
	
	@Transient
	private String categoryName;
	@Transient
    private List<String> imageUrlList;
    @Transient
    private List<Tag> tagList;
//    private Order order;
//	private List<Tag> tagList = new ArrayList<>();
/**********************************************************************/
	
    public Goods() {
    	imageUrlList = new ArrayList<>();
    	tagList = new ArrayList<>();
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getInUsed() {
		return inUsed;
	}
	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}
	
	public List<Tag> getTagList() {
		return tagList;
	}
	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getImageUrlList() {
		return imageUrlList;
	}
	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}
	public Float getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Float oldPrice) {
		this.oldPrice = oldPrice;
	}

	
	
}
