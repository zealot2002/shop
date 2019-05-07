package com.zzy.shop.common.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class PageQuery implements Serializable{
	@ApiModelProperty(value="pageNum",required=true)
    private Integer pageNum;
	@ApiModelProperty(value="pageSize",required=true)
	private Integer pageSize;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
