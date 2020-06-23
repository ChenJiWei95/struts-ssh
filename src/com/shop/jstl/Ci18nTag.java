package com.shop.jstl;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import com.shop.Constants;
import com.shop.util.SpringUtils;
import com.shop.util.i18n.AbstractCi18nCore;

public class Ci18nTag extends SimpleTagSupport {
	
	private Logger log = Logger.getLogger(Ci18nTag.class);
	
	// 标签属性text
	private String name;
	
	public void setName(String text) {
		this.name = text;
	}

	// 内容体
	StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		log.info("正在使用Ci18nTag");
		JspWriter out = getJspContext().getOut();
		String currentCode = "";
		if(name != null){
			currentCode = name;
		} else {
			currentCode = sw.toString();
		}
		log.info(currentCode);
		if(currentCode == null || "".equals(currentCode)){
			out.println("");
			return;
		}
		
		String value = ((AbstractCi18nCore) SpringUtils.getBean(Constants.CI18N_CLASS_NAME)).execute(currentCode);
		
		log.info("输出："+value);
		out.println(value);
	}

}
