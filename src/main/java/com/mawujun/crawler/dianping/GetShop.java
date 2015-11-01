package com.mawujun.crawler.dianping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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

import com.mawujun.dianping.city.City;
import com.mawujun.dianping.city.Provice;
import com.mawujun.dianping.city.Shop;
import com.mawujun.dianping.city.ShopImage;
import com.mawujun.dianping.city.ShopReview;
import com.mawujun.repository.idEntity.UUIDGenerator;
import com.mawujun.utils.file.FileUtils;

//
public class GetShop {
	static String domain_url="http://www.dianping.com";
	//static String url="http://www.dianping.com/search/category/2/30/g141";
	
	static String savePath="e:/hujibang_images";
	static String shopimage_savePath=savePath+"/shopimage";

	public static void main(String[] args) throws IOException, SQLException {

		//get_nextpage();
		//getShop("/search/category/2/30/g141","D:/");
		
		//测试图片获取
		//getImage("http://www.dianping.com/shop/18009133/photos","?pg=1");
		//获取某个店铺的评论
		//getReview_more("http://www.dianping.com/shop/18009133/review_more","?pageno=1");

		 //getShopList("/search/category/2/30/g141","D:/");
		
		//getFirstThumb("http://i1.qcloud.dpfile.com/pc/7vgYFCyllHVMrbE6gNRXjONCnyJA6ny9o9-qIi5kTGT2qPrK5fQGB0f3C-oHZt6BuO0wim-g4wOugyDocHUlRA.jpg","d:\\");

		saveAllShopList();
		
		//saveShopReviewAndImages(20);
	}
	/**
	 * 指获取店铺的信息，店铺的图片和店铺的评论以后再分批次慢慢的获取
	 * @author mawujun 16064988@qq.com 
	 * @throws IOException
	 * @throws SQLException 
	 */
	public static void saveAllShopList() throws IOException, SQLException{
		QueryRunner run = new QueryRunner(DB.dataSource);

		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<List<Provice>> resultset_provice = new BeanListHandler<Provice>(Provice.class);
		ResultSetHandler<List<City>> resultset_city = new BeanListHandler<City>(City.class);
			List<Provice> provices = run.query("SELECT * FROM hjb_Provice", resultset_provice);
			for(Provice provice:provices){
				List<City> cityes=run.query("SELECT * FROM hjb_city where inited=0 and provice_id='"+provice.getId()+"'", resultset_city);
				for(City city:cityes){
					//System.out.println(city.getName());
					GetShop.saveShopList(city);
					DB.update("update hjb_city set inited=1 where id=?", city.getId());
					//break;
				}
				//break;
			}

	}
	
