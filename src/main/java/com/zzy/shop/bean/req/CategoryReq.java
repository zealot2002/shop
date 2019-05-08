package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class CategoryReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="名称(add|update)")
    private String name;
	@ApiModelProperty(value="icon(add|update)")
	private Long imageId;
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
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
}