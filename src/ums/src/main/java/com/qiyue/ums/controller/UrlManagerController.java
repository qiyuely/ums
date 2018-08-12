package com.qiyue.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * url管理controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/ums/")
public class UrlManagerController {

	/**
	 * 主方法
	 */
	@RequestMapping("/")
	public String main() {
		System.out.println("=======");
		return "";
	}
}
