package com.mawujun.crawler;

import java.util.Date;

public class Shopinfo {
	private String shopId;
	private String parentId;
	private String brandId;
	private String name;//店面名称
	private String listImageUrl;//图片地址
	private String type;//默认是6
	private String proCode;//可能是省
	private String cityCode;//市
	private String areaCode;//区域
	private String businessPhone;
	private String businessMobile;
	private String businessHours;//营业时间
	private String address;//地址信息
	private String location;
	private String longitude;//经度
	private String latitude;//维度
	private String distance;//距离
	private String longth;//500+公里,距离定位出来的距离多少远
	
	private Date createTime;
	private Date updateTime;
	
}
