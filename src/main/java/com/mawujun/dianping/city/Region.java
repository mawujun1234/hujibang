package com.mawujun.dianping.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.repository.idEntity.IdEntity;

/**
 * 华南，华北区域等数据
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
@Entity
@Table(name="hjb_region")
public class Region implements IdEntity<Integer> {
	@Id
	private Integer id;
	@Column(length=30)
	private String name;
	
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

}
