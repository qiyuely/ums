package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlTypeEntity;

/**
 * url类型dao
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface UrlTypeDao {
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(UrlTypeEntity entity);
	
	/**
	 * 查询列表
	 * @return
	 */
	List<UrlTypeEntity> queryList();
}
