package com.shop.teest;

import java.util.Map.Entry;

import com.shop.Filter;
import com.shop.Filter.Operator;
import com.shop.Page;
import com.shop.QueryHelper;
import com.shop.UpdateItem;

public class Testt {
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static void main(String[] args) {
		// 测试page
		QueryHelper hql = new QueryHelper();
		Page page = new Page(); 
		page.setAlias("m");
		page.setPage(3);
		page.setRows(5);
		page.setTotal(4); 
		Filter f1 = new Filter();
		f1.setOperator(Operator.no);
		f1.setProperty("m.time");
		f1.setValue("123");
//		f1.eq("time", "12:11:11");
		page.addFilter(f1);
		
		page.setPage(2);
		page.setRows(3);
		page.setTotal(500);
		page.setAlias("m");
		
		 
		
		/*UpdateItem up = new UpdateItem ();
		up.setProperty("name");
		up.setValue("cjw");
		UpdateItem up2 = new UpdateItem ();
		up2.setProperty("age");
		up2.setValue("12");
		page.addUpdateItem(up);
		page.addUpdateItem(up2);*/
		
		System.out.println(hql.buildAllQuery(page));
		
		String str = "sdfdf";
		System.out.println(str.getClass().getSimpleName());
		
		for(Entry<String, Object> item : hql.getParams().entrySet())
			System.out.println(item.getKey() + " " + item.getValue());
	}
}
