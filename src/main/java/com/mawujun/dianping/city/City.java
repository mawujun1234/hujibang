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
//@Entity
//@Table(name="hjb_city")
public class City implements IdEntity<Integer> {
	@Id
	private Integer id;
	@Column(length=30)
	private String name;
	
	private Integer region_id;

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

}
