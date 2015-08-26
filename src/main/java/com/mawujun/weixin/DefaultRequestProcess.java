package com.mawujun.weixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mawujun.message.event.LocationEvent;
import com.mawujun.message.event.MenuEvent;
import com.mawujun.message.event.QRCodeEvent;
import com.mawujun.message.event.SubscribeEvent;
import com.mawujun.message.request.ImageMessage;
import com.mawujun.message.request.LinkMessage;
import com.mawujun.message.request.LocationMessage;
import com.mawujun.message.request.ShortvideoMessage;
import com.mawujun.message.request.TextMessage;
import com.mawujun.message.request.VideoMessage;
import com.mawujun.message.request.VoiceMessage;
import com.mawujun.messge.service.IRequestProcess;

@Service
public class DefaultRequestProcess implements IRequestProcess {
	Logger logger=LogManager.getLogger(DefaultRequestProcess.class);

	@Override
	public void process(TextMessage message) {
		// TODO Auto-generated method stub
		logger.debug(message);
		System.out.println("=================="+message);
	}

	@Override
	public void process(ImageMessage message) {
		// TODO Auto-generated method stub
		logger.debug(message);
	}

	@Override
	public void process(VoiceMessage message) {
		// TODO Auto-generated method stub
		logger.debug(message);
	}

	@Override
	public void process(VideoMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(ShortvideoMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(LocationMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(LinkMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(QRCodeEvent message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(SubscribeEvent message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(MenuEvent message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process(LocationEvent message) {
		// TODO Auto-generated method stub

	}

}
