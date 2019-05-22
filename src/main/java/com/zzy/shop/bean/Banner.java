package com.zzy.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "t_banner")
public class Banner implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "link_url")
	private String linkUrl;

	/**********************************************************************/

	public Banner() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
