package com.qiyuely.ums.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiyuely.remex.utils.CollectionUtils;
import com.qiyuely.remex.utils.StringUtils;
import com.qiyuely.ums.dao.UrlTypeTempDao;
import com.qiyuely.ums.dao.UrlTypeTempRelDao;
import com.qiyuely.ums.dto.url.UrlTypeTempDto;
import com.qiyuely.ums.entity.url.UrlTypeTempEntity;
import com.qiyuely.ums.entity.url.UrlTypeTempRelEntity;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeTempCreateReq;
import com.qiyuely.ums.req.url.UrlTypeTempDeleteReq;
import com.qiyuely.ums.req.url.UrlTypeTempUpdateRelReq;
import com.qiyuely.ums.req.url.UrlTypeTempUpdateReq;
import com.qiyuely.ums.service.UrlTypeTempService;
import com.qiyuely.ums.utils.IdUtil;

/**
 * url类型模板service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class UrlTypeTempServiceImpl extends BaseService implements UrlTypeTempService {
	
	@Autowired
	private UrlTypeTempDao urlTypeTempDao;
	
	@Autowired
	private UrlTypeTempRelDao urlTypeTempRelDao;

	/**
	 * 查询url类型模板信息
	 */
	@Override
	public Result<List<UrlTypeTempDto>> queryUrlTypeTempInfo() {
		List<UrlTypeTempDto> dtoList = new ArrayList<>();
		
		List<UrlTypeTempEntity> list = urlTypeTempDao.queryList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (UrlTypeTempEntity entity : list) {
				UrlTypeTempDto dto = new UrlTypeTempDto();
				fillCreate(dto, entity);
				
				//查询url类型id列表
				List<String> typeIdList = urlTypeTempRelDao.queryUrlTypeIdListByTempId(entity.getId());
				dto.setTypeIdList(typeIdList);
				
				dtoList.add(dto);
			}
		}
		return packResult(dtoList);
	}

	/**
	 * 创建url类型模板
	 */
	@Override
	public Result<UrlTypeTempDto> createUrlTypeTemp(UrlTypeTempCreateReq req) {
		UrlTypeTempEntity entityByName = urlTypeTempDao.findByName(req.getName());
		if (entityByName != null) {
			return errorResult("该父节点下已存在同名的节点");
		}
		
		UrlTypeTempEntity entity = new UrlTypeTempEntity();
		entity.setId(IdUtil.createId());
		entity.setName(req.getName());
		
		urlTypeTempDao.insert(entity);
		
		if (CollectionUtils.isNotEmpty(req.getTypeIdList())) {
			for (String urlTypeId : req.getTypeIdList()) {
				if (StringUtils.isBlank(urlTypeId)) {
					continue;
				}
				UrlTypeTempRelEntity urlTypeRelEntity = new UrlTypeTempRelEntity();
				urlTypeRelEntity.setUrlTypeTempId(entity.getId());
				urlTypeRelEntity.setUrlTypeId(urlTypeId);
				urlTypeTempRelDao.insert(urlTypeRelEntity);
			}
		}
		
		UrlTypeTempDto dto = new UrlTypeTempDto();
		fillCreate(dto, entity);
		dto.setTypeIdList(req.getTypeIdList());
		return packResult(dto);
	}

	/**
	 * 修改url类型模板
	 */
	@Override
	public Result<Void> updateUrlTypeTemp(UrlTypeTempUpdateReq req) {
		UrlTypeTempEntity entityByName = urlTypeTempDao.findByName(req.getName());
		if (entityByName != null && !req.getId().equals(entityByName.getId())) {
			return errorResult("该父节点下已存在同名的节点");
		}
		
		UrlTypeTempEntity entity = new UrlTypeTempEntity();
		entity.setId(req.getId());
		entity.setName(req.getName());
		
		urlTypeTempDao.update(entity);
		
		//根据url类型模板id删除所有的url类型关系
		urlTypeTempRelDao.deleteByTempId(req.getId());
		
		if (CollectionUtils.isNotEmpty(req.getTypeIdList())) {
			for (String urlTypeId : req.getTypeIdList()) {
				if (StringUtils.isBlank(urlTypeId)) {
					continue;
				}
				UrlTypeTempRelEntity urlTypeRelEntity = new UrlTypeTempRelEntity();
				urlTypeRelEntity.setUrlTypeTempId(entity.getId());
				urlTypeRelEntity.setUrlTypeId(urlTypeId);
				urlTypeTempRelDao.insert(urlTypeRelEntity);
			}
		}
		
		return packResult();
	}

	/**
	 * 删除url类型模板
	 */
	@Override
	public Result<Void> deleteUrlTypeTemp(UrlTypeTempDeleteReq req) {
		urlTypeTempDao.delete(req.getId());
		
		//根据url类型模板id删除所有的url类型关系
		urlTypeTempRelDao.deleteByTempId(req.getId());
		
		return packResult();
	}
	
	/**
	 * 修改url类型模板关系
	 * @param entity
	 * @return
	 */
	@Override
	public Result<Void> updateUrlTypeTempRel(UrlTypeTempUpdateRelReq req) {
		//根据url类型模板id删除所有的url类型关系
		urlTypeTempRelDao.deleteByTempId(req.getId());

		if (CollectionUtils.isNotEmpty(req.getTypeIdList())) {
			for (String urlTypeId : req.getTypeIdList()) {
				if (StringUtils.isBlank(urlTypeId)) {
					continue;
				}
				UrlTypeTempRelEntity urlTypeRelEntity = new UrlTypeTempRelEntity();
				urlTypeRelEntity.setUrlTypeTempId(req.getId());
				urlTypeRelEntity.setUrlTypeId(urlTypeId);
				urlTypeTempRelDao.insert(urlTypeRelEntity);
			}
		}
		
		return packResult();
	}
	

	/**
	 * 填充创建数据
	 */
	private void fillCreate(UrlTypeTempDto dto, UrlTypeTempEntity entity) {
		dto.setId(entity.getId());
		dto.setName(entity.getName());
	}
}
