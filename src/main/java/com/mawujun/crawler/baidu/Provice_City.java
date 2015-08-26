package com.mawujun.crawler.baidu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Provice_City {

	public static void main(String[] args) throws IOException {
		//File file=new File("E:\\eclipse\\workspace\\hujibang\\src\\main\\java\\com\\mawujun\\crawler\\provice_city.txt");
		BufferedReader in=new BufferedReader(new FileReader("E:\\eclipse\\workspace\\hujibang\\src\\main\\java\\com\\mawujun\\crawler\\provice_city.txt"));
		StringBuilder html=new StringBuilder();
		String s;
		while((s=in.readLine())!=null) {
			html.append(s);
		} 
		Document doc = Jsoup.parse(html.toString());
		
		//Element content = doc.getElementById("content");
		Elements trs = doc.getElementsByTag("tr");
		for (Element tr : trs) {
			if(tr.children().size()<3){
				continue;
			}
			String provice=tr.child(1).child(0).text();
			System.out.println(provice);
			
			Elements city_as=tr.child(2).getElementsByTag("a");
			for(Element  a:city_as){
				System.out.println(a.text());
			}
		  //String linkHref = link.attr("href");
		 // String linkText = link.text();
		}
	}

}
