package com.qiyuely.ums.req.url;

import java.io.Serializable;

/**
 * url创建请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlDeleteReq implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
