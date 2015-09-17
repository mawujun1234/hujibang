package com.mawujun.crawler.dianping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//
public class GetShop {
	static String url="http://www.dianping.com/search/category/2/30/g141";
	public static void main(String[] args) throws IOException {
		//get_areaList();
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httppost = new HttpGet(url);
		//httppost.setConfig(config); 

		httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httppost.addHeader("Accept-Encoding", "gzip, deflate");
		httppost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httppost.addHeader("Connection", "keep-alive");
		httppost.addHeader("Host", "www.dianping.com");
		//httppost.addHeader("Referer", "http://www.dianping.com/beijing");
		//httppost.addHeader("X-Requested-With", "XMLHttpRequest");
		//httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
		

//		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//
//		nvps.add(new BasicNameValuePair("cityCode", cityCode));
//		httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
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
		 System.out.println(builder.toString());
	}
	
	public static void get_areaList() throws IOException {
		Document doc = Jsoup.connect(url).get();
		//获取到的是门店的list
		Elements lies = doc.select("#shop-all-list li");
		for (Element link : lies) {
			  //String linkHref = link.attr("href");
			  //String linkText = link.text();
			Element pic=link.getElementsByClass("pic").get(0);
			String id=pic.child(0).attr("href");
			String thumb=pic.child(0).child(0).attr("src");
			String name=pic.child(0).child(0).attr("title");
			
			System.out.println(id);
			System.out.println(thumb);
			System.out.println(name);
			
		}
		
		
	}
}
