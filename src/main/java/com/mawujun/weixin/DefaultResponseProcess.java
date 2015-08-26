package com.mawujun.weixin;

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
import com.mawujun.messge.service.IResponseProcess;

@Service
public class DefaultResponseProcess implements IResponseProcess {

	public BaseMessage process(TextMessage message) {
		// TODO Auto-generated method stub
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(ImageMessage message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(VoiceMessage message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(VideoMessage message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(ShortvideoMessage message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(LocationMessage message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(LinkMessage message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(QRCodeEvent message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(SubscribeEvent message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("谢谢关注");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(MenuEvent message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

	public BaseMessage process(LocationEvent message) {
		com.mawujun.message.response.TextMessage result=new com.mawujun.message.response.TextMessage();
		result.setContent("热烈欢迎");
		result.setFromUserName(message.getToUserName());
		result.setToUserName(message.getFromUserName());
		result.setCreateTime(12345678);
		
		return result;
	}

}
