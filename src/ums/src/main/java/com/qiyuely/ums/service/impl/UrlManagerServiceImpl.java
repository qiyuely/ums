package com.qiyuely.ums.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyuely.remex.utils.CollectionUtils;
import com.qiyuely.remex.utils.StringUtils;
import com.qiyuely.ums.dao.UrlManagerDao;
import com.qiyuely.ums.dao.UrlTypeRelDao;
import com.qiyuely.ums.dto.sys.SysOpSettingHistoryDto;
import com.qiyuely.ums.dto.url.UrlAllInfoDto;
import com.qiyuely.ums.dto.url.UrlDto;
import com.qiyuely.ums.dto.url.UrlTypeDto;
import com.qiyuely.ums.dto.url.UrlTypeTempDto;
import com.qiyuely.ums.entity.url.UrlEntity;
import com.qiyuely.ums.entity.url.UrlTypeRelEntity;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlCreateReq;
import com.qiyuely.ums.req.url.UrlDeleteReq;
import com.qiyuely.ums.req.url.UrlUpdateReq;
import com.qiyuely.ums.service.SysOpSettingHistoryService;
import com.qiyuely.ums.service.UrlManagerService;
import com.qiyuely.ums.service.UrlTypeService;
import com.qiyuely.ums.service.UrlTypeTempService;
import com.qiyuely.ums.utils.IdUtil;

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
	
	@Autowired
	private UrlTypeRelDao urlTypeRelDao;
	
	@Autowired
	private UrlTypeService urlTypeService;
	
	@Autowired
	private UrlTypeTempService urlTypeTempService;
	
	@Autowired
	private SysOpSettingHistoryService sysOpSettingHistoryService;

	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public Result<List<UrlDto>> queryList() {
		List<UrlDto> dtoList = new ArrayList<>();
		
		List<UrlEntity> list = urlManagerDao.queryList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (UrlEntity entity : list) {
				UrlDto dto = new UrlDto();
				fillCreate(dto, entity);
				
				//查询url类型id列表
				List<String> typeIdList = urlTypeRelDao.queryUrlTypeIdListByUrlId(entity.getId());
				dto.setTypeIdList(typeIdList);;
				
				dtoList.add(dto);
			}
		}
		return packResult(dtoList);
	}

	/**
	 * 创建url
	 * @param entity
	 * @return
	 */
	@Transactional
	@Override
	public Result<UrlDto> createUrl(UrlCreateReq req) {
		UrlEntity entity = new UrlEntity();
		entity.setId(IdUtil.createId());
		entity.setName(req.getName());
		entity.setUrl(req.getUrl());
		entity.setRemark(req.getRemark());
		
		urlManagerDao.insert(entity);
		
		//保存url类型关系信息
		if (CollectionUtils.isNotEmpty(req.getTypeIdList())) {
			for (String urlTypeId : req.getTypeIdList()) {
				if (StringUtils.isBlank(urlTypeId)) {
					continue;
				}
				UrlTypeRelEntity urlTypeRelEntity = new UrlTypeRelEntity();
				urlTypeRelEntity.setUrlId(entity.getId());
				urlTypeRelEntity.setUrlTypeId(urlTypeId);
				urlTypeRelDao.insert(urlTypeRelEntity);
			}
		}
		
		UrlDto dto = new UrlDto();
		fillCreate(dto, entity);
		dto.setTypeIdList(req.getTypeIdList());
		return packResult(dto);
	}

	/**
	 * 修改url
	 * @param entity
	 * @return
	 */
	@Transactional
	@Override
	public Result<Void> updateUrl(UrlUpdateReq req) {
		UrlEntity entity = new UrlEntity();
		fillCreate(entity, req);
		
		urlManagerDao.update(entity);
		
		//删除此url的所有url类型关系信息
		urlTypeRelDao.deleteByUrlId(req.getId());
		
		//保存url类型关系信息
		if (CollectionUtils.isNotEmpty(req.getTypeIdList())) {
			for (String urlTypeId : req.getTypeIdList()) {
				if (StringUtils.isBlank(urlTypeId)) {
					continue;
				}
				UrlTypeRelEntity urlTypeRelEntity = new UrlTypeRelEntity();
				urlTypeRelEntity.setUrlId(entity.getId());
				urlTypeRelEntity.setUrlTypeId(urlTypeId);
				urlTypeRelDao.insert(urlTypeRelEntity);
			}
		}
		
		return packResult();
	}

	/**
	 * 删除url
	 * @param entity
	 * @return
	 */
	@Transactional
	@Override
	public Result<Void> deleteUrl(UrlDeleteReq req) {
		urlManagerDao.delete(req.getId());
		
		//删除此url的所有url类型关系信息
		urlTypeRelDao.deleteByUrlId(req.getId());
				
		return packResult();
	}
	
	/**
	 * 查询url相关全部信息
	 * @return
	 */
	@Override
	public Result<UrlAllInfoDto> queryUrlAllInfo() {
		UrlAllInfoDto dto = new UrlAllInfoDto();
		
		//url类型列表
		List<UrlTypeDto> typeList = urlTypeService.queryUrlTypeInfo().getData();
		dto.setTypeList(typeList);
		
		//url类型模板列表
		List<UrlTypeTempDto> typeTempList = urlTypeTempService.queryUrlTypeTempInfo().getData();
		dto.setTypeTempList(typeTempList);
		
		//操作设定信息列表
		List<SysOpSettingHistoryDto> opSettingHistoryList = sysOpSettingHistoryService.queryOpSettingHistoryList().getData();
		dto.setOpSettingHistoryList(opSettingHistoryList);
		
		//url信息列表
		List<UrlDto> urlList = queryList().getData();
		dto.setUrlList(urlList);
		
		return packResult(dto);
	}
	
	
	
	
	
	
	
	
	/**
	 * 填充创建数据
	 */
	private void fillCreate(UrlDto dto, UrlEntity entity) {
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUrl(entity.getUrl());
		dto.setRemark(entity.getRemark());
	}
	
	/**
	 * 填充创建数据
	 */
	private void fillCreate(UrlEntity entity, UrlUpdateReq req) {
		entity.setId(req.getId());
		entity.setName(req.getName());
		entity.setUrl(req.getUrl());
		entity.setRemark(req.getRemark());
	}
}
