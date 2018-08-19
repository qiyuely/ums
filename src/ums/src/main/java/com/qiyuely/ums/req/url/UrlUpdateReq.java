package com.qiyuely.ums.req.url;

import java.io.Serializable;

/**
 * url创建请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlUpdateReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** id */
	private String id;

	/** url */
	private String url;
	
	/** 备注 */
	private String remark;
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUrl() {
		return url;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