	/**
	 * 爬取指定门店数的店铺，对未爬取过的门店进行爬取
	 * @author mawujun 16064988@qq.com 
	 * @param limit
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void saveShopReviewAndImages(int limit) throws IOException, SQLException{
		QueryRunner run = new QueryRunner(DB.dataSource);
		ResultSetHandler<List<Shop>> resultset_shop = new BeanListHandler<Shop>(Shop.class);
		List<Shop> shopes = run.query("SELECT * FROM hjb_shop where inited=0 limit 0,"+limit, resultset_shop);
		for(Shop shop:shopes){
			//===============================================================
			//评论内容获取
			getReview_more(domain_url+"/shop/"+shop.getId()+"/review_more","?pageno=1",shop);
			//获取原始图片和图片所产生的缩略图//http://www.dianping.com/shop/18009133/photos
			getImage(domain_url+"/shop/"+shop.getId()+"/photos","?pg=1",shop);
			
			String sql_deleteShop_review="delete from hjb_shop_review where shop_code=?";
			DB.update(sql_deleteShop_review, shop.getId());
			String sql_deleteShop_image="delete from hjb_shop_image where shop_code=?";
			DB.update(sql_deleteShop_image, shop.getId());
			
			String sql_insert_shop_review="insert into "
					+ " hjb_shop_review(id,shop_code,user_id,user_name,user_img,user_img_orginurl,content,rst_skill,rst_envi,rst_service,rst_skill_txt,rst_envi_txt,rst_service_txt) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[][] params_reviewes = new Object[shop.getReviewes().size()][13];
			for(int i=0;i<shop.getReviewes().size();i++){
				ShopReview review=shop.getReviewes().get(i);
				params_reviewes[i][0]=UUIDGenerator.generate();
				params_reviewes[i][1]=review.getShop_code();
				params_reviewes[i][2]=review.getUser_id();
				params_reviewes[i][3]=review.getUser_name();
				params_reviewes[i][4]=review.getUser_img();
				params_reviewes[i][5]=review.getUser_img();
				params_reviewes[i][6]=review.getContent();
				params_reviewes[i][7]=review.getRst_skill();
				params_reviewes[i][8]=review.getRst_envi();
				params_reviewes[i][9]=review.getRst_service();
				params_reviewes[i][10]=review.getRst_skill_txt();
				params_reviewes[i][11]=review.getRst_envi_txt();
				params_reviewes[i][12]=review.getRst_service_txt();
			}
			DB.batch(sql_insert_shop_review, params_reviewes);
	
			Object[][] params_images = new Object[shop.getImages().size()][6];
			String sql_insert_shop_image="insert into hjb_shop_image(id,shop_code,thumb_url,thumb_ogrinurl,image_url,image_orginurl) values(?,?,?,?,?,?)";
			for(int i=0;i<shop.getImages().size();i++){
				ShopImage shopImage=shop.getImages().get(i);
				params_images[i][0]=UUIDGenerator.generate();
				params_images[i][1]=shopImage.getShop_code();
				params_images[i][2]=shopImage.getThumb_url();
				params_images[i][3]=shopImage.getThumb_ogrinurl();
				params_images[i][4]=shopImage.getImage_url();
				params_images[i][5]=shopImage.getImage_orginurl();
			}
			DB.batch(sql_insert_shop_image, params_images);
			
			DB.update("update hjb_shop set inited=1 where id=?", shop.getId());
		}
	}
	
	//http://langgufu.iteye.com/blog/2167077
	
	
	/**
	 * 获取shop的列表
	 * getShop("/search/category/2/30/g141","D:/");
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param uri
	 * @param imgDir
	 * @throws IOException
	 */
	public static void saveShopList(City city) throws IOException {
		
		String url=domain_url+"/search/category/"+city.getId()+"/30/g141";
		String html=getContent(url);
		Document doc = Jsoup.parse(html);
		
		//获取当前页所有的店铺数据
		Elements lies = doc.select("#shop-all-list ul li");
		for (Element link : lies) {
			//Element pic=link.getElementsByClass("pic").get(0);
			//获取缩略图
			Element pic=link.child(0);
			String shop_url=pic.child(0).attr("href");//店铺url地址
			String shop_code=shop_url.substring(shop_url.lastIndexOf('/')+1);
			String thumb_url=pic.child(0).child(0).attr("data-src");//店铺缩略图
			String shop_name=pic.child(0).child(0).attr("title");//店铺名称
			
			saveShopInifo(city,shop_code,shop_name,thumb_url,shop_url);
			
			break;
		}
		
		// ===================================================================================================
		// 获取下一页，如果有下一页就继续获取数据
		Elements nextpages = doc.select("div.page a.next");
		for (Element link : nextpages) {
			// String linkHref = link.attr("href");
			// String linkText = link.text();
			String linkHref = link.attr("href");
			// "http://www.dianping.com"+linkHref;下一页的数据

			System.out.println(linkHref);
			// getShop(domain_url+ linkHref);
		}
	}
	/**
	 * 获取门店具体的信息
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param shop_code 门店的id
	 * @param thumb 门店的缩略图地址，展示用的
	 * @param shopUrl 门店的地址
	 * @param imgDir 门店图片保存的文件夹
	 * @throws IOException
	 */
	
