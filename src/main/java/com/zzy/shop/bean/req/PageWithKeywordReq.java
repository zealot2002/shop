package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class PageWithKeywordReq{
	@ApiModelProperty(value="pageNum",required=true)
    private Integer pageNum;
	@ApiModelProperty(value="pageSize",required=true)
	private Integer pageSize;
	@ApiModelProperty(value="keyword",required=true)
	private String keyword;
	
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	
}
