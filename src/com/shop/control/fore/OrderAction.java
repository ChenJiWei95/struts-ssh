package com.shop.control.fore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.Constants;
import com.shop.annotation.RequestTypeAnno;
import com.shop.control.SuperActionSupport;
import com.shop.entity.Address;
import com.shop.entity.CartList;
import com.shop.entity.Logistics;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.entity.User;
import com.shop.service.AddressService;
import com.shop.service.CartListService;
import com.shop.service.GoodsService;
import com.shop.service.LogisticsService;
import com.shop.service.OrderItemService;
import com.shop.service.OrderService;
import com.shop.util.ActionUtil;
import com.shop.util.CommonUtil;
import com.shop.util.Message;
import com.shop.util.enums.RequestType;

@Transactional
@Component	 		
@Scope("prototype")
public class OrderAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(OrderAction.class); // 日志对象

	private Order order; 
	
	@Autowired
	private CartListService cartListServiceImpl; 
	
	@Autowired
	private GoodsService goodsServiceImpl;
	
	@Autowired
	private AddressService addressServiceImpl;
	
	@Autowired
	private OrderService orderServiceImpl; 
	
	@Autowired
	private LogisticsService logisticsServiceImpl;  
	
	@Autowired
	private OrderItemService orderItemServiceImpl; 
	
	public Order getOrder() {
		order = (order == null ? new Order() : order);
		return order;
	}	
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}
	public HttpServletRequest getRequest() {  
        return request;  
    }
	
	/**
	 * 添加方法 测试
	 * 链接格式 当前类为例：user(类前缀)_save(方法)
	 * @return
	 */
	
	@RequestTypeAnno(RequestType.POST)
	public String save(){
		try {
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			// 结算之后为已支付
			
			order = new Order();
			order.setId(CommonUtil.getId());
			order.setPaymentStatus("02");
			order.setuId(user.getId());
			order.setLogisticsStatus("02");
			
			BigDecimal sum = new BigDecimal("0");
			BigDecimal origSum = new BigDecimal("0");
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			map.remove("all");
			Serializable[] ids = new Serializable[map.size()];
			int count = 0;
			for(Entry<String, String> item : map.entrySet()) {
				ids[count++] = item.getKey();
				CartList cart = cartListServiceImpl.get(item.getKey());
				if(cart == null){
					throw new Exception("cart="+cart+"; id = " + item.getKey());
				}
				OrderItem oi = new OrderItem();
				oi.setOrderItemId(CommonUtil.getId());
				oi.setOrderId(order.getId());
				oi.setSize(cart.getSize());
				oi.setColour(cart.getColour());
				oi.setCount(cart.getCount());
				oi.setUrl(cart.getUrl());
				oi.setGoodsId(cart.getGoodsId());
				oi.setName(cart.getName());
				
				BigDecimal price = cart.getPrice();
				BigDecimal discount = cart.getDiscount();
				BigDecimal sum_ = price.multiply(discount).multiply(new BigDecimal(cart.getCount()));
				BigDecimal origSum_ = price.multiply(new BigDecimal(cart.getCount()));
				oi.setPrice(price);
				oi.setDiscount(discount);
				oi.setTotal(sum_);
				
				sum = sum.add(sum_);
				origSum = origSum.add(origSum_);
				
				orderItemServiceImpl.save(oi);
			}
			
			order.setTotalAmount(sum);
			order.setOriginalAmount(origSum);
			order.setCreateDate(getNowTime());
			
			orderServiceImpl.save(order);
			
			cartListServiceImpl.delBatch(ids);
			setMessage(Message.success(getText("shop.error.checkoutOk"), order.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(Message.success(getText("shop.error.checkoutfail")));
		}
		return JSON;
	}	
	/**
	 * 修改方法 测试
	 * 链接格式 当前类为例：user(类前缀)_update(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String update(){
		String textErr = "";
		try {
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			if("payment".equals(map.get("method"))){
				User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
				for(Map.Entry<String, String> item : map.entrySet())
					log.info(item.getKey()+" " +item.getValue());
				log.info(order);
				
				order = orderServiceImpl.get(map.get("id"));
				order.setPaymentStatus(map.get("paymentStatus"));
				order.setLogisticsStatus(map.get("logisticsStatus"));
				orderServiceImpl.update(order);
				
				Logistics logistics = new Logistics();
				Address address = addressServiceImpl.get(map.get("addressId")); 
				logistics.setArea(address.getArea());
				logistics.setCity(address.getCity());
				logistics.setStreet(address.getStreet());
				logistics.setProvince(address.getProvince());
				logistics.setName(address.getName());
				logistics.setPhone(address.getPhone());
				logistics.setOrderId(order.getId());
				
				logistics.setLogisticsId(CommonUtil.getId());
				logistics.setNickname(user.getUsername());
				logisticsServiceImpl.save(logistics);
				
				String textOk = "shop.error.payOk";
				textErr = "shop.error.payError";
				if(Constants.SHOP_STATUS_CANCEL.equals(order.getPaymentStatus())) {// 返回信息调控
					textOk = "shop.error.cancelOk";
					textErr = "shop.error.cancelError";
				}
				setMessage(new Message().success(getText(textOk)));
			}else {
				setMessage(new Message().error(getText(textErr)));
			}
		}catch(Exception e) {
			e.printStackTrace();
			setMessage(new Message().error(getText(textErr)));
		}
		return JSON;
	}	
	
	/**
	 * 删除方法 测试
	 * 链接格式 当前类为例：user(类前缀)_delete(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String delete(){
		
		try {
			orderServiceImpl.delete(order);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}	
	/**
	 * 删除方法 测试
	 * 链接格式 当前类为例：user(类前缀)_delete(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String delBatch(){
		
		try {
			JSONArray json = JSONObject.parseArray(ActionUtil.read(request));
			String[] ids = new String[json.size()];
			for(int i = 0; i < json.size(); i++) 
				ids[i] = json.getJSONObject(i).getString("id");
			if(json.size() > 0) 
				orderServiceImpl.delBatch(ids);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}			
			
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：user(类前缀)_list(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String list(){
		
		try {
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			StringBuilder eq = new StringBuilder("from Order where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param.add(item.getValue());
			}
			List<Order> list = orderServiceImpl.findList(eq.toString(), param.toArray());	
			setMessage(new Message().success(getText("shop.error.getOk"), list));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
	
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：user(类前缀)_list(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String get(){
		
		try {
			Order t = orderServiceImpl.get(order.getId());	
			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
}
