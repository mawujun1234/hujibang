package com.mawujun.weixin;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.SpringHandlerInstantiator;

import com.mawujun.controller.spring.SpringContextHolder;
import com.mawujun.message.servlet.MessageDispatch;
import com.mawujun.messge.context.WeiXinApplicationContext;
import com.mawujun.messge.context.WeiXinConfigInCache;
import com.mawujun.messge.service.AbstractResponseProcess;

public class MessageServlet extends HttpServlet{
	static Logger logger=LogManager.getLogger(WeiXinApplicationContext.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//protected static String config_file_path="weixin.properties";
	public void init() { 
		ServletConfig config = getServletConfig(); 

//		WeiXinConfigInCache weiXinConfig=new WeiXinConfigInCache();
//		weiXinConfig.setToken(config.getInitParameter("token"));
//		weiXinConfig.setAppid(config.getInitParameter("appid"));
//		weiXinConfig.setAppsecret(config.getInitParameter("appsecret"));
//		WeiXinApplicationContext.setWeiXinConfig(weiXinConfig);
		
		WeiXinConfigInCache weiXinConfig=new WeiXinConfigInCache();
		weiXinConfig.setToken("mawujun1234");
		weiXinConfig.setAppid("wxc57f66afafe529c2");
		weiXinConfig.setAppsecret("b0fcc1ff54ed693eecde7ce51f68040d");
		WeiXinApplicationContext.setWeiXinConfig(weiXinConfig);

		
		WeiXinApplicationContext.setResponseProcess(SpringContextHolder.getBean(DefaultResponseProcess.class));
		
//		try {
//			Class clazz = Class.forName(config.getInitParameter("responseProcess"));
//			AbstractResponseProcess responseProcess = (AbstractResponseProcess) clazz.newInstance();
//			WeiXinApplicationContext.setResponseProcess(responseProcess);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//			logger.error(e);
//			throw new RuntimeException("初始化发生失败",e);
//		} catch (InstantiationException e) {
//			logger.error(e);
//			throw new RuntimeException("初始化发生失败",e);
//		} catch (IllegalAccessException e) {
//			logger.error(e);
//			throw new RuntimeException("初始化发生失败",e);
//		}
		
		
		
		
//		WeiXinConfigInCache weiXinConfig=new WeiXinConfigInCache();
//		weiXinConfig.setToken("mawujun1234");
//		weiXinConfig.setAppid("wxc57f66afafe529c2");
//		weiXinConfig.setAppsecret("b0fcc1ff54ed693eecde7ce51f68040d");
//		WeiXinApplicationContext.setWeiXinConfig(weiXinConfig);
//		
//		WeiXinApplicationContext.setResponseProcess(responseProcess);
		

	} 
	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDispatch.doGet(request, response);
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDispatch.doPost(request, response);
	}
}
