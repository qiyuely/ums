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
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	int update(UrlTypeEntity entity);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(String id);
}
