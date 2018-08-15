package com.qiyuely.ums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiyuely.ums.dao.UrlManagerDao;
import com.qiyuely.ums.entity.url.UrlEntity;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlQueryReq;
import com.qiyuely.ums.service.UrlManagerService;

/**
 * url管理service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class UrlManagerServiceImpl extends BaseService implements UrlManagerService {
	
	@Autowired
	private UrlManagerDao urlManagerDao;

	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public Result<List<UrlEntity>> queryList(UrlQueryReq req) {
		List<UrlEntity> list = urlManagerDao.queryList(req);
		return packResult(list);
	}
	
}
