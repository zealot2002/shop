package com.zzy.shop.bean.req;
import java.util.ArrayList;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;

public class GoodsReq{
	@ApiModelProperty(value="id(update)")
	private Long id;
	@ApiModelProperty(value="类别id(add|update)")
	private Long categoryId;
	@ApiModelProperty(value="名称(add|update)")
    private String name;
	@ApiModelProperty(value="描述(add|update)")
    private String desciption;
	@ApiModelProperty(value="价格(add|update)")
	private Float price;
	@ApiModelProperty(value="是否有效(update)")
	private Integer inUsed;
	@ApiModelProperty(value="imageIdList(add|update)")
    private List<Long> imageIdList;
	@ApiModelProperty(value="tagIdList(add|update)")
    private List<Long> tagIdList;
/**********************************************************************/
	
    public GoodsReq() {
    	imageIdList = new ArrayList<>();
    	tagIdList = new ArrayList<>();
    	inUsed = 1;
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<Long> getImageIdList() {
		return imageIdList;
	}
	public void setImageIdList(List<Long> imageIdList) {
		this.imageIdList = imageIdList;
	}
	public List<Long> getTagIdList() {
		return tagIdList;
	}
	public void setTagIdList(List<Long> tagIdList) {
		this.tagIdList = tagIdList;
	}
	public Integer getInUsed() {
		return inUsed;
	}
	public void setInUsed(Integer inUsed) {
		this.inUsed = inUsed;
	}

	
	
}
