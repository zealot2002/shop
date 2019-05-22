package com.zzy.shop.bean;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "t_shortcut")
public class Shortcut implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "image_url")
    private String imageUrl;
    private String route;
/**********************************************************************/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	

    
    
	
	
}
