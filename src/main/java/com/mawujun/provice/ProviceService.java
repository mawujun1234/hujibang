package com.mawujun.provice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.provice.Provice;
import com.mawujun.provice.ProviceRepository;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProviceService extends AbstractService<Provice, Integer>{

	@Autowired
	private ProviceRepository proviceRepository;
	
	@Override
	public ProviceRepository getRepository() {
		return proviceRepository;
	}

}
