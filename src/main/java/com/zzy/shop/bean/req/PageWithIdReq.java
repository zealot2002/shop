package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class PageWithIdReq{
	@ApiModelProperty(value="pageNum",required=true)
    private Integer pageNum;
	@ApiModelProperty(value="pageSize",required=true)
	private Integer pageSize;
	@ApiModelProperty(value="id",required=true)
	private Long id;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
