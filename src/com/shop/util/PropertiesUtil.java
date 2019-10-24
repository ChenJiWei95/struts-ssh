package com.shop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 公共基础类
 * @className CommonUtil
 * @Description
 * @author xuguiyi
 * @contact
 * @date 2016-5-11 下午03:57:30
 */
public class PropertiesUtil {

	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static Properties properties;
	
	//加载配置文件
	static {
		InputStream in = null;
		BufferedReader bf = null;
		try {
			in = CommonUtil.class.getResourceAsStream("/common.properties");
			bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
			properties = new Properties();
			properties.load(bf);
		} catch (Exception e) {
			logger.error("加载属性失败common.properties",e);
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("关闭InputStream异常",e);
				}
			}
			
			if(bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					logger.error("关闭BufferedReader异常",e);
				}
			}
		}
	}

	/**
	 * 根据name获取值
	 * @param name
	 * @return
	 */
	public static String getProperty(String name) {
		
		return properties.getProperty(name);
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getProperty("orderApplicationNo"));
	}

}
