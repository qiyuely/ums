package com.qiyuely.ums.dto.url;

import java.io.Serializable;
import java.util.List;

/**
 * url业务实体
 * @author Administrator
 *
 */
public class UrlDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** url */
	private String url;
	
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
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setTypeIdList(List<String> typeIdList) {
		this.typeIdList = typeIdList;
	}
	
	public List<String> getTypeIdList() {
		return typeIdList;
	}
}
