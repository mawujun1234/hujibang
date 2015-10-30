package com.mawujun.dianping.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="hjb_shop_image")
public class ShopImage  extends UUIDEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=30)
	private String shop_code;
	@Column(length=200)
	private String thumb_url;
	@Column(length=200)
	private String thumb_ogrinurl;
	@Column(length=200)
	private String image_url;
	@Column(length=200)
	private String image_orginurl;
	public String getShop_code() {
		return shop_code;
	}
	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getThumb_ogrinurl() {
		return thumb_ogrinurl;
	}
	public void setThumb_ogrinurl(String thumb_ogrinurl) {
		this.thumb_ogrinurl = thumb_ogrinurl;
	}
	public String getImage_orginurl() {
		return image_orginurl;
	}
	public void setImage_orginurl(String image_orginurl) {
		this.image_orginurl = image_orginurl;
	}

	

}
