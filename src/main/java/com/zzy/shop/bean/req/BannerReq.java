package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class BannerReq {
	@ApiModelProperty(value = "id(update)", required = false)
	private Long id;
	@ApiModelProperty(value = "imageUrl(add|update)", required = false)
	private String imageUrl;
	@ApiModelProperty(value = "linkUrl(add|update)", required = false)
	private String linkUrl;

	/**********************************************************************/
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
