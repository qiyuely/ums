package com.qiyuely.ums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyuely.ums.dao.SysDao;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.service.SysService;

/**
 * 系统管理service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class SysServiceImpl extends BaseService implements SysService {
	
	@Autowired
	private SysDao sysDao;

	/**
	 * 初始化数据库
	 */
	@Transactional
	@Override
	public void initDb() {
		sysDao.initDb();
	}
}
