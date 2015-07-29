package com.mawujun.crawler;

import java.io.BufferedReader;
import java.io.File;
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
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

	private static String area_path="E:\\eclipse\\workspace\\hujibang\\src\\main\\webapp\\db\\area";
	private static String shop_path="E:\\eclipse\\workspace\\hujibang\\src\\main\\webapp\\db\\area\\shop";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//execu_areaList();	
		exec_shopList();
	}
	
	public static String get_areaList(String cityCode) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://as.51jlt.com/area/areaList.php");
		//httppost.setConfig(config); 

		httppost.addHeader("Accept", "application/json;");
		httppost.addHeader("Referer", "http://as.51jlt.com/shop/toShop.php");
		httppost.addHeader("X-Requested-With", "XMLHttpRequest");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
		

		List <NameValuePair> nvps = new ArrayList <NameValuePair>();

		nvps.add(new BasicNameValuePair("cityCode", cityCode));
		httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		CloseableHttpResponse response = httpclient.execute(httppost);
		
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
		return builder.toString();
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

			String cityCode=dd.text();//
			//cityname="北京市";
			System.out.println(cityCode);
			
			 File file=new File(area_path+"\\"+cityCode+".js");
		       FileUtils.write(file, get_areaList(cityCode),"UTF-8");
			//return;
		}
		
	}
	
	public static String get_shopList(String cityCode,String areaId,String lat,String lng,String page,String pageSize) throws IOException {
		//cityCode="北京市";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://as.51jlt.com/shop/shopList.php");
		
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		nvps.add(new BasicNameValuePair("areaId", areaId));
		nvps.add(new BasicNameValuePair("autoSearchType", "0"));
		nvps.add(new BasicNameValuePair("cityCode", cityCode));
		nvps.add(new BasicNameValuePair("keyValue", ""));
		nvps.add(new BasicNameValuePair("lat", lat));
		nvps.add(new BasicNameValuePair("lng", lng));
		nvps.add(new BasicNameValuePair("page", page));
		nvps.add(new BasicNameValuePair("pageSize", pageSize));
		nvps.add(new BasicNameValuePair("screenType", "0"));
		httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		CloseableHttpResponse response = httpclient.execute(httppost);
		
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
	        System.out.println(builder);
	        
	      
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return builder.toString();
	}
	public static void exec_shopList() throws  IOException{
		//获取所有的市
//		 LineIterator it = FileUtils.lineIterator(file, "UTF-8");
//		 try {
//		   while (it.hasNext()) {
//		     String line = it.nextLine();
//		     /// do something with line
//		   }
//		 } finally {
//		   LineIterator.closeQuietly(iterator);
//		 }
		
		Collection<File> files=FileUtils.listFiles(new File(area_path), new String[]{"js"}, false);

		for(File file:files){	
			String cityCode=file.getName().split("\\.")[0];
			File shopfile=new File(shop_path+"\\"+cityCode+".js");
		    //FileUtils.write(shopfile, get_shopList(cityCode),"UTF-8");
		//return;
		}
	}
	
	
	
	public static String queryShopDetail(String cityCode,String shopId,String lat,String lng) throws IOException {
		//cityCode="北京市";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://as.51jlt.com/shop/queryShopDetail.php");
		
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		nvps.add(new BasicNameValuePair("cityCode", cityCode));
		nvps.add(new BasicNameValuePair("shopId", shopId));
		nvps.add(new BasicNameValuePair("lat", lat));
		nvps.add(new BasicNameValuePair("lng", lng));

		httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		CloseableHttpResponse response = httpclient.execute(httppost);
		
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
	        System.out.println(builder);
	        
	      
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return builder.toString();
	}
	
}
