package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;
public class PathReq{
	@ApiModelProperty(value="path",required=true)
    private String path;

	public PathReq() {
		
	}
	public PathReq(String path) {
		this.path = path;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
