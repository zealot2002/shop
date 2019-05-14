package com.zzy.shop.bean.req;

import com.zzy.shop.constants.CommonConstants;
import com.zzy.shop.service.UserService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserReq{
	@ApiModelProperty(value="id(update)")
    private Long id;
	@ApiModelProperty(value="用户名(add|update|login)")
    private String username;
	@ApiModelProperty(value="密码(add|update)")
    private String password;
    @ApiModelProperty(value="电话(add|update)")
    private String phone;
    @ApiModelProperty(value="头像(add|update)")
    private String avatar;
/******************************************************************************/
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}