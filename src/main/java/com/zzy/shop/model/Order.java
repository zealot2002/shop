package com.zzy.shop.model;

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
    
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},
			optional=false)//可选属性optional=false,表示user不能为空。删除order，不影响user
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