package com.qiyuely.ums.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiyuely.remex.utils.CollectionUtils;
import com.qiyuely.ums.dao.SysOpSettingHistoryDao;
import com.qiyuely.ums.dto.sys.SysOpSettingHistoryDto;
import com.qiyuely.ums.entity.sys.SysOpSettingHistoryEntity;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.sys.SysOpSettingHistoryUpdateReq;
import com.qiyuely.ums.service.SysOpSettingHistoryService;

/**
 * 操作设定历史service
 *
 * @author Qiaoxin.Hong
 *
 */
@Service
public class SysOpSettingHistoryServiceImpl extends BaseService implements SysOpSettingHistoryService {
	
	@Autowired
	private SysOpSettingHistoryDao sysOpSettingHistoryDao;

	/**
	 * 查询操作设定历史列表
	 */
	@Override
	public Result<List<SysOpSettingHistoryDto>> queryOpSettingHistoryList() {
		List<SysOpSettingHistoryDto> dtoList = new ArrayList<>();
		List<SysOpSettingHistoryEntity> list = sysOpSettingHistoryDao.queryList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (SysOpSettingHistoryEntity entity : list) {
				SysOpSettingHistoryDto dto = new SysOpSettingHistoryDto();
				dto.setOpKey(entity.getOpKey());
				dto.setSetting(entity.getSetting());
				dtoList.add(dto);
			}
		}
		
		return packResult(dtoList);
	}

	/**
	 * 修改操作设定历史
	 * @param entity
	 * @return
	 */
	@Override
	public Result<Void> updateOpSettingHistory(SysOpSettingHistoryUpdateReq req) {
		SysOpSettingHistoryEntity entity = sysOpSettingHistoryDao.findByOpKey(req.getOpKey());
		//如果没有操作设定历史记录，则初始化
		if (entity == null) {
			entity = new SysOpSettingHistoryEntity();
			entity.setOpKey(req.getOpKey());
			entity.setSetting(req.getSetting());
			sysOpSettingHistoryDao.insert(entity);
		} else {  //有操作设定历史记录，则走修改逻辑
			entity.setSetting(req.getSetting());
			sysOpSettingHistoryDao.update(entity);
		}
		
		return packResult();
	}

}
