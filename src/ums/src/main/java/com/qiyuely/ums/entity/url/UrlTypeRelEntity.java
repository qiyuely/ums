package com.qiyuely.ums.entity.url;

import java.io.Serializable;

/**
 * url类型关系entity
 * @author Administrator
 *
 */
public class UrlTypeRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** url id */
	private String urlId;
	
	/** url类型id */
	private String urlTypeId;

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getUrlTypeId() {
		return urlTypeId;
	}

	public void setUrlTypeId(String urlTypeId) {
		this.urlTypeId = urlTypeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
