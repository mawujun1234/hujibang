package com.mawujun.dianping.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.IdEntity;

/**
 * 城市
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
@Entity
@Table(name="hjb_city")
public class City implements IdEntity<String> {
	@Id
	private String id;
	@Column(length=30)
	private String name;//中文名称  天津
	
	private String pinyin;//天津 tianjin
	
	private String simple_pinyin;//天津 简写 TJ
	
	private String urlpath;// 类似 /path
	
	private String provice_id;//所属的省份

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getSimple_pinyin() {
		return simple_pinyin;
	}

	public void setSimple_pinyin(String simple_pinyin) {
		this.simple_pinyin = simple_pinyin;
	}



	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}

	public String getProvice_id() {
		return provice_id;
	}

	public void setProvice_id(String provice_id) {
		this.provice_id = provice_id;
	}

	public String getUrlpath() {
		return urlpath;
	}

}
