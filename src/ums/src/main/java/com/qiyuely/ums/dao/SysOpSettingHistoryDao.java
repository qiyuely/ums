package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.sys.SysOpSettingHistoryEntity;

/**
 * 操作设定历史dao
 *
 * @author Qiaoxin.Hong
 *
 */
public interface SysOpSettingHistoryDao {

	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(SysOpSettingHistoryEntity entity);
	
	/**
	 * 查询列表
	 * @return
	 */
	List<SysOpSettingHistoryEntity> queryList();
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	int update(SysOpSettingHistoryEntity entity);
	
	/**
	 * 根据操作主键查询操作设定
	 * @param name
	 * @return
	 */
	SysOpSettingHistoryEntity findByOpKey(String opKey);
}
