package com.zzy.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Table(name = "t_user")
public class User implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String username;
    private String password;
    private String deviceId;
    @Column(name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "expired_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredTime;

    private String phone;
    private String avatar;
    @Column(name = "in_used")
    private Integer inUsed;
    
/******************************************************************************/
	public User() {
		createdTime = new Date();
		inUsed = 1;
	}
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
    
    
    public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	
	public Integer getInUsed() {
		return inUsed;
	}
	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}