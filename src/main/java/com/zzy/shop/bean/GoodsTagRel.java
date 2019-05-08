package com.zzy.shop.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_goods_tag_rel")
public class GoodsTagRel implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "goods_id")
	private Long goodsId;
	@Column(name = "tag_id")
	private Long tagId;
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
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

}