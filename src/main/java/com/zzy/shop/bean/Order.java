package com.zzy.shop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.zzy.shop.constants.CommonConstants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "t_order")
public class Order implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    
    @Column(name = "create_time")
    private Date createdTime;
    
    private Integer state = 0;
    
    private String remarks;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Transient
    private List<Goods> goodsList;

/**********************************************************************/
    public Order(){
    	createdTime = new Date();
    	code = "No."+System.currentTimeMillis();
    	state = CommonConstants.ORDER_STATE_CREATED;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
	
}