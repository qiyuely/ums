package com.qiyuely.ums.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qiyuely.ums.dto.url.UrlTypeTempDto;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeTempCreateReq;
import com.qiyuely.ums.req.url.UrlTypeTempDeleteReq;
import com.qiyuely.ums.req.url.UrlTypeTempUpdateReq;
import com.qiyuely.ums.service.UrlTypeTempService;

/**
 * url类型模板service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class UrlTypeTempServiceImpl extends BaseService implements UrlTypeTempService {

	@Override
	public Result<List<UrlTypeTempDto>> queryUrlTypeTempInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<UrlTypeTempDto> createUrlTypeTemp(UrlTypeTempCreateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Void> updateUrlTypeTemp(UrlTypeTempUpdateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Void> deleteUrlTypeTemp(UrlTypeTempDeleteReq req) {
		// TODO Auto-generated method stub
		return null;
	}

}
