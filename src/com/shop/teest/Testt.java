package com.shop.teest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.NonUniqueResultException;

import com.shop.entity.Language;
import com.shop.service.LanguageService;
import com.shop.util.BeanUtil;
import com.shop.util.CharStreamImpl;
import com.shop.util.CommonUtil;

public class Testt {
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static void main(String[] args) {
		/*QueryHelper hql = new QueryHelper();
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
			System.out.println(item.getKey() + " " + item.getValue());*/
		LanguageService impl = (LanguageService) BeanUtil.getBean("languageServiceImpl");
		/*CharStreamImpl c = new CharStreamImpl("C:\\Users\\Administrator.USER-20160224QQ\\git\\struts-ssh\\src.resource\\message_en_US.properties");
		c.read(line -> {
			if(line != null && !"".equals(line.toString().trim()) && line.toString().indexOf("=") != -1)
			{
				System.out.println();
				Language entity = new Language();
				entity.setId(CommonUtil.getId());
				String[] arr = line.toString().split("=");
				entity.setCode(arr[0]);
				entity.setEnUs(arr[1]);
				impl.save(entity);
			}
		});*/
		List<Language> list = impl.findAll();
		Map<String, String> map = new HashMap<String, String>();
		for(Language l : list){
			map.put(l.getCode(), l.getId());
		}
		CharStreamImpl c2 = new CharStreamImpl("C:\\Users\\Administrator.USER-20160224QQ\\git\\struts-ssh\\src.resource\\message_zh_CN.properties");
		
		c2.read(line -> {
			if(line != null && !"".equals(line.toString().trim()) && line.toString().indexOf("=") != -1)
			{
				String[] arr = line.toString().split("=");
				System.out.println(arr[0]);
				try{
					Language entity = impl.find("from Language where id=?", map.get(arr[0]));
					if(entity != null){
						entity.setCnZh(CommonUtil.parseC(arr[1]));
						impl.update(entity);
					}
				}catch(NonUniqueResultException e){
				}
			}
				
		});	
		
		c2.close();
	} 
}
