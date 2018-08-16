package com.qiyuely.ums.req.url;

import java.io.Serializable;

/**
 * url类型创建请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeCreateReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 类型名称 */
	private String name;
	
	/** 父编号 */
	private String parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
