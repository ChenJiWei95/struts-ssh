package com.shop.control.fore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shop.Constant;
import com.shop.control.SuperActionSupport;
import com.shop.entity.User;

/**
 * @version: V-1.0 
 * @Description: 
 * 这个类值提供main方法，其他均不支持<br>
 * 将会访问fore文件下的主要页面，如主页，登录注册、错误页面等。<br>
 * 类名称是固定的写法--ForeAction，如果调用main的方法就会取前缀，作为路径参考<br>
 * @author: cjw 
 * @date: 2019年10月30日 上午11:35:09
 */
@Component 				// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class ForeAction extends SuperActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 7539369474585568995L;
	
	private static final Logger log = Logger.getLogger(ForeAction.class); // 日志对象
	
	private HttpServletRequest request;
	
	
	
	@Override  
    public void setServletRequest(HttpServletRequest arg0) {  
        request = arg0;  
    }  
	
    public HttpServletRequest getRequest() {   
        return request;  
    }
	
	public String main(){	
		log.info("----------------------"+request.getSession().getAttribute("WW_TRANS_I18N_LOCALE"));
		/*log.info("主要页面跳转：");
		log.info("跳转完毕：");*/
		backhaul(request);
		return MAIN;
	}
	
	public void index() {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute(Constant.LOGIN_SIGN);
		session.setAttribute("username", user.getUsername());
	}
}