	public static Shop saveShopInifo(City city,String shop_code,String shop_name,String thumb_orginurl,String shopUrl ) throws IOException {
			Shop shop=new Shop();
			shop.setId(shop_code);
			//shop.setAddr();
			shop.setName(shop_name);
			shop.setCity_id(city.getId());

			//先创建需要的路径
			FileUtils.createDir(shopimage_savePath+File.separator+shop_code);
			FileUtils.createDir(shopimage_savePath+File.separator+shop_code+"/thumb");//存放缩略图
			FileUtils.createDir(shopimage_savePath+File.separator+shop_code+"/images");//存放原始图
			FileUtils.createDir(savePath+File.separator+"userimages");
			//http://i2.s2.dpfile.com/pc/e0de1df59cb5aee2a826d57a7ec0fece(249x249)/thumb.jpg
			//缩略图下载过来,按照
			//店铺代码/***.jpg（搜索时显示的图片）,店铺代码/thumb这个目录存放缩略图，店铺代码/image存放原始图
			//并且获取到的第一张作为默认缩略图
			String thumb_rel_path="/"+shop_code+"/"+getFirstThumb(thumb_orginurl,shopimage_savePath+File.separator+shop_code);
			shop.setThumb_orginurl(thumb_orginurl);
			shop.setThumb(thumb_rel_path);
			
			
			getShopDetailInfo(domain_url+shopUrl,shop);
			
//			//===============================================================
//			//评论内容获取
//			getReview_more(domain_url+shopUrl+"/review_more","?pageno=1",shop);
//			//获取原始图片和图片所产生的缩略图//http://www.dianping.com/shop/18009133/photos
//			getImage(domain_url+shopUrl+"/photos","?pg=1",shop);
			
			
			//吧图片原地址都放上去
			//先清空该报表的所有数据，店铺，评论，图片
			String sql_deleteShop="delete from hjb_shop where id=?";
			DB.update(sql_deleteShop, shop_code);
//			String sql_deleteShop_review="delete from hjb_shop_review where shop_code=?";
//			DB.update(sql_deleteShop_review, shop_code);
//			String sql_deleteShop_image="delete from hjb_shop_image where shop_code=?";
//			DB.update(sql_deleteShop_image, shop_code);
			
			String sql_insert_shop="insert into hjb_shop(id,name,thumb,thumb_orginurl,addr,phone,meanPrice) values(?,?,?,?,?,?,?)";
			DB.update(sql_insert_shop, shop.getId(),shop.getName(),shop.getThumb(),shop.getThumb_orginurl(),shop.getAddr(),shop.getPhone(),shop.getMeanPrice());
			
//			String sql_insert_shop_review="insert into "
//					+ " hjb_shop_review(id,shop_code,user_id,user_name,user_img,user_img_orginurl,content,rst_skill,rst_envi,rst_service,rst_skill_txt,rst_envi_txt,rst_service_txt) "
//					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			Object[][] params_reviewes = new Object[shop.getReviewes().size()][13];
//			for(int i=0;i<shop.getReviewes().size();i++){
//				ShopReview review=shop.getReviewes().get(i);
//				params_reviewes[i][0]=UUIDGenerator.generate();
//				params_reviewes[i][1]=review.getShop_code();
//				params_reviewes[i][2]=review.getUser_id();
//				params_reviewes[i][3]=review.getUser_name();
//				params_reviewes[i][4]=review.getUser_img();
//				params_reviewes[i][5]=review.getUser_img();
//				params_reviewes[i][6]=review.getContent();
//				params_reviewes[i][7]=review.getRst_skill();
//				params_reviewes[i][8]=review.getRst_envi();
//				params_reviewes[i][9]=review.getRst_service();
//				params_reviewes[i][10]=review.getRst_skill_txt();
//				params_reviewes[i][11]=review.getRst_envi_txt();
//				params_reviewes[i][12]=review.getRst_service_txt();
//			}
//			DB.batch(sql_insert_shop_review, params_reviewes);
//	
//			Object[][] params_images = new Object[shop.getImages().size()][6];
//			String sql_insert_shop_image="insert into hjb_shop_image(id,shop_code,thumb_url,thumb_ogrinurl,image_url,image_orginurl) values(?,?,?,?,?,?)";
//			for(int i=0;i<shop.getImages().size();i++){
//				ShopImage shopImage=shop.getImages().get(i);
//				params_images[i][0]=UUIDGenerator.generate();
//				params_images[i][1]=shopImage.getShop_code();
//				params_images[i][2]=shopImage.getThumb_url();
//				params_images[i][3]=shopImage.getThumb_ogrinurl();
//				params_images[i][4]=shopImage.getImage_url();
//				params_images[i][5]=shopImage.getImage_orginurl();
//			}
//			DB.batch(sql_insert_shop_image, params_images);
			
			return shop;

	}
	

	public static void getReview_more(String url, String page,Shop shop) throws IOException {
		List<ShopReview> reviewes=shop.getReviewes();
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
			String user_img_orginurl=img.attr("src");
			String user_name=img.attr("title");
			
			String user_img_filename=getFirstThumb(user_img_orginurl,savePath+File.separator+"userimages");
			
			ShopReview review=new ShopReview();
			review.setShop_code(shop.getId());
			review.setUser_id(user_id);
			review.setUser_img("/userimages/"+user_img_filename);
			review.setUser_img_orginurl(user_img_orginurl);
			review.setUser_name(user_name);
			
			Element content=li.child(1);
			//获取评论的星级
			Elements comment_rst=content.select("span.rst");
			if(comment_rst!=null && comment_rst.size()>0){
				//查询具体的评价信息
				for(Element rst:comment_rst){
					
					String rst1=rst.ownText();//分为 技师，环境，服务等几个方面来评价的,内容格式是：技师2，环境2，服务3
					String rst1_text=rst.child(0).text();//评价的文字描述，(好)，(非常好)，(很差)
					
					if(rst1.startsWith("技师")){
						review.setRst_skill(Integer.parseInt(rst1.substring(2)));
						review.setRst_skill_txt(rst1_text);
					} else if(rst1.startsWith("环境")){
						review.setRst_envi(Integer.parseInt(rst1.substring(2)));
						review.setRst_envi_txt(rst1_text);
					}  else if(rst1.startsWith("服务")){
						review.setRst_service(Integer.parseInt(rst1.substring(2)));
						review.setRst_service_txt(rst1_text);
					}
					review.setRst_envi_txt(rst1_text);
					
					System.out.println(rst1);
					System.out.println(rst1_text);
				}
				
			}
			//获取评论的内容
			Elements comment_txt=content.select("div.J_brief-cont");
			String contentText=comment_txt.get(0).text();
			System.out.println(contentText);

			
			
			
			review.setContent(contentText);	
			
			reviewes.add(review);
		}

