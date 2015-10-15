package com.mawujun.crawler.dianping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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

import com.mawujun.utils.file.FileUtils;

//
public class GetShop {
	static String domain_url="http://www.dianping.com";
	static String url="http://www.dianping.com/search/category/2/30/g141";

	public static void main(String[] args) throws IOException {

		//get_nextpage();
		//getShop(domain_url+"/search/category/2/30/g141");
		
		//测试图片获取
		//getImage("http://www.dianping.com/shop/18009133/photos","?pg=1","d:/aaa");
		//获取某个店铺的评论
		//getReview_more("http://www.dianping.com/shop/18009133/review_more","?pageno=1");
	}
	
	public static void getShop(String url) throws IOException {
		String html=getContent(url);
		Document doc = Jsoup.parse(html);
		
		//获取当前页所有的店铺数据
		Elements lies = doc.select("#shop-all-list ul li");
		for (Element link : lies) {
			
			//Element pic=link.getElementsByClass("pic").get(0);
			//获取缩略图
			Element pic=link.child(0);
			String href=pic.child(0).attr("href");//店铺url地址
			String shop_code=href.substring(href.lastIndexOf('/')+1);
			String thumb=pic.child(0).child(0).attr("data-src");//店铺缩略图
			String name=pic.child(0).child(0).attr("title");//店铺名称
			
			//先创建需要的路径
			FileUtils.createDir("d:/"+shop_code);
			FileUtils.createDir("d:/"+shop_code+"/thumb");//存放缩略图
			FileUtils.createDir("d:/"+shop_code+"/images");//存放原始图
			//http://i2.s2.dpfile.com/pc/e0de1df59cb5aee2a826d57a7ec0fece(249x249)/thumb.jpg
			//缩略图下载过来,按照
			//店铺代码/***.jpg（搜索时显示的图片）,店铺代码/thumb这个目录存放缩略图，店铺代码/image存放原始图
			//并且获取到的第一张作为默认缩略图
			getThumb(thumb,"d:/"+shop_code);
			//获取原始图片和图片所产生的缩略图
			//http://www.dianping.com/shop/18009133/photos
			getImage(domain_url+href+"/photos","?pg=1","d:/"+shop_code);
			
			
			
			//===============================================================
			//评论内容获取
			getReview_more(domain_url+href+"/review_more","?pageno=1");
			
			
			getShopDetailInfo(domain_url+href);
			
//			System.out.println("href:"+href);
//			System.out.println(thumb);
//			System.out.println(name);
			
			//Element txt=link.child(1);
			//Element comment=link.child(2);
			//评论内容，星级(技师，环境，服务评分)，均价，营业时间，电话，地址等等信息都到店铺的详细信息里面去获取，就是前面href中的内容
			//评论地址http://www.dianping.com/shop/4674049/review_more?pageno=1 里面也有分页
			
			
			//门店图片的地址  http://www.dianping.com/shop/4674049/photos
			break;
			
		}
		
		//===================================================================================================
		//获取下一页，如果有下一页就继续获取数据
		Elements nextpages = doc.select("div.page a.next");
		for (Element link : nextpages) {
			  //String linkHref = link.attr("href");
			  //String linkText = link.text();
			String linkHref = link.attr("href");
			//"http://www.dianping.com"+linkHref;下一页的数据
			
			System.out.println(linkHref);
			//getShop(domain_url+ linkHref);
		}
	}

	public static void getReview_more(String url, String page) throws IOException {
		// 获取图片所在网页的所有图片
		String html = getContent(url + page);
		// 获取图片所有的页数，然后进形循环获取
		Document doc = Jsoup.parse(html);
		// 开始获取当前页的所有图片
		Elements comment_list = doc.select(".comment-list ul li");
		for (Element li : comment_list) {
			//获取评论用户的id，图片和昵称
			Elements pices=li.select("div.pic");
			if(pices==null || pices.size()==0){
				continue;
			}
			Element pic=pices.get(0);
			Element J_card=pic.child(0);
			String user_id=J_card.attr("user-id");
			Element img=J_card.child(0);
			String user_img_src=img.attr("src");
			String user_name=img.attr("title");
			
			Element content=li.child(1);
			//获取评论的星级
			Elements comment_rst=content.select("span.rst");
			if(comment_rst!=null && comment_rst.size()>0){
				//查询具体的评价信息
				for(Element rst:comment_rst){
					//分为 技师，环境，服务等几个方面来评价的
					String rst1=rst.ownText();
					String rst1_text=rst.child(0).text();//评价的文字描述
					
					System.out.println(rst1);
					System.out.println(rst1_text);
				}
				
			}
			//获取评论的内容
			Elements comment_txt=content.select("div.J_brief-cont");
			String contentText=comment_txt.get(0).text();
			System.out.println(contentText);

		}

		// 判断是否有下一页，如果有下一页，就继续获取,正在递归获取
		Elements NextPage = doc.select(".Pages .Pages .NextPage");
		if (NextPage != null && NextPage.size() > 0) {
			getReview_more(url, NextPage.get(0).attr("href"));
		}
	}
	//获取第一个缩略图，就是显示在查询的时候的缩略图
	//获取图片的公共方法
	public static void getThumb(String url,String savePath) throws ClientProtocolException, IOException{
		
		String[] thumbes=url.split("/");
		//获取图片后缀
		String imgFileName=thumbes[thumbes.length-1];
		String imgFileName_suffix=imgFileName.substring(imgFileName.indexOf('.')+1);
		String filePath=savePath+"/"+thumbes[thumbes.length-2]+"."+imgFileName_suffix;
		
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		HttpGet httpget = new HttpGet(url);  
		CloseableHttpResponse response = httpclient.execute(httpget);
		
        File storeFile = new File(filePath);  
        FileOutputStream output = new FileOutputStream(storeFile);  
        
        //得到网络资源的字节数组,并写入文件  
        HttpEntity entity = response.getEntity();
        InputStream input = entity.getContent();
        //OutputStream output = new FileOutputStream(new File("D:\\website\\1.gif"));
        IOUtils.copy(input, output);
        output.flush();
        
	}
	

