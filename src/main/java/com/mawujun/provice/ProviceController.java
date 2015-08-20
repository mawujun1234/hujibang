package com.mawujun.provice;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mawujun.utils.page.PageRequest;
import com.mawujun.utils.page.QueryResult;
import com.mawujun.controller.spring.mvc.json.JsonConfigHolder;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.page.Page;
import com.mawujun.utils.M;

import com.mawujun.provice.Provice;
import com.mawujun.provice.ProviceService;
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


	/**
	 * 请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param id 是父节点的id
	 * @return
	 */
	@RequestMapping("/provice/query.do")
	@ResponseBody
	public List<Provice> query(String id) {
		Cnd cnd=Cnd.select().andEquals(M.Provice.parent.id, "root".equals(id)?null:id);
		List<Provice> provicees=proviceService.query(cnd);
		//JsonConfigHolder.setFilterPropertys(Provice.class,M.Provice.parent.name());
		return provicees;
	}

	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/provice/query.do")
	@ResponseBody
	public Page query(Integer start,Integer limit,String sampleName){
		Page page=Page.getInstance(start,limit);//.addParam(M.Provice.sampleName, "%"+sampleName+"%");
		return proviceService.queryPage(page);
	}

	@RequestMapping("/provice/query.do")
	@ResponseBody
	public List<Provice> query() {	
		List<Provice> provicees=proviceService.queryAll();
		return provicees;
	}
	

	@RequestMapping("/provice/load.do")
	public Provice load(Integer id) {
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
	public Integer deleteById(Integer id) {
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
