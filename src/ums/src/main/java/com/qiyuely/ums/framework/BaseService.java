package com.qiyuely.ums.framework;

import com.qiyuely.ums.framework.result.Result;
import com.qiyuely.ums.framework.result.ResultKey;

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
	
	/**
	 * 创建错误的结果集
	 * @return
	 */
	protected <T> Result<T> errorResult() {
		return new Result<>(ResultKey.FAILED, "未知错误");
	}
	
	/**
	 * 创建错误的结果集
	 * @return
	 */
	protected <T> Result<T> errorResult(String msg) {
		return new Result<>(ResultKey.FAILED, msg);
	}
	
	/**
	 * 创建错误的结果集
	 * @return
	 */
	protected <T> Result<T> errorResult(String key, String msg) {
		return new Result<>(key, msg);
	}
}
