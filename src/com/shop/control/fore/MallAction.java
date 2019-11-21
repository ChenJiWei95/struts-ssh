package com.shop.control.fore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shop.Constants;
import com.shop.control.SuperActionSupport;
import com.shop.entity.Goods;
import com.shop.entity.User;
import com.shop.service.CartListService;
import com.shop.service.GoodsService;
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
	
	@Autowired
	private GoodsService goodsServiceImpl;
	
	@Autowired
	private CartListService cartListServiceImpl; 
	
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
	
	public void goodsdetails(){
		HttpSession session = (HttpSession) request.getSession();
		Goods goods = goodsServiceImpl.get(request.getParameter("id"));
		session.setAttribute("goods", goods);
		
		User user = (User) session.getAttribute(Constants.LOGIN_SIGN);
		request.getSession().setAttribute("cartListCount", cartListServiceImpl.count("from CartList where userId = ?", user.getId()));
		
	}
	public void furnishing(){
		// 调用家居页面
		StringBuilder eq = new StringBuilder("from Goods where type=?");
		List<Goods> goodsList = goodsServiceImpl.findList(eq.toString(), "01");
		request.getSession().setAttribute("goodsList", goodsList);
	}
	public void homeappliances(){
		StringBuilder eq = new StringBuilder("from Goods where type=?");
		List<Goods> goodsList = goodsServiceImpl.findList(eq.toString(), "02");
		request.getSession().setAttribute("goodsList", goodsList);
	}
	public void washandcare(){
		StringBuilder eq = new StringBuilder("from Goods where type=?");
		List<Goods> goodsList = goodsServiceImpl.findList(eq.toString(), "03");
		request.getSession().setAttribute("goodsList", goodsList);
	}
	public void kitchenutensils(){
		StringBuilder eq = new StringBuilder("from Goods where type=?");
		List<Goods> goodsList = goodsServiceImpl.findList(eq.toString(), "04");
		request.getSession().setAttribute("goodsList", goodsList);
	}
	public void necessities(){
		StringBuilder eq = new StringBuilder("from Goods where type=?");
		List<Goods> goodsList = goodsServiceImpl.findList(eq.toString(), "05");
		request.getSession().setAttribute("goodsList", goodsList);
	}

}
