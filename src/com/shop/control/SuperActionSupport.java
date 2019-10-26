package com.shop.control;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.util.Message;
/**
 * Action父类，当请求需要返回数据时继承此类<b>
 * <p>
 * 描述:<br>
 * 该类为模板类
 * <p>
 * 进行ajax请求时继承此类
 * @author 威 
 * <br>2018年3月16日 下午5:23:17 
 * @see
 * @since 1.0
 */ 
public class SuperActionSupport extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	/*
	inputStream属性和配置文件中的inputStream对应，并且需要gettersetter方法getInputStream，setInputStream
	<action name="testAction" class="testAction">
		<result type="stream">
	    	<param name="contentType">text/html</param>
	   		<param name="inputName">inputStream</param>
	  	</result>  
	</action>
	*/
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	private Message message;
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * ajax请求时   str即为返回响应数据
	 * <p>	 
	 * @param str
	 * void
	 * @see
	 * @since 1.0
	 */
	public void print(String str){
		try {
			inputStream = new ByteArrayInputStream(str.getBytes("UTF-8")); // 解决中文乱码问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回失败状态
	 * <p>	 
	 * @param callbackName	回调方法名
	 * @param result		返回数据
	 * void
	 * @see
	 * @since 1.0
	 */
	/*public void printErr(String callbackName, String result){
		inputStream = new StringBufferInputStream(com.shop.util.FormAjaxUtil.failAccess(callbackName, result));
	}*/
	
	/**
	 * 返回成功状态
	 * <p>	 
	 * @param callbackName	回调方法名
	 * @param result		返回数据
	 * void
	 * @see
	 * @since 1.0
	 */
	/*public void printOk(String callbackName, String result){
		inputStream = new StringBufferInputStream(com.shop.util.FormAjaxUtil.successAccess(callbackName, result));
	}*/
	
	public Object getBean(String beanName){
		return com.shop.util.BeanUtil.getBean(beanName);
	}
	
}
