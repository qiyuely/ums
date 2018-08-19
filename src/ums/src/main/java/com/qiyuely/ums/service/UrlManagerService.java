package com.qiyuely.ums.service;

import java.util.List;

import com.qiyuely.ums.dto.url.UrlDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlCreateReq;
import com.qiyuely.ums.req.url.UrlDeleteReq;
import com.qiyuely.ums.req.url.UrlQueryReq;
import com.qiyuely.ums.req.url.UrlUpdateReq;

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
	public Result<List<UrlDto>> queryList(UrlQueryReq req);
	
	/**
	 * 创建url
	 * @param entity
	 * @return
	 */
	public Result<UrlDto> createUrl(UrlCreateReq req);
	
	/**
	 * 修改url
	 * @param entity
	 * @return
	 */
	public Result<Void> updateUrl(UrlUpdateReq req);
	
	/**
	 * 删除url
	 * @param entity
	 * @return
	 */
	public Result<Void> deleteUrl(UrlDeleteReq req);
}
