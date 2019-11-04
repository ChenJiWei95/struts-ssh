package com.shop.control.fore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.shop.annotation.RequestTypeAnno;
import com.shop.control.SuperActionSupport;
import com.shop.entity.Goods;
import com.shop.service.GoodsService;
import com.shop.util.Message;
import com.shop.util.SnowFlakeGenerator;
import com.shop.util.enums.RequestType;

@Component	 		
@Scope("prototype")	
public class GoodsAction extends SuperActionSupport implements ModelDriven<Goods>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5979900385536298029L;

	
	private Goods goods; 
	
	@Autowired
	private GoodsService goodsServiceImpl; 	

	@Override
	public Goods getModel() {
		if ( goods == null ){   
			goods = new Goods();   
	   	//user.setUsername(" 这是原来的 User 对象 ");   
		}
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
			
	// 获取方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_list(方法)
	public String list(){
		
		// 不支持
		new UnsupportedOperationException("不支持：list");
		return null;
		
	}		
}
