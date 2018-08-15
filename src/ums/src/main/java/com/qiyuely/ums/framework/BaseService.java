package com.qiyuely.ums.framework;

import com.qiyuely.ums.framework.result.Result;

/**
 * service基础类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class BaseService {
	
	/**
	 * 创建默认成功的结果集
	 * @param data
	 * @return
	 */
	protected <T> Result<T> packResult() {
		return packResult(null);
	}
	
	/**
	 * 创建默认成功的结果集
	 * @param data
	 * @return
	 */
	protected <T> Result<T> packResult(T data) {
		return new Result<T>(data);
	}
}
