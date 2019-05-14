package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;
public class IdReq{
	@ApiModelProperty(value="id",required=true)
    private Long id;

	public IdReq() {
		
	}
	public IdReq(Integer id) {
		this.id = (long)id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
	
	
}
