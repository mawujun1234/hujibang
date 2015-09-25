package com.mawujun.weixin.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.weixin.message.Event;
import com.mawujun.weixin.message.EventRepository;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class EventService extends AbstractService<Event, String>{

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public EventRepository getRepository() {
		return eventRepository;
	}

}
