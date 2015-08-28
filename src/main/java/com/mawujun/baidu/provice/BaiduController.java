package com.mawujun.baidu.provice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaiduController {
	
	@ResponseBody
	@RequestMapping("/baidu/query.do")
	public String query(){
		return "aaa";
	}
}
