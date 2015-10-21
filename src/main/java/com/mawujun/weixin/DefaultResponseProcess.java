package com.mawujun.weixin;

import java.io.File;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mawujun.bos.BosUtils;
import com.mawujun.message.event.BaseEvent;
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
import com.mawujun.message.request.RequestMsgType;
import com.mawujun.message.request.ShortvideoMessage;
import com.mawujun.message.request.TextMessage;
import com.mawujun.message.request.VideoMessage;
import com.mawujun.message.request.VoiceMessage;
import com.mawujun.message.response.BaseMessageOut;
import com.mawujun.message.response.TextMessageOut;
import com.mawujun.message.utils.MessageUtils;
import com.mawujun.messge.context.WeiXinApplicationContext;
import com.mawujun.messge.service.AbstractResponseProcess;
import com.mawujun.utils.bean.BeanUtils;
import com.mawujun.weixin.message.Event;
import com.mawujun.weixin.message.EventService;
import com.mawujun.weixin.message.RequestMessage;
import com.mawujun.weixin.message.RequestMessageService;

@Service
@Transactional
public class DefaultResponseProcess extends AbstractResponseProcess {
	@Autowired
	private RequestMessageService requestMessageService;
	@Autowired
	private EventService eventService;
	
	private String bucketName="hujibang";

	static Logger logger=LogManager.getLogger(DefaultResponseProcess.class);

	/**
	 * 返回简单的响应消息
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param message
	 * @return
	 */
	public BaseMessageOut returnSimaleReplayInfo(com.mawujun.message.request.BaseMessage message) {
		//return getAutoReplyResponse(message);
		com.mawujun.message.response.TextMessageOut messageOut=new com.mawujun.message.response.TextMessageOut();
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());
		MessageUtils.copyFromToUserName(message, messageOut);
		messageOut.setContent("亲，您好！正在为您转接到客服，请稍候！mo-微笑");
		return messageOut;
	}
	/**
	 * 档关注的时候发送的响应信息
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param message
	 * @return
	 */
	public BaseMessageOut getSubscribeReply(BaseEvent message) {
		//return getAutoReplyResponse(message);
		TextMessageOut messageOut=new TextMessageOut();
		MessageUtils.copyFromToUserName(message, messageOut);
		messageOut.setContent("亲，您好！感谢您来到护脊邦在线咨询平台，我们能帮您就近找到最适合您的治疗方案，也可以让医生或理疗机构更好的为您服务！mo-微笑");
		return messageOut;
	}
	/**
	 * 保存传递过来的消息
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param message
	 */
	protected void saveMessage(com.mawujun.message.request.BaseMessage message) {
		RequestMessage requestMessage=BeanUtils.copyOrCast(message, RequestMessage.class);
		if(message.getMsgType()==RequestMsgType.image) {
//			String[] result=WeiXinApplicationContext.get_material_temp_content(requestMessage.getMediaId());
//			//转存到百度的BOS中
//			File file=new File(result[1]);
//			
//			String objectKey= WeiXinApplicationContext.getMedia_image()+result[0];
//			BosUtils.putObject(bucketName,objectKey, file);
//			if(file!=null && file.exists()){
//	    		file.delete();
//	    	}
//			
//			String baidu_url=BosUtils.generatePresignedUrl(bucketName, objectKey, -1);
//			requestMessage.setBaidu_objectKey(objectKey);
//			requestMessage.setBaidu_mediaurl(baidu_url);
			sendMedia_baidu(requestMessage,WeiXinApplicationContext.getMedia_image());
		} else if(message.getMsgType()==RequestMsgType.voice){
			sendMedia_baidu(requestMessage,WeiXinApplicationContext.getMedia_voice());
		} else if(message.getMsgType()==RequestMsgType.video){
			sendMedia_baidu(requestMessage,WeiXinApplicationContext.getMedia_video());
		} else if(message.getMsgType()==RequestMsgType.shortvideo){
			sendMedia_baidu(requestMessage,WeiXinApplicationContext.getMedia_shortvideo());
		}
		
		requestMessageService.create(requestMessage);
	}
	
	protected void saveEvent(BaseEvent message) {
		Event event=BeanUtils.copyOrCast(message, Event.class);
		eventService.create(event);
	}
	/**
	 * 把素材数据保存到百度的bos中去
	 * @author mawujun email:160649888@163.com qq:16064988
	 */
	protected void sendMedia_baidu(RequestMessage requestMessage,String media_prefix){
		String[] result=WeiXinApplicationContext.get_material_temp_content(requestMessage.getMediaId());
		//转存到百度的BOS中
		File file=new File(result[1]);
		
		String objectKey= media_prefix+result[0];
		BosUtils.putObject(bucketName,objectKey, file);
		if(file!=null && file.exists()){
    		file.delete();
    	}
		
		String baidu_url=BosUtils.generatePresignedUrl(bucketName, objectKey, -1);
		requestMessage.setBaidu_objectKey(objectKey);
		requestMessage.setBaidu_mediaurl(baidu_url);
	}
	
	@Override
	public BaseMessageOut process(TextMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
	}
	@Override
	public BaseMessageOut process(ImageMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
//		ImageMessage response=new ImageMessage();
//		response.setFromUserName(message.getToUserName());
//		response.setToUserName(message.getFromUserName());
//		response.setCreateTime(new Date());
//		response.setImage(MediaId);
		
	}
	@Override
	public BaseMessageOut process(VoiceMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
	}
	@Override
	public BaseMessageOut process(VideoMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
	}
	@Override
	public BaseMessageOut process(ShortvideoMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
	}
	@Override
	public BaseMessageOut process(LocationMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
	}
	@Override
	public BaseMessageOut process(LinkMessage message) {
		this.saveMessage(message);
		// TODO Auto-generated method stub
		return returnSimaleReplayInfo(message);
	}
	
	//===========================================================================================
	@Override
	public BaseMessageOut process_subscribe(QRCodeEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_SCAN(QRCodeEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_subscribe(SubscribeEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return getSubscribeReply(message);
	}
	@Override
	public BaseMessageOut process_unsubscribe(SubscribeEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_CLICK(MenuClickViewEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_VIEW(MenuClickViewEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}

	@Override
	public BaseMessageOut process(LocationEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_scancode_push(MenuScancodeEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_scancode_waitmsg(MenuScancodeEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_pic_sysphoto(MenuPicEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_pic_photo_or_album(MenuPicEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_pic_weixin(MenuPicEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	@Override
	public BaseMessageOut process_location_select(MenuLocationEvent message) {
		// TODO Auto-generated method stub
		saveEvent(message);
		return null;
	}
	
//	public BaseMessage process(ImageMessage message) {
//		System.out.println("ImageMessage======================================"+message.getMediaId());
//		//return getAutoReplyResponse(message);
//		ImageMessage result=new ImageMessage();
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
//		VoiceMessage result=new VoiceMessage();
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
//		VideoMessage result=new VideoMessage();
//		result.setVideo(message.getMediaId(),"","");
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());	
//		return result;
//	}
//
//	public BaseMessage process(ShortvideoMessage message) {
//		//return getAutoReplyResponse(message);
//		VoiceMessage result=new VoiceMessage();
//		System.out.println("ShortvideoMessage======================================"+message.getMediaId());
//		result.setVoice(message.getMediaId());
//		result.setFromUserName(message.getToUserName());
//		result.setToUserName(message.getFromUserName());
//		result.setCreateTime(new Date());
//		return result;
//	}

	

}
