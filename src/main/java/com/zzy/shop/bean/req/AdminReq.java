package com.zzy.shop.bean.req;

import io.swagger.annotations.ApiModelProperty;

public class AdminReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="用户名(add|update)")
    private String username;
	@ApiModelProperty(value="密码(add|update)")
    private String password;
/**********************************************************************/
    
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
}