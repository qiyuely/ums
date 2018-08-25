package com.qiyuely.ums.entity.sys;

/**
 * 操作设定历史 entity
 *
 * @author Qiaoxin.Hong
 *
 */
public class SysOpSettingHistoryEntity {

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
