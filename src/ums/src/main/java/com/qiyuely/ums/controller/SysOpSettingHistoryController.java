package com.qiyuely.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuely.ums.dto.sys.SysOpSettingHistoryDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.sys.SysOpSettingHistoryUpdateReq;
import com.qiyuely.ums.service.SysOpSettingHistoryService;

/**
 * 操作设定历史controller
 *
 * @author Qiaoxin.Hong
 *
 */
@Controller
@RequestMapping("/sys/opSettingHistory/")
public class SysOpSettingHistoryController {
	
	@Autowired
	private SysOpSettingHistoryService sysOpSettingHistoryService;
	
	/**
	 * 查询操作设定历史列表
	 */
	@ResponseBody
	@RequestMapping("queryOpSettingHistoryList")
	public Result<List<SysOpSettingHistoryDto>> queryOpSettingHistoryList() {
		return sysOpSettingHistoryService.queryOpSettingHistoryList();
	}
	
	/**
	 * 修改操作设定历史
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateOpSettingHistory")
	public Result<Void> updateOpSettingHistory(SysOpSettingHistoryUpdateReq req) {
		return sysOpSettingHistoryService.updateOpSettingHistory(req);
	}
}
