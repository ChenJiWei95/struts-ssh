package com.shop.util;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

/**
 * <b>为FormAjax返回参数提供封装支持</b>
 * <p>
 * 描述:<br>
 * 	获取callbackObj字段
 *	String callbackObj = ((String[]) ActionContext.getContext().
 *	  getParameters().get("callbackObj"))[0];
 * 	调用successAccess方法获取成功的js数据；"true"为自定返回信息
 *		FormAjaxUtil.successAccess(callbackObj, "true")
 *	调用failAccess方法获取失败的js数据；"用户不存在"为自定返回信息
 *		FormAjaxUtil.failAccess(callbackObj, "用户不存在")
 * @author 威 
 * <br>2017年12月1日 下午12:54:14 
 * @since 1.0
 */
public class FormAjaxUtil {
	
	/**
	 * 
	 * 成功请求返回的字符串包括要传回去的数据 
	 * @see
	 * @param obj				当前的FormAjax对象
	 * @param resInformate		
	 * @return
	 * String
	 *
	 */
	public static String successAccess(String obj, String resInformate){
		
		return "<script>parent."+ (obj == null ? "" : obj + ".") +
				"msg('" + UnicodeUtil.parseU(resInformate) + "') ;</script>" ;
	}
	public static String failAccess(String obj, String resInformate){
		return "<script>parent."+ (obj == null ? "" : obj + ".") +
				"fail('" + UnicodeUtil.parseU(resInformate) + "') ;</script>" ;
	}
}
