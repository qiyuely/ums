package com.qiyuely.ums.req.url;

import java.io.Serializable;
import java.util.List;

/**
 * url类型模板关系修改请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeTempUpdateRelReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** id */
	private String id;
	
	/** url类型编号列表 */
	private List<String> typeIdList;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}
	
	public List<String> getTypeIdList() {
		return typeIdList;
	}
}
