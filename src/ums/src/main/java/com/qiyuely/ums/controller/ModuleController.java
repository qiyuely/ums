package com.qiyuely.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组件controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/module/")
public class ModuleController {
	
	/**
	 * 打开url类型选择组件界面
	 * @return
	 */
	@RequestMapping("toModuleUrlTypeSelect")
	public String toModuleUrlTypeSelect() {
		return "url/urlTypeSelectModule";
	}
}
