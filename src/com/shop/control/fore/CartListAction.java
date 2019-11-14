package com.shop.control.fore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.shop.Constants;
import com.shop.annotation.RequestTypeAnno;
import com.shop.control.SuperActionSupport;
import com.shop.entity.CartList;
import com.shop.entity.Goods;
import com.shop.entity.User;
import com.shop.service.CartListService;
import com.shop.service.GoodsService;
import com.shop.util.ActionUtil;
import com.shop.util.Message;
import com.shop.util.SnowFlakeGenerator;
import com.shop.util.enums.RequestType;

@Component	 		
@Scope("prototype")
public class CartListAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(CartListAction.class); // 日志对象

	private CartList cartList; 
	
	@Autowired
	private CartListService cartListServiceImpl; 
	
	@Autowired
	private GoodsService goodsServiceImpl; 
	
	public CartList getCartList() {
		cartList = cartList == null ? new CartList() : cartList;
		return cartList;
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
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			if(cartList == null) cartList = new CartList();
			cartList.setId(String.valueOf(new SnowFlakeGenerator(2, 2).nextId()));
			cartList.setUserId(user.getId());
			
			cartList.setGoodsId(map.get("id"));//传值
			cartList.setColour("墨绿");//传值
			cartList.setUrl("");	//传值
			cartList.setCount(0);	//传值
			cartList.setSize("L");	//传值
			
			Goods goods = goodsServiceImpl.get(map.get("id"));//传值
			if("".equals(cartList.getUrl())) cartList.setUrl(goods.getUrl());	
			cartList.setName(goods.getName());
			cartList.setDiscount(goods.getDiscount());
			cartList.setPrice(goods.getPrice());
			
			cartListServiceImpl.save(cartList);
			user = null;
			setMessage(Message.success("添加成功", cartList));
		} catch (Exception e) {
			setMessage(Message.success("添加失败"));
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
		try {
			cartListServiceImpl.update(cartList);
			setMessage(new Message().success(getText("shop.error.updateOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.updateError")));
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
			cartListServiceImpl.delete(cartList);
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
				cartListServiceImpl.delBatch(ids);
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
			StringBuilder eq = new StringBuilder("from CartList where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param.add(item.getValue());
			}
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			eq.append(" AND u_id="+user.getId());
			user = null;
			List<CartList> list = cartListServiceImpl.findList(eq.toString(), param.toArray());	
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
			CartList t = cartListServiceImpl.get(cartList.getId());	
			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
}
