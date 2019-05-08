package com.zzy.shop.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_image")
public class Image implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "in_used")
	private Integer inUsed;
    private String url;
/**********************************************************************/
    public Image() {
    	inUsed = 1;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getInUsed() {
		return inUsed;
	}

	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}

    

}