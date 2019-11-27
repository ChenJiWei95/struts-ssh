package com.shop.teest;

import java.util.Map.Entry;

import com.shop.Filter;
import com.shop.Filter.Operator;
import com.shop.Page;
import com.shop.QueryHelper;

public class Testt {
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static void main(String[] args) {
		QueryHelper hql = new QueryHelper();
		Page page = new Page(); 
		page.setAlias("m");
		page.setPage(3);
		page.setRows(5);
		page.setTotal(4); 
		Filter f1 = new Filter();
		f1.setOperator(Operator.eq);
		f1.setProperty("m.time");
		f1.setValue("123");
		f1.eq("time", "12:11:11");
		page.addFilter(f1);
		
		System.out.println(hql.buildAllQuery(page));
		
		for(Entry<String, Object> item : hql.getParams().entrySet())
			System.out.println(item.getKey() + " " + item.getValue());
	}
}
