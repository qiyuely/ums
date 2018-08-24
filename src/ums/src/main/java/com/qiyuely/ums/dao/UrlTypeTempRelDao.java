package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlTypeTempRelEntity;

/**
 * url类型模板关系dao
 * @author Administrator
 *
 */
public interface UrlTypeTempRelDao {
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(UrlTypeTempRelEntity entity);
	
	/**
	 * 根据url类型模板id删除所有的url类型关系
	 * @param urlId
	 * @return
	 */
	int deleteByTempId(String tempId);
	
	/**
	 * 根据url类型模板查询所有的url类型id列表
	 * @param urlId
	 * @return
	 */
	List<String> queryUrlTypeIdListByTempId(String tempId);
}
