package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class CategoryReq{
	@ApiModelProperty(value="id(update)",required = false)
	private Long id;
	@ApiModelProperty(value="名称(add|update)",required = false)
    private String name;
	@ApiModelProperty(value="imageId(add|update)",required = false)
	private Long imageId;
	@ApiModelProperty(value="是否有效(update)",required = false)
	private Integer inUsed;
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
	public Integer getInUsed() {
		return inUsed;
	}
	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}
	
	
}
	