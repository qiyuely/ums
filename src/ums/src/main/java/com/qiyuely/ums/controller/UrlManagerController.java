package com.qiyuely.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuely.ums.dto.url.UrlAllInfoDto;
import com.qiyuely.ums.dto.url.UrlDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlCreateReq;
import com.qiyuely.ums.req.url.UrlDeleteReq;
import com.qiyuely.ums.req.url.UrlUpdateReq;
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
	public Result<List<UrlDto>> queryList() {
		return urlManagerService.queryList();
	}
	
	/**
	 * 创建url
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createUrl")
	public Result<UrlDto> createUrl(UrlCreateReq req) {
		return urlManagerService.createUrl(req);
	}
	
	/**
	 * 修改url
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUrl")
	public Result<Void> updateUrl(UrlUpdateReq req) {
		return urlManagerService.updateUrl(req);
	}
	
	/**
	 * 删除url
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteUrl")
	public Result<Void> deleteUrl(UrlDeleteReq req) {
		return urlManagerService.deleteUrl(req);
	}
	
	/**
	 * 查询url相关全部信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryUrlAllInfo")
	public Result<UrlAllInfoDto> queryUrlAllInfo() {
		return urlManagerService.queryUrlAllInfo();
	}
}
