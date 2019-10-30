package com.shop.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class I18nFilter implements Filter {
	@Override
	public void destroy() {
		System.out.println("销毁》》》》");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
		FilterChain filterChain) throws IOException, ServletException { 
		HttpServletRequest r = (HttpServletRequest) req;
		MyRequestWrapper request = new MyRequestWrapper(r);
		System.out.println("I18nFilter拦截器正在处理》》》》");
		filterChain.doFilter(request, resp);
	}
}

class MyRequestWrapper extends HttpServletRequestWrapper {
	  
    private Locale locale = null;
  
    public MyRequestWrapper(HttpServletRequest request) {
        super(request);
        HttpSession session = request.getSession();
        locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
    }
  
    /** 
     * struts2的BUG，如果重定向的话，国际化默认取HTTP请求头中的参数 替换HTTP请求参数
     **/
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if ("Accept-Language".equals(name) && locale != null) {
            value = locale.getLanguage() + "_" + locale.getCountry()
                    + value.substring(6, value.length());
        }
        return value;
    }
  
    @Override
    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }
        return super.getLocale();
    }
  
}
