package com.qiyuely.ums.dto.url;

import java.util.List;

/**
 * url类型模板业务实体
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeTempDto {

	/** id */
	private String id;
	
	/** 类型模板名称 */
	private String name;
	
	/** url类型编号列表 */
	private List<String> typeIdList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}
	
	public List<String> getTypeIdList() {
		return typeIdList;
	}
}
