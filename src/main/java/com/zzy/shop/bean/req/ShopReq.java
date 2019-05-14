package com.zzy.shop.bean.req;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="名称(add|update)")
    private String name;
	@ApiModelProperty(value="地址(add|update)")
    private String address;
	@ApiModelProperty(value="电话(add|update)")
    private String phone;
	@ApiModelProperty(value="描述(add|update)")
    private String description;
	
	@ApiModelProperty(value="imageIdList(add|update)")
    private List<Long> imageIdList;
/**********************************************************************/
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getImageIdList() {
		return imageIdList;
	}

	public void setImageIdList(List<Long> imageIdList) {
		this.imageIdList = imageIdList;
	}


}