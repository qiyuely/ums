package com.qiyuely.ums.framework.result;

import java.io.Serializable;

/**
 * 结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 结果key */
	private String key;
	
	/** 结果消息 */
	private String msg;
	
	/** 结果数据 */
	private T data;

	/**
	 * 创建默认成功的结果集
	 */
	public Result() {
		super();
		this.key = ResultKey.SUCCESS;
	}

	/**
	 * 创建默认成功的结果集
	 */
	public Result(T data) {
		this();
		this.data = data;
	}

	/**
	 * 创建默认失败的结果集
	 * @param msg
	 */
	public Result(String msg) {
		this.key = ResultKey.FAILED;
		this.msg = msg;
	}

	/**
	 * 创建结果集
	 * @param key
	 * @param msg
	 */
	public Result(String key, String msg) {
		this.key = key;
		this.msg = msg;
	}

	/**
	 * 创建结果集
	 * @param key
	 * @param msg
	 */
	public Result(String key, String msg, T data) {
		this.key = key;
		this.msg = msg;
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
}
