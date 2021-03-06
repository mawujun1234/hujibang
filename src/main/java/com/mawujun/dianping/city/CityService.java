package com.mawujun.dianping.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dianping.city.City;
import com.mawujun.dianping.city.CityRepository;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class CityService extends AbstractService<City, String>{

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public CityRepository getRepository() {
		return cityRepository;
	}

}
