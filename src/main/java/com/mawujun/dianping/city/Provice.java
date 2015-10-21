package com.mawujun.dianping.city;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mawujun.repository.idEntity.IdEntity;

/**
 * 华南，华北区域等数据
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
@Entity
@Table(name="hjb_Provice")
public class Provice implements IdEntity<String> {
	@Id
	private String id;
	@Column(length=30)
	private String name;
	@Transient
	List<City> cityes=new ArrayList<City>();
	
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
	
	public void addCitye(City city) {
		this.cityes.add(city);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCityes() {
		return cityes;
	}

	public void setCityes(List<City> cityes) {
		this.cityes = cityes;
	}

}
