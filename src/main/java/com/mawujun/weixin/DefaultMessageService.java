package com.mawujun.weixin;

import com.mawujun.controller.spring.SpringContextHolder;
import com.mawujun.messge.service.IRequestProcess;
import com.mawujun.messge.service.IResponseProcess;
import com.mawujun.messge.service.MessageService;

public class DefaultMessageService extends MessageService {

	@Override
	public IRequestProcess getRequestProcess() {
		// TODO Auto-generated method stub
		return SpringContextHolder.getBean(DefaultRequestProcess.class);
	}

	@Override
	public IResponseProcess getResponseProcess() {
		// TODO Auto-generated method stub
		return SpringContextHolder.getBean(DefaultResponseProcess.class);
	}

}