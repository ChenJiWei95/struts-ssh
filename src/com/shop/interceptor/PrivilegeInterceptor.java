package com.shop.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shop.Constants;
import com.shop.control.SuperActionSupport;
import com.shop.util.Message;

/**
 * <b>权限判断 未登录跳转 除了白名单</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2020年3月17日 下午9:43:33 
 * @see
 * @since 1.0
 */
public class PrivilegeInterceptor extends AbstractInterceptor {
	 
	private static final long serialVersionUID = 5543269481004367532L;
	private static Logger log = Logger.getLogger(CommonInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		/*ActionContext ac = invocation.getInvocationContext();*/
		HttpServletRequest request= (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		// 授权  通过  
		// 未授权 返回登录页面 赦免【register login】
		String uri = request.getRequestURI();
		if(null == request.getSession().getAttribute(Constants.LOGIN_SIGN)) {
			int endIndex;
			if((endIndex = uri.indexOf("_", uri.indexOf("_")+1)) != -1) {
				// 页面跳转
				String targetDo = uri.substring(endIndex+1);
				log.info("进行授权判断：targetDo="+targetDo);
				if("register".equals(targetDo) || "login".equals(targetDo)) {
					return invocation.invoke();
				} 
				return SuperActionSupport.LOGIN;	// 数据请求
			}  	
			if(!"login".equals(uri.substring(uri.indexOf("_")+1)) && !(uri.indexOf("user_save") != -1))
				return SuperActionSupport.LOGIN;
		}
		return invocation.invoke();
	} 
	/*
	 * 改进
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext ac = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		// 授权  通过  
		// 未授权 返回登录页面 赦免【register login】
		String uri = request.getRequestURI();
		String pageType = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf("_"));
		int endIndex;
		//判断是  ***_***_*** 格式
		if((endIndex = uri.indexOf("_", uri.indexOf("_")+1)) != -1) {
			String targetDo = uri.substring(endIndex+1);
			if("fore".equals(pageType)){
				if(null == request.getSession().getAttribute(Constants.LOGIN_SIGN)) {
					// 页面跳转
					log.info("进行授权判断：targetDo="+targetDo);
					if("register".equals(targetDo) || "login".equals(targetDo)) {
						return invocation.invoke();
					} 
					if(!"login".equals(uri.substring(uri.indexOf("_")+1)) && !(uri.indexOf("user_save") != -1))
						return SuperActionSupport.LOGIN;
					
					return SuperActionSupport.LOGIN;	// 数据请求	
					// 
				}
			}else if ("admin".equals(pageType)){
				if(null == request.getSession().getAttribute(Constants.ADMIN_LOGIN_SIGN)) {
					return SuperActionSupport.ADMIN_LOGIN;	// 数据请求
				}
				log.info("进行授权判断：进入管理后台");
				return invocation.invoke();
			}else {
				log.info("进行授权判断：页面不存在");
				// 页面不存在
				return SuperActionSupport.ERROR;
			}
			
		}  
		
		
		return invocation.invoke();
	} */	
}
