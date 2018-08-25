package com.qiyuely.ums.service;

import java.util.List;

import com.qiyuely.ums.dto.sys.SysOpSettingHistoryDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.sys.SysOpSettingHistoryUpdateReq;

/**
 * 操作设定历史service
 *
 * @author Qiaoxin.Hong
 *
 */
public interface SysOpSettingHistoryService {

	/**
	 * 查询操作设定历史列表
	 */
	public Result<List<SysOpSettingHistoryDto>> queryOpSettingHistoryList();
	
	/**
	 * 修改操作设定历史
	 * @param entity
	 * @return
	 */
	public Result<Void> updateOpSettingHistory(SysOpSettingHistoryUpdateReq req);
}
