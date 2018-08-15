package com.qiyuely.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * url类型controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/url/type/")
public class UrlTypeController {

	/**
	 * 到url视图界面
	 * @return
	 */
	@RequestMapping("toView")
	public String toView() {
		return "url/urlType";
	}
}
