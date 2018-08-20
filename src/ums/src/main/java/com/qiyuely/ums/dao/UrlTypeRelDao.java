package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlTypeRelEntity;

/**
 * url类型关系dao
 * @author Administrator
 *
 */
public interface UrlTypeRelDao {
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(UrlTypeRelEntity entity);
	
	/**
	 * 根据url id删除所有的url类型关系
	 * @param urlId
	 * @return
	 */
	int deleteByUrlId(String urlId);
	
	/**
	 * 根据url id查询所有的url类型id列表
	 * @param urlId
	 * @return
	 */
	List<String> queryUrlTypeIdListByUrlId(String urlId);
}
