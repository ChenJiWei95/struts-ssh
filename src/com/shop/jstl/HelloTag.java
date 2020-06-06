package com.shop.jstl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

/**
 * <b>简单测试</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2020年6月6日 下午12:43:11 
 * @see
 * @since 1.0
 */
public class HelloTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		
		JspWriter out = getJspContext().getOut();
	    out.println("Hello Custom Tag!");
	    
	}
}
