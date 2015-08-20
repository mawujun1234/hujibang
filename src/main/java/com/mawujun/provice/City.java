package com.mawujun.provice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.AutoIdEntity;

@Entity
@Table(name="hjb_city")
public class City extends AutoIdEntity {
	@Column(length=20)
	private String name;
	
	private Integer provice_id;//省份id

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvice_id() {
		return provice_id;
	}

	public void setProvice_id(Integer provice_id) {
		this.provice_id = provice_id;
	}
}
