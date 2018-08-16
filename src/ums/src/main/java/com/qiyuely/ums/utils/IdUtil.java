package com.qiyuely.ums.utils;

import java.util.UUID;

/**
 * 主键工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class IdUtil {

	/**
	 * 创建主键编号
	 * @return
	 */
	public static String createId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
