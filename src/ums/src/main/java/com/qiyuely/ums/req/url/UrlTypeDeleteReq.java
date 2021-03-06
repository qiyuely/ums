package com.qiyuely.ums.req.url;

import java.io.Serializable;

/**
 * url类型删除请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeDeleteReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** id */
	private String id;

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
