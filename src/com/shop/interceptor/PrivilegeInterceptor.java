package com.shop.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shop.Constant;
import com.shop.control.SuperActionSupport;

public class PrivilegeInterceptor extends AbstractInterceptor {
	 
	private static final long serialVersionUID = 5543269481004367532L;
	private static Logger log = Logger.getLogger(CommonInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/*ActionContext ac = invocation.getInvocationContext();*/
		HttpServletRequest request= (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		/*
		if(no login)
			return login
		*/
		String uri = request.getRequestURI();
		Integer index;
		if(null != request.getSession().getAttribute(Constant.LOGIN_SIGN)) {
			if((index = uri.indexOf("_")) != -1) {
				int endIndex;
				if((endIndex = uri.indexOf("_", index)) != -1) {
					String targetDo = uri.substring(index+1, endIndex);
					if("main".equals(targetDo) || "chtml".equals(targetDo)) {
						log.info("进行授权判断："+request.getSession().getAttribute("URIPath"));
						if("register".equals(uri.substring(endIndex+1)) || "login".equals(uri.substring(endIndex+1)) || null != request.getSession().getAttribute(Constant.LOGIN_SIGN)) 
							return invocation.invoke();
					}
				} else {
					// 数据请求
					return JSON
				}
			}
		}
		
		log.info("进行授权判断："+request.getSession().getAttribute("URIPath")+"未授权");
		return SuperActionSupport.LOGIN;
	} 	
}
