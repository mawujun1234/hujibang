package com.mawujun.dianping.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hjb_shop")
public class Shop {
	@Id
	private Integer id;
	@Column(length=30)
	private String name;
	@Column(length=50)
	private String thumb;
	@Column(length=150)
	private String addr;//地址
	@Column(length=20)
	private String meanPrice;//人均

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
}
