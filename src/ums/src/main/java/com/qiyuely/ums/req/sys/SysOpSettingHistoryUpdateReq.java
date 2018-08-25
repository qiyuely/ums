package com.qiyuely.ums.req.sys;

/**
 * 操作设定历史修改请求参数
 *
 * @author Qiaoxin.Hong
 *
 */
public class SysOpSettingHistoryUpdateReq {
	
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
