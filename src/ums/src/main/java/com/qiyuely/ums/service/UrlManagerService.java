package com.qiyuely.ums.service;

import java.util.List;

import com.qiyuely.ums.entity.url.UrlEntity;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlQueryReq;

/**
 * url管理service
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface UrlManagerService {

	/**
	 * 查询列表
	 * @return
	 */
	public Result<List<UrlEntity>> queryList(UrlQueryReq req);
}
