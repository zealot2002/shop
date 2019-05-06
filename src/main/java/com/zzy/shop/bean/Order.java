package com.zzy.shop.bean;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "t_order")
public class Order implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    
    @Column(name = "create_time")
    private String createTime;
    
    @ManyToOne()
    @JoinColumn(name="user_id")//设置在order表中的关联字段(外键)
    private User user;

/**********************************************************************/
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
	
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}