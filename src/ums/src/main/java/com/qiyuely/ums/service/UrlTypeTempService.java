package com.qiyuely.ums.service;

import java.util.List;

import com.qiyuely.ums.dto.url.UrlTypeTempDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeTempCreateReq;
import com.qiyuely.ums.req.url.UrlTypeTempDeleteReq;
import com.qiyuely.ums.req.url.UrlTypeTempUpdateReq;

/**
 * url类型模板service
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface UrlTypeTempService {
	
	/**
	 * 查询url类型模板信息
	 */
	public Result<List<UrlTypeTempDto>> queryUrlTypeTempInfo();
	
	/**
	 * 创建url类型模板
	 * @param entity
	 * @return
	 */
	public Result<UrlTypeTempDto> createUrlTypeTemp(UrlTypeTempCreateReq req);
	
	/**
	 * 修改url类型模板
	 * @param entity
	 * @return
	 */
	public Result<Void> updateUrlTypeTemp(UrlTypeTempUpdateReq req);
	
	/**
	 * 删除url类型模板
	 * @param entity
	 * @return
	 */
	public Result<Void> deleteUrlTypeTemp(UrlTypeTempDeleteReq req);
}