		// 判断是否有下一页，如果有下一页，就继续获取,正在递归获取
		Elements NextPage = doc.select(".Pages .Pages .NextPage");
		if (NextPage != null && NextPage.size() > 0) {
			getReview_more(url, NextPage.get(0).attr("href"),shop);
		}
	}
	
	
	/**
	 * //获取第一个缩略图，就是显示在查询的时候的缩略图
	//获取图片的公共方法
	 * 
	 * **/
	public static String getFirstThumb(String url,String savePath) throws ClientProtocolException, IOException{
		
		String[] thumbes=url.split("\\/");
		//获取图片后缀
		//http://i3.s1.dpfile.com/pc/94278e3192cd09d6f3d5a346d96afc0c(240c180)/thumb.jpg
		String imgFileName=thumbes[thumbes.length-1];
		String imgFileName_suffix=imgFileName.substring(imgFileName.indexOf('.')+1);
		//String rel_path=File.separator+thumbes[thumbes.length-2]+"."+imgFileName_suffix;
		String filePath=savePath+File.separator+thumbes[thumbes.length-2]+"."+imgFileName_suffix;;
		
		//shop.setThumb("/"+thumbes[thumbes.length-2]+"."+imgFileName_suffix);
		
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
        
        return imgFileName;
        
	}
	

	//获取某个门店拥有的所有缩略图和图片地址
	public static void getImage(String url,String page,Shop shop) throws ClientProtocolException, IOException{
		List<ShopImage> images=shop.getImages();
		
		//String[] result=new String[2];
		//获取图片所在网页的所有图片
		String html= getContent(url+page);
		//获取图片所有的页数，然后进形循环获取
		Document doc = Jsoup.parse(html);
		//开始获取当前页的所有图片
		Elements J_listes = doc.select(".picture-square .picture-list ul li.J_list div.img a");
		for(Element element:J_listes) {
			String thumb_orginurl=element.children().get(0).attr("src");
			String thumb_img_url="/"+shop.getId()+"/thumb/"+getFirstThumb(thumb_orginurl,shopimage_savePath+File.separator+shop.getId()+File.separator+"thumb");
			//result[0]=thumb_img_url;
			String img_orginurl=thumb_orginurl.replaceFirst("240c180", "700x700");
			String images_img_url="/"+shop.getId()+"/images/"+getFirstThumb(img_orginurl,shopimage_savePath+File.separator+shop.getId()+File.separator+"images");
			//result[1]=images_img_url;
			
			ShopImage shopImage=new ShopImage();
			shopImage.setImage_url(images_img_url);
			shopImage.setImage_orginurl(img_orginurl);
			shopImage.setThumb_url(thumb_img_url);
			shopImage.setThumb_ogrinurl(thumb_orginurl);
			shopImage.setShop_code(shop.getId());
			images.add(shopImage);
			//System.out.println(thumb_url);
			//System.out.println(img_url);

		}
		
		//判断是否有下一页，如果有下一页，就继续获取,正在递归获取
		Elements NextPage = doc.select(".Pages .Pages .NextPage");
		if(NextPage!=null && NextPage.size()>0){
			getImage(url,NextPage.get(0).attr("href"),shop);
		}
		
        
	}
	
	//获取店铺的详细信息
	public static void getShopDetailInfo(String url,Shop shop) throws IOException{
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
			String meanPrice=avg_element.text();//人均消费
			
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
			
			shop.setMeanPrice(meanPrice);
			shop.setAddr(region+street_address);
			shop.setPhone(phone_tel);
			
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
	
	
//	public static void get_nextpage() throws IOException {
//		//Document doc = Jsoup.connect(url).get();
//		String html=getContent(url);
//		Document doc = Jsoup.parse(html);
//		//获取到的是门店的list
//		Elements lies = doc.select("div.page a.next");
//		for (Element link : lies) {
//			  //String linkHref = link.attr("href");
//			  //String linkText = link.text();
//			//Element pic=link.getElementsByClass("pic").get(0);
//			
//			
//			String linkHref = link.attr("href");
//			//"http://www.dianping.com"+linkHref;下一页的数据
//			
//			System.out.println(linkHref);
//			
//		}

}
