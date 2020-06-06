package com.shop.control.fore;

import java.util.ArrayList;
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
import com.shop.entity.CartList;
import com.shop.entity.Collection;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.entity.User;
import com.shop.service.CartListService;
import com.shop.service.CollectionService;
import com.shop.service.LogisticsService;
import com.shop.service.OrderItemService;
import com.shop.service.OrderService;
/**
 * @version: V 1.0 
 * @Description:
 * 只支持chtml方法 负责个人中心页面跳转
 * @author: cjw 
 * @date: 2019年10月30日 上午11:39:22
 */
@Component 				// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class UcenterAction extends SuperActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 7539369474585568995L;
	
	private static final Logger log = Logger.getLogger(UcenterAction.class); // 日志对象
	
	private HttpServletRequest request;
	
	@Autowired
	private CartListService cartListServiceImpl; 
	
	@Autowired
	private CollectionService collectionServiceImpl; 
	
	@Autowired
	private OrderService orderServiceImpl; 
	
	@Autowired
	private OrderItemService orderItemServiceImpl; 
	
	@Autowired
	private LogisticsService logisticsServiceImpl;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}
	public HttpServletRequest getRequest() {  
        return request;  
    }
	
	// 负责页面跳转 
	// 链接格式 当前类为例：testAjax(类前缀)_chtml(方法，固定为chtml)_success(跳转页面，这里以success.jsp为例)
	public String chtml(){
		backhaul(ServletActionContext.getRequest()); 
		return CHTML;
	}	
	
	public void shopcart(){
		User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
		StringBuilder hql = new StringBuilder("from CartList where 1 = 1 ");
		hql.append("AND userId = ?");
		List<CartList> cartlist = cartListServiceImpl.findList(hql.toString(), user.getId());
		request.getSession().setAttribute("cartlist", cartlist);
		
	}
	
	public void collection() {
		User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
		StringBuilder hql = new StringBuilder("from Collection where 1 = 1 ");
		hql.append("AND userId = ?");
		List<Collection> list = collectionServiceImpl.findList(hql.toString(), user.getId());
		request.getSession().setAttribute("collList", list);
	}
	
	public void pcenter(){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.LOGIN_SIGN);
		request.setAttribute("username", user.getUsername());
		
		// 待发货
		String countHql = "from Order where paymentStatus = '00' and logisticsStatus = (?) and uId = (?)";
		long deliveredCount = orderServiceImpl.count(countHql, "02",user.getId());
		request.setAttribute("delivered", deliveredCount);
		// 已发货
		long receivedCount = orderServiceImpl.count(countHql, "01",user.getId());
		request.setAttribute("received", receivedCount);
		// 优惠券
		request.setAttribute("coupon", 0);
		// 礼品卡
		request.setAttribute("giftCard", 0);
		// 积分
		request.setAttribute("integral", 10);
		// 待评价
		request.setAttribute("toBeEvaluate", 0);
		log.info("========================pcenter");
		
		List<Order> orders = orderServiceImpl.findList("from Order where uId = (?) ORDER BY create_date desc", user.getId());
		request.getSession().setAttribute("orders", orders);
		
		List<OrderItem> itemList = null;
		if(orders.size() > 0){
			// 收集order id 作为查询orders的依据
			StringBuilder hql = new StringBuilder("from OrderItem where");
			Object[] parame = new Object[orders.size()];
			for(int i = 0; i < orders.size(); i++){
				hql.append(" orderId=? OR");
				parame[i] = orders.get(i).getId();
			} 
			if(orders.size() > 0) hql.delete(hql.length()-3, hql.length());// 删除多余or 
			itemList = orderItemServiceImpl.findList(hql.toString(), parame);
		} else itemList = new ArrayList<>(0);
		session.setAttribute("orderItems", itemList);
		
	}

}
