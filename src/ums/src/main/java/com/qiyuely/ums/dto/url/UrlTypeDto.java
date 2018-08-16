package com.qiyuely.ums.dto.url;

import java.util.ArrayList;
import java.util.List;

/**
 * url类型业务实体
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeDto {
	
	/** id */
	private String id;
	
	/** 类型名称 */
	private String name;
	
	/** 父编号 */
	private String parentId;
	
	/** 父类型名称 */
	private String parentName;
	
	/** 子类型列表 */
	private List<UrlTypeDto> childList = new ArrayList<>();

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
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getParentId() {
		return parentId;
	}

	public List<UrlTypeDto> getChildList() {
		return childList;
	}

	public void setChildList(List<UrlTypeDto> childList) {
		this.childList = childList;
	}
	
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public String getParentName() {
		return parentName;
	}
}