	//获取某个门店拥有的所有缩略图和图片地址
	public static void getImage(String url,String page,String savePath) throws ClientProtocolException, IOException{

		//获取图片所在网页的所有图片
		String html= getContent(url+page);
		//获取图片所有的页数，然后进形循环获取
		Document doc = Jsoup.parse(html);
		//开始获取当前页的所有图片
		Elements J_listes = doc.select(".picture-square .picture-list ul li.J_list div.img a");
		for(Element element:J_listes){
			String thumb_url=element.children().get(0).attr("src");
			getThumb(thumb_url,savePath+"/thumb");
			String img_url=thumb_url.replaceFirst("240c180", "700x700");
			getThumb(img_url,savePath+"/images");
			
			System.out.println(thumb_url);
			System.out.println(img_url);

		}
		
		//判断是否有下一页，如果有下一页，就继续获取,正在递归获取
		Elements NextPage = doc.select(".Pages .Pages .NextPage");
		if(NextPage!=null && NextPage.size()>0){
			getImage(url,NextPage.get(0).attr("href"),savePath);
		}
		

        
	}
	
	//获取店铺的详细信息
	public static void getShopDetailInfo(String url) throws IOException{
		System.out.println(url);
		String html=getContent(url);
		Document doc = Jsoup.parse(html);
		
		Elements basic_info = doc.select("#basic-info");
		for (Element ele : basic_info) {
			//获取评分信息
			Element brief_info=ele.getElementsByClass("brief-info").get(0);
			String star=brief_info.child(0).attr("title");//几星商户，例如四星商户
			Element avg_element=brief_info.child(0).nextElementSibling().nextElementSibling();
			if(!avg_element.nodeName().equalsIgnoreCase("span")){
				avg_element=brief_info.child(0).nextElementSibling().nextElementSibling().nextElementSibling();
			}
			String avg=avg_element.text();//人均消费
			
			Element jishi_star_element=avg_element.nextElementSibling();
			String jishi_star=jishi_star_element.text();//技师 分数
			Element huanjing_star_element=jishi_star_element.nextElementSibling();
			String huanjing_star=huanjing_star_element.text();//环境分数
			Element fuwu_star_element=huanjing_star_element.nextElementSibling();
			String fuwu_star=fuwu_star_element.text();//服务分数
			
			Element address = ele.getElementsByClass("address").get(0);
			String region=address.child(1).child(0).text();
			String street_address=address.child(2).text();
			
			Element tel = ele.getElementsByClass("tel").get(0);
			String phone_tel=tel.child(1).text();
			
		}
		
		

	}
	
	public static String getContent(String url) throws IOException{

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httppost = new HttpGet(url);
		//httppost.setConfig(config); 

		httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httppost.addHeader("Accept-Encoding", "gzip, deflate");
		httppost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httppost.addHeader("Connection", "keep-alive");
		httppost.addHeader("Host", "www.dianping.com");
		//httppost.addHeader("Cookie", "cy=2; cye=beijing; _hc.v=5bd00cc4-e5c3-c21a-c29c-ac617ee6d31c.1442473667; __utma=1.2090313391.1442473668.1442473668.1442474369.2; __utmz=1.1442473668.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; s_ViewType=10; aburl=1");
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
	        while((s=reader.readLine())!=null ){
	          builder.append(s);
	        }
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		//System.out.println(builder.toString());
		return builder.toString();
	}
	
	
	public static void get_nextpage() throws IOException {
		//Document doc = Jsoup.connect(url).get();
		String html=getContent(url);
		Document doc = Jsoup.parse(html);
		//获取到的是门店的list
		Elements lies = doc.select("div.page a.next");
		for (Element link : lies) {
			  //String linkHref = link.attr("href");
			  //String linkText = link.text();
			//Element pic=link.getElementsByClass("pic").get(0);
			
			
			String linkHref = link.attr("href");
			//"http://www.dianping.com"+linkHref;下一页的数据
			
			System.out.println(linkHref);
			
		}
		
		
	}
}
