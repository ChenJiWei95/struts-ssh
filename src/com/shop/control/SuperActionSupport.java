package com.shop.control;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.util.BeanUtil;
/**
 * 
 * <b>Action父类，当请求需要返回数据时继承此类 该类为模板类<b>
 * @author 威 
 * <br>2018年3月16日 下午5:23:17 
 *
 */
@SuppressWarnings("deprecation")
public class SuperActionSupport extends ActionSupport {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	private InputStream inputStream ;
	
	public InputStream getInputStream() {
		return inputStream ;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream ;
	}

	public void print(String str){
		inputStream = new StringBufferInputStream(str) ;
	}
	
	public Object getBean(String beanName){
		return com.shop.util.BeanUtil.getBean(beanName) ;
	}
}
