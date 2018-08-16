package com.qiyuely.ums.service;

import java.util.List;

import com.qiyuely.ums.dto.url.UrlTypeDto;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeCreateReq;

/**
 * url类型管理service
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface UrlTypeService {
	
	/**
	 * 查询url类型信息
	 */
	public Result<List<UrlTypeDto>> queryUrlTypeInfo();
	
	/**
	 * 创建url类型
	 * @param entity
	 * @return
	 */
	public Result<Void> createUrlType(UrlTypeCreateReq req);
}
