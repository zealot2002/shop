package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class AddressReq{
	@ApiModelProperty(value="id(update)")
    private Long id;
	@ApiModelProperty(value="用户id(add)")
    private Long userId;
	@ApiModelProperty(value="描述(add|update)")
    private String description;
/**********************************************************************/
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    

}