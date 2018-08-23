package com.qiyuely.ums.req.url;

import java.io.Serializable;
import java.util.List;

/**
 * url类型模板创建请求参数
 * 
 * @author Qiaoxin.Hong
 *
 */
public class UrlTypeTempCreateReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 类型名称 */
	private String name;
	
	/** url类型编号列表 */
	private List<String> typeIdList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTypeIdList() {
		return typeIdList;
	}

	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
