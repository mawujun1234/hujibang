package com.mawujun.dianping.city;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mawujun.controller.spring.mvc.json.JsonConfigHolder;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.PageParam;
import com.mawujun.utils.page.PageResult;

import com.mawujun.dianping.city.City;
import com.mawujun.dianping.city.CityService;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/city")
public class CityController {

	@Resource
	private CityService cityService;


//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/city/query.do")
//	@ResponseBody
//	public List<City> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.City.parent.id, "root".equals(id)?null:id);
//		List<City> cityes=cityService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(City.class,M.City.parent.name());
//		return cityes;
//	}
//
//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/city/query.do")
//	@ResponseBody
//	public PageResult<City> query(Integer start,Integer limit,String sampleName){
//		PageParam page=PageParam.getInstance(start,limit);//.addParam(M.City.sampleName, "%"+sampleName+"%");
//		return cityService.queryPage(page);
//	}

	@RequestMapping("/city/query.do")
	@ResponseBody
	public List<City> query() {	
		List<City> cityes=cityService.queryAll();
		return cityes;
	}
	

	@RequestMapping("/city/load.do")
	public City load(String id) {
		return cityService.get(id);
	}
	
	@RequestMapping("/city/create.do")
	@ResponseBody
	public City create(@RequestBody City city) {
		cityService.create(city);
		return city;
	}
	
	@RequestMapping("/city/update.do")
	@ResponseBody
	public  City update(@RequestBody City city) {
		cityService.update(city);
		return city;
	}
	
	@RequestMapping("/city/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		cityService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/city/destroy.do")
	@ResponseBody
	public City destroy(@RequestBody City city) {
		cityService.delete(city);
		return city;
	}
	
	
}
