package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="用户id(add)")
    private Long userId;
    @ApiModelProperty(value="状态(update)")
    private Integer state = 0;
    @ApiModelProperty(value="备注(add|update)")
    private String remarks;
/**********************************************************************/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}