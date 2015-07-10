package com.mawujun.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬去城市的数据
 * @author mawujun email:16064988@qq.com qq:16064988
 *
 */
public class AreaCrawler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//execu_areaList();
		
		exec_shopList("北京市");
	}

	/**
	 * 获取市  区域数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @throws IOException
	 */
	public static void execu_areaList() throws IOException {	
		String url="http://as.51jlt.com/shop/toShop.php";
		Document doc = Jsoup.connect(url).get();
		Elements dds = doc.select("div#container div.change-city ul li dl dd");
		for (Element dd : dds) {

			String cityname=dd.text();//
			System.out.println(cityname);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://as.51jlt.com/area/areaList.php");
			//httppost.setConfig(config); 
	
			httppost.addHeader("Accept", "application/json;");
			httppost.addHeader("Referer", "http://as.51jlt.com/shop/toShop.php");
			httppost.addHeader("X-Requested-With", "XMLHttpRequest");
			httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
			

			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	
			nvps.add(new BasicNameValuePair("cityCode", cityname));
			httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httppost);
			
			
			try {
				//System.out.println(response.getStatusLine());
			    HttpEntity entity = response.getEntity();
			    //InputStream inputStream=entity.getContent();
			    
			    ContentType contentType = ContentType.getOrDefault(entity);
		        Charset charset = contentType.getCharset();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		        
		        StringBuilder builder=new StringBuilder();
		        String s;
		        while((s=reader.readLine())!=null && s.length()!=0){
		          builder.append(s);
		        }
		        System.out.println(builder);
		        
		        
			    // do something useful with the response body
			    // and ensure it is fully consumed
			    EntityUtils.consume(entity);
			} finally {
			    response.close();
			}
			return;
		}
		
	}
	
	public static void exec_shopList(String cityCode) throws  IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://as.51jlt.com/shop/shopList.php");
		
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		nvps.add(new BasicNameValuePair("areaId", ""));
		nvps.add(new BasicNameValuePair("autoSearchType", "0"));
		nvps.add(new BasicNameValuePair("cityCode", cityCode));
		//nvps.add(new BasicNameValuePair("keyValue", ));
		//nvps.add(new BasicNameValuePair("lat", ));
		//nvps.add(new BasicNameValuePair("lng", ));
		nvps.add(new BasicNameValuePair("page", "0"));
		nvps.add(new BasicNameValuePair("pageSize", "100"));
		nvps.add(new BasicNameValuePair("screenType", "0"));
		httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		CloseableHttpResponse response = httpclient.execute(httppost);
		
		
		try {
			//System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    //InputStream inputStream=entity.getContent();
		    
		    ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = contentType.getCharset();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
	        
	        StringBuilder builder=new StringBuilder();
	        String s;
	        while((s=reader.readLine())!=null && s.length()!=0){
	          builder.append(s);
	        }
	        System.out.println(builder);
	        
	        
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
	}
	
}
