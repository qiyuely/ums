package com.qiyuely.ums.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiyuely.remex.utils.CollectionUtils;
import com.qiyuely.remex.utils.StringUtils;
import com.qiyuely.ums.dao.UrlTypeDao;
import com.qiyuely.ums.dto.url.UrlTypeDto;
import com.qiyuely.ums.entity.url.UrlTypeEntity;
import com.qiyuely.ums.framework.BaseService;
import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.req.url.UrlTypeCreateReq;
import com.qiyuely.ums.req.url.UrlTypeDeleteReq;
import com.qiyuely.ums.req.url.UrlTypeUpdateReq;
import com.qiyuely.ums.service.UrlTypeService;
import com.qiyuely.ums.utils.IdUtil;

/**
 * url类型管理service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class UrlTypeServiceImpl extends BaseService implements UrlTypeService {

	@Autowired
	private UrlTypeDao urlTypeDao;
	
	/**
	 * 查询url类型信息
	 */
	@Override
	public Result<List<UrlTypeDto>> queryUrlTypeInfo() {
		List<UrlTypeEntity> list = urlTypeDao.queryList();
		
		List<UrlTypeDto> dtoList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(list)) {
			//递归组装url类型数据
			for (UrlTypeEntity entity : list) {
				//第一级节点
				if (StringUtils.isBlank(entity.getParentId())) {
					UrlTypeDto dto = new UrlTypeDto();
					fillCreate(dto, entity);
					
					//递归封装下一节点的url类型数据
					packUrlType(dto, list);
					
					dtoList.add(dto);
				}
			}
		}
		
		return packResult(dtoList);
	}
	
	/**
	 * 创建url类型
	 * @param entity
	 * @return
	 */
	@Override
	public Result<UrlTypeDto> createUrlType(UrlTypeCreateReq req) {
		UrlTypeEntity entityByName = urlTypeDao.findByName(req.getName());
		if (entityByName != null) {
			return errorResult("该父节点下已存在同名的节点");
		}
		
		UrlTypeEntity entity = new UrlTypeEntity();
		entity.setId(IdUtil.createId());
		entity.setName(req.getName());
		entity.setParentId(req.getParentId());
		
		urlTypeDao.insert(entity);
		
		UrlTypeDto dto = new UrlTypeDto();
		fillCreate(dto, entity);
		return packResult(dto);
	}
	
	/**
	 * 修改url类型
	 * @param entity
	 * @return
	 */
	@Override
	public Result<Void> updateUrlType(UrlTypeUpdateReq req) {
		UrlTypeEntity entityByName = urlTypeDao.findByName(req.getName());
		if (entityByName != null && !req.getId().equals(entityByName.getId())) {
			return errorResult("该父节点下已存在同名的节点");
		}
		
		UrlTypeEntity entity = new UrlTypeEntity();
		entity.setId(req.getId());
		entity.setName(req.getName());
		
		urlTypeDao.update(entity);
		
		return packResult();
	}
	
	/**
	 * 删除url类型
	 * @param entity
	 * @return
	 */
	@Override
	public Result<Void> deleteUrlType(UrlTypeDeleteReq req) {
		urlTypeDao.delete(req.getId());
		return packResult();
	}
	
	
	
	
	/**
	 * 递归组装url类型数据
	 * @param parentDto
	 * @param list
	 */
	private void packUrlType(UrlTypeDto parentDto, List<UrlTypeEntity> list) {
		for (UrlTypeEntity entity : list) {
			//属于此节点的子节点
			if (parentDto.getId().equals(entity.getParentId())) {
				UrlTypeDto curDto = new UrlTypeDto();
				fillCreate(curDto, entity);
				curDto.setParentName(parentDto.getName());
				
				//递归封装下一节点的url类型数据
				packUrlType(curDto, list);
				
				parentDto.getChildList().add(curDto);
			}
		}
	}
	
	
	
	
	
	/**
	 * 填充创建数据
	 */
	private void fillCreate(UrlTypeDto dto, UrlTypeEntity entity) {
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setParentId(entity.getParentId());
	}
}
