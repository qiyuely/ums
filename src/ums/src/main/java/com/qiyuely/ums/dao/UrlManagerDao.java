package com.qiyuely.ums.dao;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlEntity;
import com.qiyuely.ums.req.url.UrlQueryReq;

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
	List<UrlEntity> queryList(UrlQueryReq req);
	
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	int insert(UrlEntity entity); 
}
