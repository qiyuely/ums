package com.qiyuely.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
	
	/**
	 * 主方法
	 */
	@RequestMapping("/")
	public String main() {
		return "main";
	}
}
