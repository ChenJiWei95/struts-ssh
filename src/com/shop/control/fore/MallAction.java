package com.shop.control.fore;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shop.control.SuperActionSupport;
/**
 * @version: V 1.0 
 * @Description:
 * 只支持chtml方法 负责商城页面跳转
 * @author: cjw 
 * @date: 2019年10月30日 上午11:39:22
 */
@Component 				// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class MallAction extends SuperActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 7539369474585568995L;
	
	private static final Logger log = Logger.getLogger(MallAction.class); // 日志对象
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}
	public HttpServletRequest getRequest() {  
        return request;  
    }
	
	
	public String chtml(){
		backhaul(ServletActionContext.getRequest()); 
		return CHTML;
	}

}
