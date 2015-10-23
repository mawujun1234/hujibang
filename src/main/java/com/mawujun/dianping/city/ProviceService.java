package com.mawujun.dianping.city;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.crawler.dianping.GetProviceCity;
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

	
	public void init() throws IOException{
		proviceRepository.deleteAll();
		cityRepository.deleteAll();
		List<Provice> provices=GetProviceCity.getProviceCity();
		
		for(Provice provice:provices){
			proviceRepository.create(provice);
			
			
			for(City city:provice.getCityes()){
				cityRepository.create(city);
			}
		}
		
	}
	
	public void saveShop(City city) throws IOException{
		
	}
	
	
}
