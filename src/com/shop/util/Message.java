package com.shop.util;

/**
 * 信息消息
 * @className Message
 * @Description
 * @author rjht
 * @contact qq 676342073
 * @date 2014-10-27 下午12:04:01
 */
public class Message { 
	
	private String code;

	/** 内容 */
	private String msg;

	private Object data;
	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public Message() {

	}

	/**
	 * 初始化一个新创建的 Message 对象
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 */
	public Message(String code, String content) {
		this.code = code;
		this.msg = content;
	}

	/**
	 * @param code
	 *            类型
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 */
	public Message(String code, String content, Object data) {
		this.code = code;
		this.msg = content;
		this.data = data;
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(String content) {
		return new Message("0", content, null);
	}
	
	public static Message success(String content, Object data) {
		return new Message("0", content, data);
	}

	/**
	 * 返回警告消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 警告消息
	 */
	public static Message warn(String content, Object data) {
		return new Message("1", content, data);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message error(String content) {
		return new Message("2", content, null);
	}
	public static Message error(String content, Object data) {
		return new Message("2", content, data);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}