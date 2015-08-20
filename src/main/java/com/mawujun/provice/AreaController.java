package com.mawujun.provice;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mawujun.crawler.AreaCrawler;

@Controller
public class AreaController {
	
	@RequestMapping("/area/areaList.do")
	public void arealist(HttpServletRequest request,HttpServletResponse response,String cityCode) throws IOException{
//		String contextPath=request.getSession().getServletContext().getRealPath("/");
//		String filepath=contextPath+"db"+File.separator+"area"+File.separator+cityCode+".js";
//		String content=FileUtils.readFileToString(new File(filepath), "UTF-8");
		
		response.getWriter().write(AreaCrawler.get_areaList(cityCode));
		response.getWriter().close();
	}
	
	@RequestMapping("/area/shopList.do")
	public void shopList(HttpServletRequest request,HttpServletResponse response,String cityCode,String areaId,String lat,String lng,String page,String pageSize,String keyValue) throws IOException{
//		String contextPath=request.getSession().getServletContext().getRealPath("/");
//		String filepath=contextPath+"db"+File.separator+"area"+File.separator+"shop"+File.separator+cityCode+".js";
//		String content=FileUtils.readFileToString(new File(filepath), "UTF-8");
		
		response.getWriter().write(AreaCrawler.get_shopList(cityCode,areaId,lat,lng,page,pageSize,keyValue));
		response.getWriter().close();
	}
	
	@RequestMapping("/shop/queryShopDetail.do")
	public void queryShopDetail(HttpServletRequest request,HttpServletResponse response,String cityCode,String shopId,String lat,String lng) throws IOException{
//		String contextPath=request.getSession().getServletContext().getRealPath("/");
//		String filepath=contextPath+"db"+File.separator+"area"+File.separator+"shop"+File.separator+cityCode+".js";
//		String content=FileUtils.readFileToString(new File(filepath), "UTF-8");
		
		response.getWriter().write(AreaCrawler.queryShopDetail(cityCode,shopId,lat,lng));
		response.getWriter().close();
	}

}
