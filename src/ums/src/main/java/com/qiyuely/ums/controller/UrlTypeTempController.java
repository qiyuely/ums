package com.qiyuely.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuely.ums.dto.url.UrlTypeTempDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeTempCreateReq;
import com.qiyuely.ums.req.url.UrlTypeTempDeleteReq;
import com.qiyuely.ums.req.url.UrlTypeTempUpdateReq;
import com.qiyuely.ums.service.UrlTypeTempService;

/**
 * url类型模板模板controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/url/typeTemp/")
public class UrlTypeTempController {
	
	@Autowired
	private UrlTypeTempService urlTypeTempService;

	/**
	 * 到url类型模板视图界面
	 * @return
	 */
	@RequestMapping("toView")
	public String toView() {
		return "url/urlTypeTemp";
	}
	
	/**
	 * 查询url类型模板信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryUrlTypeTempInfo")
	public Result<List<UrlTypeTempDto>> queryUrlTypeTempInfo() {
		return urlTypeTempService.queryUrlTypeTempInfo();
	}
	
	/**
	 * 创建url类型模板
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createUrlTypeTemp")
	public Result<UrlTypeTempDto> createUrlTypeTemp(UrlTypeTempCreateReq req) {
		return urlTypeTempService.createUrlTypeTemp(req);
	}
	
	/**
	 * 修改url类型模板
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUrlTypeTemp")
	public Result<Void> updateUrlTypeTemp(UrlTypeTempUpdateReq req) {
		return urlTypeTempService.updateUrlTypeTemp(req);
	}
	
	/**
	 * 删除url类型模板
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteUrlTypeTemp")
	public Result<Void> deleteUrlTypeTemp(UrlTypeTempDeleteReq req) {
		return urlTypeTempService.deleteUrlTypeTemp(req);
	}
}
