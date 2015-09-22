package com.mawujun.weixin;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.mawujun.message.response.TextMessage;
import com.mawujun.message.utils.MessageUtils;

/**
 * 模拟微信发出请求，往服务器发出请求
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
public class RequestProcessTest {
	
	public static void main(String[] args) throws ClientProtocolException, IOException{
		RequestProcessTest aa=new RequestProcessTest();
		
		String textMsg_xml="<xml>"
				+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				+ "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[this 中文is a test]]></Content>"
				+ "<MsgId>1234567890123456</MsgId>"
				+ "</xml>";
		TextMessage textMessage=aa.post(textMsg_xml,TextMessage.class);
		System.out.println(textMessage.getContent());
	}
	//@Test
	public void get() throws UnsupportedOperationException, IOException {
		
		//模拟http进行测试
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://localhost:8084/messageServlet?signature=82492507cfbefaa71409b83546603a6f60f96896"
				+ "&timestamp=1440554054&nonce=117859536&echostr=1111");
		//httppost.setConfig(config); 

//		httppost.addHeader("Accept", "application/json;");
//		httppost.addHeader("Referer", "http://as.51jlt.com/shop/toShop.php");
//		httppost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpget.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");

		CloseableHttpResponse response = httpclient.execute(httpget);
		
		StringBuilder builder=new StringBuilder();
		try {
			//System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    //InputStream inputStream=entity.getContent();
		    
		    ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = contentType.getCharset();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
	        
	       
	        String s;
	        while((s=reader.readLine())!=null && s.length()!=0){
	          builder.append(s);
	        }
	        //System.out.println(builder);
	        
	       
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		assertEquals("1111", builder.toString());
	}

	//@Test
	public <T> T post(String reqXml,Class<T> clazz) throws ClientProtocolException, IOException  {

		//模拟http进行测试
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8084/messageServlet?signature=82492507cfbefaa71409b83546603a6f60f96896"
				+ "&timestamp=1440554054&nonce=117859536&echostr=1111");

		//httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		//httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");

		
		
				
		StringEntity reqEntity=new StringEntity(reqXml,ContentType.create("application/xml", "UTF-8"));
         
		httppost.setEntity(reqEntity);
		
		
		CloseableHttpResponse response = httpclient.execute(httppost);
		
		StringBuilder builder=new StringBuilder();
		try {
			//System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    //InputStream inputStream=entity.getContent();
		    
		    //ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = Charset.forName("UTF-8");//contentType.getCharset();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
	        
	       
	        String s;
	        while((s=reader.readLine())!=null && s.length()!=0){
	          builder.append(s);
	        }
	        //System.out.println(builder);
	        
	       
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		System.out.println(builder);
		T t=MessageUtils.xml2Message(builder.toString(), clazz);
		//System.out.println(textMessage.getContent());
		return t;
		//return builder.toString();
	}
}
