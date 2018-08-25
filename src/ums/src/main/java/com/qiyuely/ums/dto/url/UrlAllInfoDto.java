package com.qiyuely.ums.dto.url;

import java.util.List;

import com.qiyuely.ums.dto.sys.SysOpSettingHistoryDto;

/**
 * url相关全部信息业务实体
 *
 * @author Qiaoxin.Hong
 *
 */
public class UrlAllInfoDto {
	
	/** url信息列表 */
	private List<UrlDto> urlList;
	
	/** url类型列表 */
	private List<UrlTypeDto> typeList;
	
	/** url类型模板列表 */
	private List<UrlTypeTempDto> typeTempList;
	
	/** 操作设定信息列表 */
	private List<SysOpSettingHistoryDto> opSettingHistoryList;

	public List<UrlTypeDto> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<UrlTypeDto> typeList) {
		this.typeList = typeList;
	}

	public List<UrlTypeTempDto> getTypeTempList() {
		return typeTempList;
	}

	public void setTypeTempList(List<UrlTypeTempDto> typeTempList) {
		this.typeTempList = typeTempList;
	}

	public List<SysOpSettingHistoryDto> getOpSettingHistoryList() {
		return opSettingHistoryList;
	}

	public void setOpSettingHistoryList(List<SysOpSettingHistoryDto> opSettingHistoryList) {
		this.opSettingHistoryList = opSettingHistoryList;
	}
	
	public void setUrlList(List<UrlDto> urlList) {
		this.urlList = urlList;
	}
	
	public List<UrlDto> getUrlList() {
		return urlList;
	}
}
