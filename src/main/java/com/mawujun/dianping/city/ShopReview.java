package com.mawujun.dianping.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="hjb_shop_review")
public class ShopReview extends UUIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=30)
	private String shop_code;
	@Column(length=30)
	private String user_id;
	@Column(length=30)
	private String user_name;
	@Column(length=200)
	private String user_img;
	@Column(length=250)
	private String user_img_url;//在点评上的地址
	
	@Column(length=500)
	private String content;//评价内容
	
	private Integer rst_skill;//技术
	private Integer rst_envi;//环境
	private Integer rst_service;//服务
	@Column(length=30)
	private String rst_skill_txt;//技术
	@Column(length=30)
	private String rst_envi_txt;//环境
	@Column(length=30)
	private String rst_service_txt;//服务
	


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_img() {
		return user_img;
	}


	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getRst_skill() {
		return rst_skill;
	}


	public void setRst_skill(Integer rst_skill) {
		this.rst_skill = rst_skill;
	}


	public Integer getRst_envi() {
		return rst_envi;
	}


	public void setRst_envi(Integer rst_envi) {
		this.rst_envi = rst_envi;
	}


	public Integer getRst_service() {
		return rst_service;
	}


	public void setRst_service(Integer rst_service) {
		this.rst_service = rst_service;
	}


	public String getRst_skill_txt() {
		return rst_skill_txt;
	}


	public void setRst_skill_txt(String rst_skill_txt) {
		this.rst_skill_txt = rst_skill_txt;
	}


	public String getRst_envi_txt() {
		return rst_envi_txt;
	}


	public void setRst_envi_txt(String rst_envi_txt) {
		this.rst_envi_txt = rst_envi_txt;
	}


	public String getRst_service_txt() {
		return rst_service_txt;
	}


	public void setRst_service_txt(String rst_service_txt) {
		this.rst_service_txt = rst_service_txt;
	}


	public String getShop_code() {
		return shop_code;
	}


	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}


	public String getUser_img_url() {
		return user_img_url;
	}


	public void setUser_img_url(String user_img_url) {
		this.user_img_url = user_img_url;
	}
}
