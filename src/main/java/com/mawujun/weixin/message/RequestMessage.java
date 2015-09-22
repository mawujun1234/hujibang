package com.mawujun.weixin.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.mawujun.message.request.RequestMsgType;
import com.mawujun.repository.idEntity.UUIDEntity;
/**
 * 客户发送来的消息保存
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
@Entity
@Table(name="hjb_requestmessage")
public class RequestMessage extends UUIDEntity {
	//消息id，64位整型
	@Column(length=70)
	private String MsgId;
		
	// 开发者微信号，公众号的微信号
	@Column(length=30)
	private String ToUserName;
	// 发送方帐号（一个OpenID）
	@Column(length=30)
	private String FromUserName;
	// 消息创建时间 （整型）
	private Long CreateTime;
	//消息类型，text，image,voice,video,shortvideo,location.link等等
	@Enumerated(EnumType.STRING)
	@Column(length=30)
	private RequestMsgType MsgType;
	
	//============================
	@Column(length=300)
	private String Content;//文本消息内容
	
	
	//---------------------------------------
	@Column(length=100)
	private String MediaId;//image，voice,video,shortvideo消息媒体id，可以调用多媒体文件下载接口拉取数据。
	@Column(length=200)
	private String savePath;//相对地址，image，voice,video,shortvideo发送过来的话，只有3天有效期，所以要先保存，这是本地地址
	@Column(length=200)
	private String savePath_abstract;//绝对路径,image，voice,video,shortvideo发送过来的话，只有3天有效期，所以要先保存，这是本地地址
	
	//========================================
	@Column(length=250)
	private String PicUrl;//image图片链接
	//=====================
	@Column(length=10)
	private String Format;//voice语音格式	
	//======================================视video,shortvideo频消息
	@Column(length=100)
	private String ThumbMediaId;//video,shortvideo消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	
	
	//===============================地理消息
	@Column(length=30)
	private String Location_X;//地理位置维度
	@Column(length=30)
	private String Location_Y;//地理位置经度
	@Column(length=30)
	private String Scale;//地图缩放大小
	@Column(length=60)
	private String Label;//地理位置信息
	
	//=====================链接消息
	@Column(length=60)
	private String Title;//消息标题
	@Column(length=300)
	private String Description;//消息描述
	@Column(length=100)
	private String Url;//消息链接
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public RequestMsgType getMsgType() {
		return MsgType;
	}
	public void setMsgType(RequestMsgType msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getSavePath_abstract() {
		return savePath_abstract;
	}
	public void setSavePath_abstract(String savePath_abstract) {
		this.savePath_abstract = savePath_abstract;
	}
	
	
	
	
	
}
