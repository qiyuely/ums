package com.qiyuely.ums.dto.sys;

/**
 * 操作设定历史业务实体
 *
 * @author Qiaoxin.Hong
 *
 */
public class SysOpSettingHistoryDto {
	
	/** 操作主键 */
	private String opKey;
	
	/** 操作设定 */
	private String setting;

	public String getOpKey() {
		return opKey;
	}

	public void setOpKey(String opKey) {
		this.opKey = opKey;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}
}
