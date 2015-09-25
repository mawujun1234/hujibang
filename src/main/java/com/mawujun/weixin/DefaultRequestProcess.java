package com.mawujun.weixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mawujun.message.event.LocationEvent;
import com.mawujun.message.event.MenuClickViewEvent;
import com.mawujun.message.event.MenuLocationEvent;
import com.mawujun.message.event.MenuPicEvent;
import com.mawujun.message.event.MenuScancodeEvent;
import com.mawujun.message.event.QRCodeEvent;
import com.mawujun.message.event.SubscribeEvent;
import com.mawujun.message.request.ImageMessage;
import com.mawujun.message.request.LinkMessage;
import com.mawujun.message.request.LocationMessage;
import com.mawujun.message.request.ShortvideoMessage;
import com.mawujun.message.request.TextMessage;
import com.mawujun.message.request.VideoMessage;
import com.mawujun.message.request.VoiceMessage;
import com.mawujun.message.response.BaseMessage;
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
	public BaseMessage process_CLICK(MenuClickViewEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_VIEW(MenuClickViewEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_scancode_push(MenuScancodeEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_scancode_waitmsg(MenuScancodeEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_pic_sysphoto(MenuPicEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_pic_photo_or_album(MenuPicEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_pic_weixin(MenuPicEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMessage process_location_select(MenuLocationEvent message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(LocationEvent message) {
		// TODO Auto-generated method stub
		
	}


	
}
