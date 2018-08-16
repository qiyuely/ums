package com.qiyuely.ums.req.url;

import java.io.Serializable;

/**
 * url类型修改请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeUpdateReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** id */
	private String id;
	
	/** 类型名称 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
