package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlEntity;

/**
 * url管理dao
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface UrlManagerDao {

	/**
	 * 查询列表
	 * @param req
	 * @return
	 */
	List<UrlEntity> queryList();
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(UrlEntity entity); 
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int update(UrlEntity entity);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(String id);
}
