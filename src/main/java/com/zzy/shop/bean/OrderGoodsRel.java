package com.zzy.shop.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_order_goods_rel")
public class OrderGoodsRel implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "goods_id")
	private Long goodsId;
	@Column(name = "order_id")
	private Long orderId;
/**********************************************************************/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}