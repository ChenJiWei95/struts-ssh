package com.shop.control;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

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
	
	public static final String JSON 	= "json";		// ajax请求json格式数据返回
	public static final String STREAM 	= "stream";		// ajax请求一般数据流返回
	public static final String CHTML 	= "chtml"; 		// 页面跳转处理
	public static final String MAIN 	= "main"; 		// 主要页面跳转处理
	public static final String ERROR 	= "error"; 		// 错误页面
	
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
	 * 前端需要通过EL表达式获取后台值时调用此方法，根据请求地址末端反射同名方法 例如testParam_chtml_index， 则此时若想传值前台须有一个index方法
	 * <p>	 
	 * @param request 
	 * void
	 * @see
	 * @since 1.0
	 */
	protected void backhaul(HttpServletRequest request){
		Class<?> clazz = this.getClass();
		String path = request.getRequestURI();
		String name = path.substring(path.lastIndexOf("_")+1);
		try {
			Method method = clazz.getMethod(name, HttpServletRequest.class);
			if(method != null) method.invoke(this, request);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}	
	
	// 负责页面跳转 
	// 链接格式 当前类为例：testAjax(类前缀)_chtml(方法，固定为chtml)_success(跳转页面，这里以success.jsp为例)
	public String chtml(){
		backhaul(ServletActionContext.getRequest()); 
		return CHTML;
	}			
	
	public Object getBean(String beanName){
		return com.shop.util.BeanUtil.getBean(beanName);
	}
	
}
