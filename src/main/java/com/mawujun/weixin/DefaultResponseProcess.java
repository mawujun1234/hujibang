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
import com.mawujun.message.response.BaseMessage;
import com.mawujun.messge.context.WeiXinApplicationContext;
import com.mawujun.messge.service.AbstractResponseProcess;

@Service
public class DefaultResponseProcess extends AbstractResponseProcess {

	static Logger logger=LogManager.getLogger(DefaultResponseProcess.class);

	@Override
	public BaseMessage process(TextMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(ImageMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(VoiceMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(VideoMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(ShortvideoMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(LocationMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(LinkMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_subscribe(QRCodeEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_SCAN(QRCodeEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_subscribe(SubscribeEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_unsubscribe(SubscribeEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_CLICK(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_VIEW(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_scancode_push(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_scancode_waitmsg(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_pic_sysphoto(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_pic_photo_or_album(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_pic_weixin(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process_location_select(MenuEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseMessage process(LocationEvent message) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public BaseMessage process(ImageMessage message) {
//		System.out.println("ImageMessage======================================"+message.getMediaId());
//		//return getAutoReplyResponse(message);
//		com.mawujun.message.response.ImageMessage result=new com.mawujun.message.response.ImageMessage();
//		result.setImage(message.getMediaId());
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());	
//		return result;
//		
//	}
//
//	public BaseMessage process(VoiceMessage message) {
//		//return getAutoReplyResponse(message);
//		com.mawujun.message.response.VoiceMessage result=new com.mawujun.message.response.VoiceMessage();
//		System.out.println("VoiceMessage======================================"+message.getMediaId());
//		result.setVoice(message.getMediaId());
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());
//		return result;
//	}
//
//	public BaseMessage process(VideoMessage message) {
//		System.out.println("VideoMessage======================================"+message.getMediaId());
//		//return getAutoReplyResponse(message);
//		com.mawujun.message.response.VideoMessage result=new com.mawujun.message.response.VideoMessage();
//		result.setVideo(message.getMediaId(),"","");
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());	
//		return result;
//	}
//
//	public BaseMessage process(ShortvideoMessage message) {
//		//return getAutoReplyResponse(message);
//		com.mawujun.message.response.VoiceMessage result=new com.mawujun.message.response.VoiceMessage();
//		System.out.println("ShortvideoMessage======================================"+message.getMediaId());
//		result.setVoice(message.getMediaId());
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());
//		return result;
//	}

	

}
