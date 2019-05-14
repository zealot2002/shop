package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;
public class ImageReq{
	@ApiModelProperty(value="id",required=true)
    private Long id;

	@ApiModelProperty(value="路径(add|update)")
	private String url;

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
	
	
}
