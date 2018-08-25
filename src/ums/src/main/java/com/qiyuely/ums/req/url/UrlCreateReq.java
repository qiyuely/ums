package com.qiyuely.ums.req.url;

import java.io.Serializable;
import java.util.List;

/**
 * url创建请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlCreateReq implements Serializable {
	private static final long serialVersionUID = 1L;

	/** url */
	private String url;
	
	/** 名称 */
	private String name;
	
	/** 备注 */
	private String remark;
	
	/** url类型编号列表 */
	private List<String> typeIdList;
	
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
	
	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}
	
	public List<String> getTypeIdList() {
		return typeIdList;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
