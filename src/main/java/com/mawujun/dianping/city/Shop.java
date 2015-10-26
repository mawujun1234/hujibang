package com.mawujun.dianping.city;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

//@Entity
//@Table(name="hjb_shop")
public class Shop {
	@Id
	private String id;
	@Column(length=30)
	private String name;
	@Column(length=50)
	private String thumb;
	@Column(length=150)
	private String addr;//地址
	@Column(length=150)
	private String phone;//地址
	@Column(length=20)
	private String meanPrice;//人均
	
	@Transient
	private List<String[]> images=new ArrayList<String[]>();
	@Transient
	private List<Review> reviewes=new ArrayList<Review>();

	public void addImages(String[] image) {
		this.images.add(image);
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMeanPrice() {
		return meanPrice;
	}

	public void setMeanPrice(String meanPrice) {
		this.meanPrice = meanPrice;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String[]> getImages() {
		return images;
	}

	public void setImages(List<String[]> images) {
		this.images = images;
	}


	public List<Review> getReviewes() {
		return reviewes;
	}


	public void setReviewes(List<Review> reviewes) {
		this.reviewes = reviewes;
	}
}
