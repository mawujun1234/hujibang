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
import com.mawujun.messge.service.AbstractRequestProcess;

@Service
public class DefaultRequestProcess extends AbstractRequestProcess {
	Logger logger=LogManager.getLogger(DefaultRequestProcess.class);

	@Override
	public void process(TextMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(ImageMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(VoiceMessage message) {
		// TODO Auto-generated method stub
		
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
	public void process_subscribe(QRCodeEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_SCAN(QRCodeEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_subscribe(SubscribeEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_unsubscribe(SubscribeEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_CLICK(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_VIEW(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_scancode_push(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_scancode_waitmsg(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_pic_sysphoto(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_pic_photo_or_album(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_pic_weixin(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process_location_select(MenuEvent message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(LocationEvent message) {
		// TODO Auto-generated method stub
		
	}

	
}
