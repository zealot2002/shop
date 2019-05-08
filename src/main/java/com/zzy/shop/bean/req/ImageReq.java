package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class ImageReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="url(add|update)")
    private String url;
	@ApiModelProperty(value="是否有效(update)")
	private Integer inUsed;
/**********************************************************************/
	
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
