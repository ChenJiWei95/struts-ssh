package com.shop.control.fore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.annotation.RequestTypeAnno;
import com.shop.control.SuperActionSupport;
import com.shop.entity.Goods;
import com.shop.service.GoodsService;
import com.shop.util.ActionUtil;
import com.shop.util.Message;
import com.shop.util.SnowFlakeGenerator;
import com.shop.util.enums.RequestType;

@Component	 		
@Scope("prototype")	
public class GoodsAction extends SuperActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 5979900385536298029L;
	
	private static final Logger log = Logger.getLogger(GoodsAction.class); // 日志对象

	private Goods goods; 
	private HttpServletRequest request;
	
	@Autowired
	private GoodsService goodsServiceImpl; 	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}	
	
	public Goods getGoods(Goods goods) {
		goods = goods == null ? new Goods() : goods;
		return goods;
	}

	// 添加方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_save(方法)
	@RequestTypeAnno(RequestType.POST)
	public String save(){
		try {
			goods.setId(String.valueOf(new SnowFlakeGenerator(2, 2).nextId()));
			goodsServiceImpl.save(goods);
			setMessage(Message.success("添加成功", goods));
		} catch (Exception e) {
			setMessage(Message.success("添加失败"));
		}
		return JSON;
	}	
	
	// 修改方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_update(方法)
	@RequestTypeAnno(RequestType.POST)
	public String update(){
		try {
			goodsServiceImpl.update(goods);
			setMessage(Message.success("修改成功", goods));
		} catch (Exception e) {
			setMessage(Message.success("修改失败"));
		}
		return JSON;
	}	
	
	// 删除方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_delete(方法)
	@RequestTypeAnno(RequestType.POST)
	public String delete(){
		try {
			goodsServiceImpl.delete(goods);
			setMessage(Message.success("删除成功", goods));
		} catch (Exception e) {
			setMessage(Message.success("删除失败"));
		}
		return JSON;
	}		
	
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String delBatch(){
		
		try {
			JSONArray json = JSONObject.parseArray(ActionUtil.read(request));
			String[] ids = new String[json.size()];
			for(int i = 0; i < json.size(); i++) 
				ids[i] = json.getJSONObject(i).getString("id");
			if(json.size() > 0) 
				goodsServiceImpl.delBatch(ids);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}
			
	// 获取方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_list(方法)
	@SuppressWarnings("static-access")
	public String list(){
		
		// 不支持
		try {
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			StringBuilder eq = new StringBuilder("from Goods where 1=1");
			List<Object> param = new ArrayList<Object>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append("AND"+item.getKey()+"=?");
				param.add(item.getValue());
			}
			List<Goods> list = goodsServiceImpl.findList(eq.toString(), param.toArray());	
			setMessage(new Message().success(getText("shop.error.getOk"), list));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}

}
