package com.qiyuely.ums.entity.url;

/**
 * url类型 entity
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeEntity {
	
	/** id */
	private String id;
	
	/** 类型名称 */
	private String name;
	
	/** 父编号 */
	private String parentId;

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
}
