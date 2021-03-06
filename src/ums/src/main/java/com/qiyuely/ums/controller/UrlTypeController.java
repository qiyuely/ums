package com.qiyuely.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuely.ums.dto.url.UrlTypeDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeCreateReq;
import com.qiyuely.ums.req.url.UrlTypeDeleteReq;
import com.qiyuely.ums.req.url.UrlTypeUpdateReq;
import com.qiyuely.ums.service.UrlTypeService;

/**
 * url类型controller
 * 
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/url/type/")
public class UrlTypeController {
	
	@Autowired
	private UrlTypeService urlTypeService;

	/**
	 * 到url类型视图界面
	 * @return
	 */
	@RequestMapping("toView")
	public String toView() {
		return "url/urlType";
	}
	
	/**
	 * 查询url类型信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryUrlTypeInfo")
	public Result<List<UrlTypeDto>> queryUrlTypeInfo() {
		return urlTypeService.queryUrlTypeInfo();
	}
	
	/**
	 * 创建url类型
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createUrlType")
	public Result<UrlTypeDto> createUrlType(UrlTypeCreateReq req) {
		return urlTypeService.createUrlType(req);
	}
	
	/**
	 * 修改url类型
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUrlType")
	public Result<Void> updateUrlType(UrlTypeUpdateReq req) {
		return urlTypeService.updateUrlType(req);
	}
	
	/**
	 * 删除url类型
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteUrlType")
	public Result<Void> deleteUrlType(UrlTypeDeleteReq req) {
		return urlTypeService.deleteUrlType(req);
	}
}
