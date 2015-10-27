package com.mawujun.dianping.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProviceService extends AbstractService<Provice, String> {

	@Autowired
	private ProviceRepository proviceRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public ProviceRepository getRepository() {
		return proviceRepository;
	}


	
}
