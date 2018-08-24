package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlTypeTempEntity;

/**
 * url类型模板dao
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface UrlTypeTempDao {
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(UrlTypeTempEntity entity);
	
	/**
	 * 查询列表
	 * @return
	 */
	List<UrlTypeTempEntity> queryList();
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	int update(UrlTypeTempEntity entity);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(String id);
	
	/**
	 * 根据名字查询url类型模板
	 * @param name
	 * @return
	 */
	UrlTypeTempEntity findByName(String name);
}
