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
//		UrlTypeCreateReq createReq = new UrlTypeCreateReq();
//		createReq.setParentId("61a26a5b0ecd4b0486dca0201062c7fa");
//		createReq.setName("节点2");
//		createUrlType(createReq);
		
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
	public Result<Void> createUrlType(UrlTypeCreateReq req) {
		UrlTypeEntity entity = new UrlTypeEntity();
		entity.setId(IdUtil.createId());
		entity.setName(req.getName());
		entity.setParentId(req.getParentId());
		
		urlTypeDao.insert(entity);
		
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