package com.qiyuely.ums.entity.url;

import java.sql.Timestamp;

/**
 * url entity
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlEntity {
	
	/** id */
	private String id;
	
	/** 名称 */
	private String name;
	
	/** url */
	private String url;
	
	/** 创建时间 */
	private Timestamp createTime;
	
	/** 备注 */
	private String remark;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
}
