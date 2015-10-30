package com.mawujun.dianping.city;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mawujun.repository.idEntity.IdEntity;

@Entity
@Table(name="hjb_shop")
public class Shop implements IdEntity<String>{
	@Id
	@Column(length=30)
	private String id;
	@Column(length=30)
	private String name;
	@Column(length=100)
	private String thumb;
	@Column(length=250)
	private String thumb_orginurl;
	@Column(length=150)
	private String addr;//地址
	@Column(length=150)
	private String phone;//地址
	@Column(length=20)
	private String meanPrice;//人均
	
	@Column(length=30)
	private String city_id;
	
	@Transient
	private List<ShopImage> images=new ArrayList<ShopImage>();
	@Transient
	private List<ShopReview> reviewes=new ArrayList<ShopReview>();

	public void addImages(ShopImage image) {
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

	public List<ShopImage> getImages() {
		return images;
	}

	public void setImages(List<ShopImage> images) {
		this.images = images;
	}


	public List<ShopReview> getReviewes() {
		return reviewes;
	}


	public void setReviewes(List<ShopReview> reviewes) {
		this.reviewes = reviewes;
	}


	public String getCity_id() {
		return city_id;
	}


	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}


	public String getThumb_orginurl() {
		return thumb_orginurl;
	}


	public void setThumb_orginurl(String thumb_orginurl) {
		this.thumb_orginurl = thumb_orginurl;
	}
}
