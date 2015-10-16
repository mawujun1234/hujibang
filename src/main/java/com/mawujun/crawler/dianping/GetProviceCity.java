package com.mawujun.crawler.dianping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mawujun.dianping.city.City;
import com.mawujun.dianping.city.Provice;

public class GetProviceCity {

	static String url="http://www.dianping.com/citylist/citylist?citypage=1#";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//
		
		getProviceCityJSON();
		getProviceCity();
	}
	
	private static void getProviceCity() throws IOException{
		String html=GetShop.getContent(url);
		Document doc = Jsoup.parse(html);
		
		List<Provice> result=new ArrayList<Provice>();
		int provice_id=1;
		//获取当前页所有的店铺数据
		Elements lies = doc.select("#divArea>li");
		for(Element li:lies){
			if(!"root".equals(li.attr("class"))){
				//直辖市，港澳台的数据获取
				Element provice_e=li.child(0);
				Provice provice=new Provice();
				provice.setId(provice_id+"");
				provice.setName(provice_e.text());
				
				Elements cityes=li.select("div.terms a");
				for(Element city_a:cityes){
					//System.out.println(city_a.attr("href"));
					String city_name=city_a.text();
					if(city_a.children().size()>0){
						//System.out.println(city_a.child(0).text());	
						city_name=city_a.child(0).text();
					} else {
						//System.out.println(city_a.text());
						city_name=city_a.text();
					}
					System.out.println(city_name);
					City city=citys.get(city_name);
					city.setProvice_id(provice_id+"");
					city.setUrlpath(city_a.attr("href"));
					provice.addCitye(city);
					
				}
				
				result.add(provice);
				provice_id++;
				
			}
		}
		
		Elements dles = doc.select("#divArea>li.root dl.terms");
		for(Element dl:dles){
			Element dt=dl.child(0);
			System.out.println("======="+dt.text());
			
			Provice provice=new Provice();
			provice.setId(provice_id+"");
			provice.setName(dt.text());
			
			Elements cityes=dl.select("dd a");
			for(Element city_a:cityes){
				//System.out.println(city_a.attr("href"));
				String city_name=null;;
				if(city_a.children().size()>0){
					//System.out.println(city_a.child(0).text());
					city_name=city_a.child(0).text();
				} else {
					//System.out.println(city_a.text());
					city_name=city_a.text();
				}
				System.out.println(city_name);
				if("更多".equals(city_name)){
					continue;
				}
				City city=citys.get(city_name);
				city.setProvice_id(provice_id+"");
				city.setUrlpath(city_a.attr("href"));
				provice.addCitye(city);
			}
			result.add(provice);
			provice_id++;
		}
		

	} 

	//获取到的所有县城市的数据"天津|tianjin|TJ|10|tianjin"
	static Map<String,City> citys=new HashMap<String,City>();
	private static void getProviceCityJSON() throws IOException{
		String url="http://www.dianping.com/ajax/json/index/citylist/getCitylist?do=allCitylist&_nr_force=1444977342128";
		String html=GetShop.getContent(url);
		JSONObject jsonobject=JSON.parseObject(html);
		JSONArray array=jsonobject.getJSONObject("msg").getJSONArray("html");
		System.out.println(array.size());
		for(int i=0;i<array.size();i++){
			System.out.println(array.get(i));
			String[] aaa=array.getString(i).split("\\|");
			City city=new City();
			city.setId(aaa[3]);
			city.setName(aaa[0]);
			city.setPinyin(aaa[1]);
			city.setSimple_pinyin(aaa[2]);
			city.setUrlpath(aaa[4]);
			
			citys.put(city.getName(), city);
			
		}
	}
}
