package com.mawujun.dianping.city;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.controller.spring.mvc.json.JsonConfigHolder;
import com.mawujun.crawler.dianping.GetShop;
import com.mawujun.messge.context.WeiXinApplicationContext;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.PageParam;
import com.mawujun.utils.page.PageResult;
import com.mawujun.dianping.city.Provice;
import com.mawujun.dianping.city.ProviceService;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/provice")
public class ProviceController {

	@Resource
	private ProviceService proviceService;
	@Resource
	private CityService cityService;

//
//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/provice/query.do")
//	@ResponseBody
//	public List<Provice> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.Provice.parent.id, "root".equals(id)?null:id);
//		List<Provice> provicees=proviceService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(Provice.class,M.Provice.parent.name());
//		return provicees;
//	}
	
	@RequestMapping("/provice/init.do")
	@ResponseBody
	public String init(Integer start,Integer limit,String sampleName) throws IOException{
		proviceService.init();
		return "success";
	}
	
	public void initShop() throws IOException{
		List<City> cityes=cityService.queryAll();
		
		for(City city:cityes){
			s
			GetShop.getShop("/search/category/"+city.getId()+"/30/g141",WeiXinApplicationContext.getWebapp_realPath());
		}
	}

	@RequestMapping("/provice/query.do")
	@ResponseBody
	public List<Provice> query() {	
		List<Provice> provicees=proviceService.queryAll();
		return provicees;
	}
	

	@RequestMapping("/provice/load.do")
	public Provice load(String id) {
		return proviceService.get(id);
	}
	
	@RequestMapping("/provice/create.do")
	@ResponseBody
	public Provice create(@RequestBody Provice provice) {
		proviceService.create(provice);
		return provice;
	}
	
	@RequestMapping("/provice/update.do")
	@ResponseBody
	public  Provice update(@RequestBody Provice provice) {
		proviceService.update(provice);
		return provice;
	}
	
	@RequestMapping("/provice/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		proviceService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/provice/destroy.do")
	@ResponseBody
	public Provice destroy(@RequestBody Provice provice) {
		proviceService.delete(provice);
		return provice;
	}
	
	
}
