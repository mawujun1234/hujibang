package com.mawujun.weixin.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.mawujun.message.event.EventType;
import com.mawujun.message.request.RequestMsgType;
import com.mawujun.repository.idEntity.UUIDEntity;
/**
 * 客户发送来的事件,注意
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
@Entity
@Table(name="hjb_event")
public class Event extends UUIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
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
	@Enumerated(EnumType.STRING)
	@Column(length=30)
	private EventType Event;
	
	//扫描带参数二维码事件
	@Column(length=80)
	private String Ticket;//二维码的ticket，可用来换取二维码图片
		
	//主要用于菜单事件和扫描带参数二维码事件
	@Column(length=40)
	private String EventKey;//1:事件KEY值，与自定义菜单接口中KEY值对应，2:或者是一个32位无符号整数，即创建二维码时的二维码scene_id,

	//Event=scancode_push：扫码推事件的事件推送。Event=scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送
	@Column(length=100)
	private String ScanCodeInfo;
	@Column(length=40)
	private String ScanType;
	@Column(length=140)
	private String ScanResult;
	
	//Event=pic_sysphoto：弹出系统拍照发图的事件推送。Event=pic_photo_or_album：弹出拍照或者相册发图的事件推送。Event=pic_weixin：弹出微信相册发图器的事件推送
	@Column(length=140)
	private String SendPicsInfo;
	private Integer Count;
	@Column(length=100)
	private String PicList;//图片列表
	@Column(length=100)
	private String PicMd5Sum;
	
	//Event=location_select：弹出地理位置选择器的事件推送,这个是主动发送的，下面这个地理位置是自动发送的
	@Column(length=140)
	private String SendLocationInfo;
	@Column(length=40)
	private String Location_X;
	@Column(length=40)
	private String Location_Y;
	@Column(length=40)
	private String Scale;
	@Column(length=100)
	private String Label;
	@Column(length=100)
	private String Poiname;
	
	//Event=LOCATION：上报地理位置事件,用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，
	//公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
	@Column(length=40)
	private String Latitude;
	@Column(length=40)
	private String Longitude;
	@Column(length=40,name="Precision_c")
	private String Precision;
	
	
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
	public EventType getEvent() {
		return Event;
	}
	public void setEvent(EventType event) {
		Event = event;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getScanCodeInfo() {
		return ScanCodeInfo;
	}
	public void setScanCodeInfo(String scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	public String getSendPicsInfo() {
		return SendPicsInfo;
	}
	public void setSendPicsInfo(String sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}
	public Integer getCount() {
		return Count;
	}
	public void setCount(Integer count) {
		Count = count;
	}
	public String getPicList() {
		return PicList;
	}
	public void setPicList(String picList) {
		PicList = picList;
	}
	public String getPicMd5Sum() {
		return PicMd5Sum;
	}
	public void setPicMd5Sum(String picMd5Sum) {
		PicMd5Sum = picMd5Sum;
	}
	public String getSendLocationInfo() {
		return SendLocationInfo;
	}
	public void setSendLocationInfo(String sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
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
	public String getPoiname() {
		return Poiname;
	}
	public void setPoiname(String poiname) {
		Poiname = poiname;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	
	
	
	
}
