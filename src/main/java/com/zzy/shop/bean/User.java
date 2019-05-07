package com.zzy.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "t_user")
public class User implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ApiModelProperty(value="用户名",required=true)
    private String username;
	@ApiModelProperty(value="密码",required=true)
    private String password;
    
    @Column(name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @ApiModelProperty(value="电话",required=true)
    private String phone;
    @ApiModelProperty(value="头像",required=false)
    private String avatar;
    
//    @OneToMany(mappedBy = "user")
//    private List<Order> orders=new ArrayList<>();

    
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

    
//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	
	//工具方法，不需要映射为数据表的一列
    @Transient
    public String getInfo(){
        return "username:"+username+",phone:"+phone;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createdTime=" + createdTime
				+ ", phone=" + phone + ", avatar=" + avatar + "]";
	}
    
    

}