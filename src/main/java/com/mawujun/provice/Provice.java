package com.mawujun.provice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.AutoIdEntity;


@Entity
@Table(name="hjb_provice")
public class Provice extends AutoIdEntity {
	@Column(length=20)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
