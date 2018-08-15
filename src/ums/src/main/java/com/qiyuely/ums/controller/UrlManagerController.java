package com.qiyuely.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuely.ums.entity.url.UrlEntity;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlQueryReq;
import com.qiyuely.ums.service.UrlManagerService;

/**
 * url管理controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/url/")
public class UrlManagerController {
	
	@Autowired
	private UrlManagerService urlManagerService;

	/**
	 * 到url视图界面
	 * @return
	 */
	@RequestMapping("toView")
	public String toView() {
		return "url/url";
	}
	
	/**
	 * 查询列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryList")
	public Result<List<UrlEntity>> queryList(UrlQueryReq req) {
		return urlManagerService.queryList(req);
	}
}
