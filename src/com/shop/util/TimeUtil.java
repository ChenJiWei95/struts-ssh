package com.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * <b>时间类<b>
 * @author 威 
 * <br>2017年9月4日 下午7:35:37 
 * @see   <br> G 年代标志符
  <br>y 年
  <br>M 月
  <br>d 日
  <br>h 时 在上午或下午 (1~12)
  <br>H 时 在一天中 (0~23)
  <br>m 分
  <br>s 秒
  <br>S 毫秒
  <br>E 星期
  <br>D 一年中的第几天
  <br>F 一月中第几个星期几
  <br>w 一年中第几个星期
  <br>W 一月中第几个星期
  <br>a 上午 / 下午 标记符 
  <br>k 时 在一天中 (1~24)
  <br>K 时 在上午或下午 (0~11)
  <br>z 时区
 *
 */
public class TimeUtil {
	// 返回16位随机id
	public static String randomId (){
		return TimeUtil.getDatetime("yyyyMMddHHmmss")+String.valueOf(System.currentTimeMillis()).substring(11);
	}
	/**
	 * 
	 * 获取时间类型为yyyy-MM-dd HH:mm:ss
	 * @see
	 * @return
	 * String
	 *
	 */
	public static String getDatetime(){
		Date datetime=new Date();
		SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return st.format(datetime);
	}
	public static String getDatetime(String format){
		Date datetime=new Date();
		SimpleDateFormat st=new SimpleDateFormat(format);
		return st.format(datetime);
	}
	/**
	 * 
	 * 只获取日期
	 * @see
	 * @return
	 * String
	 *
	 */
	public static String getDate(){
		Date datetime=new Date();
		SimpleDateFormat st=new SimpleDateFormat("yyyy/MM/dd");
		return st.format(datetime);
	}
	/**
	 * 
	 * 只获取时间
	 * @see
	 * @return
	 * String
	 *
	 */
	public static String getTime(){
		Date datetime=new Date();
		SimpleDateFormat st=new SimpleDateFormat("HH:mm:ss");
		return st.format(datetime);
	}
	public static void main(String[] args){
		System.out.println(randomId()) ;
	}
}
