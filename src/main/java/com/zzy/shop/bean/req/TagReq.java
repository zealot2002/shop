package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class TagReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="名称(add|update)")
    private String name;
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
}
