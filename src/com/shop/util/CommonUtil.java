package com.shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 通用工具类
 * 
 * @className CommonUtil
 * @Description
 * @author rjht
 * @contact qq 676342073
 * @date 2014-10-22 下午03:06:42
 */
public class CommonUtil {

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	private static Integer count = 0;
	private static Integer orderCount = 0;
	private static Integer orderTransCount = 0;

	/**
	 * 获取32位的uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return s;
	}

	/**
	 * 计算
	 * 
	 * @return
	 */
	public synchronized static Integer cal() {
		count++;
		if (count > 999)
			count = 0;
		return count;
	}

	/**
	 * 计算
	 * 
	 * @return
	 */
	public synchronized static Integer cal9999() {
		orderCount++;
		if (orderCount > 99999)
			orderCount = 0;
		return orderCount;
	}

	/**
	 * 计算
	 * 
	 * @return
	 */
	public synchronized static Integer calTrans9999() {
		orderTransCount++;
		if (orderTransCount > 99999)
			orderTransCount = 0;
		return orderTransCount;
	}

	/**
	 * 不足左补字符
	 * 
	 * @param s
	 * @param len
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static String padleft(String s, int len, char c) throws Exception {
		s = s.trim();
		if (s.length() > len)
			return s;
		StringBuilder d = new StringBuilder(len);
		int fill = len - s.length();
		while (fill-- > 0)
			d.append(c);
		d.append(s);
		return d.toString();
	}
	public static String generalBatchNo() {
        String dateStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return new StringBuffer().append(getRandomString(11, dateStr)).toString();
    }
	/**
     * 生成随机数字符串
     * 
     * @param length 表示生成字符串的长度, dateStr 基本字符串
     * @return StringBuffer
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
    public static String getRandomString(int length, String dateStr) { // length表示生成字符串的长度
        String base = dateStr;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    public static String getRandomNumber(int length) { // length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

	/**
	 * 认证订单号：yyyyMMddHHmmss + 序列号4位 + 应用id4位+自定义编号(机器码)2位
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String generateOrderId() throws Exception {
		DateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formate.format(new Date());
		StringBuffer sb = new StringBuffer();
		sb.append(date);
		sb.append(PropertiesUtil.getProperty("orderApplicationNo"));
		sb.append(CommonUtil.padleft(CommonUtil.cal9999().toString(), 5, '0'));
		return sb.toString();
	}

	/**
	 * 交易订单号：yyyyMMddHHmmss + 序列号4位 + 应用id4位+自定义编号(机器码)2位
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String generateTransOrderId() throws Exception {
		DateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formate.format(new Date());
		StringBuffer sb = new StringBuffer();
		sb.append(date);
		sb.append(PropertiesUtil.getProperty("orderApplicationTransNo"));
		sb.append(CommonUtil.padleft(CommonUtil.calTrans9999().toString(), 5, '0'));
		return sb.toString();
	}

	/**
	 * file转base64
	 * 
	 * @param path
	 *            文件路径 D:\image\1.jpg
	 * @return
	 * @throws Exception
	 */
	public static String base64File(String path) throws Exception {
		FileInputStream inputFile = null;
		try {
			File file = new File(path);
			inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			return new BASE64Encoder().encode(buffer);
		} catch (Exception e) {
			logger.error("base64File 异常", e);
			return null;
		} finally {
			if (inputFile != null)
				inputFile.close();
		}
	}

	/**
	 * file转base64
	 * 
	 * @param path
	 *            文件路径 D:\image\1.jpg
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws Exception {
		FileInputStream inputFile = null;
		try {
			File file = new File(path);
			inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			return java.net.URLEncoder.encode(new BASE64Encoder().encode(buffer), "utf-8");
		} catch (Exception e) {
			logger.error("encodeBase64File 异常", e);
			return null;
		} finally {
			if (inputFile != null)
				inputFile.close();
		}
	}

	/**
	 * base64字符串转化成图片
	 * 
	 * @param imgStr
	 *            base64 字符串
	 * @param filename
	 *            文件名
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public static boolean GenerateImage(String imgStr, String filename, String filePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;

		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			File desFile = new File(filePath);
			if (!desFile.exists()) {// 目录不存在，则创建目录
				desFile.mkdirs();
			}

			// 生成jpg图片
			String imgFilePath = filePath + filename;// 新生成的图片

			File idcardFile = new File(imgFilePath);
			if (idcardFile.exists()) {
				return true;
			}

			out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					return false;
				}
		}
	}
	
	/** 
     * 获取随机字符串 a-z,A-Z,0-9 
     *  
     * @param length 
     *            长度 
     * @return 
     */  
    public static String getRandomString(int length) {  
        String str = "ABCDEF0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
  
        for (int i = 0; i < length; ++i) {  
            int number = random.nextInt(16);// [0,36)  
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    } 
    private static String SEPARATOR_OF_MAC = ":";
    public static String randomMac4Qemu() {
        Random random = new Random();
        String[] mac = {
                String.format("%02x", 0x52),
                String.format("%02x", 0x54),
                String.format("%02x", 0x00),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff))
        };
        return String.join(SEPARATOR_OF_MAC, mac);
    }
    /*
	 * 随机生成国内IP地址
	 */
	public static String getIp() {
 
		// ip范围
		int[][] range = { { 607649792, 608174079 },// 36.56.0.0-36.63.255.255
				{ 1038614528, 1039007743 },// 61.232.0.0-61.237.255.255
				{ 1783627776, 1784676351 },// 106.80.0.0-106.95.255.255
				{ 2035023872, 2035154943 },// 121.76.0.0-121.77.255.255
				{ 2078801920, 2079064063 },// 123.232.0.0-123.235.255.255
				{ -1950089216, -1948778497 },// 139.196.0.0-139.215.255.255
				{ -1425539072, -1425014785 },// 171.8.0.0-171.15.255.255
				{ -1236271104, -1235419137 },// 182.80.0.0-182.92.255.255
				{ -770113536, -768606209 },// 210.25.0.0-210.47.255.255
				{ -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
		};
 
		Random rdint = new Random();
		int index = rdint.nextInt(10);
		String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
		return ip;
	}
 
	/*
	 * 将十进制转换成ip地址
	 */
	public static String num2ip(int ip) {
		int[] b = new int[4];
		String x = "";
 
		b[0] = (int) ((ip >> 24) & 0xff);
		b[1] = (int) ((ip >> 16) & 0xff);
		b[2] = (int) ((ip >> 8) & 0xff);
		b[3] = (int) (ip & 0xff);
		x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
 
		return x;
	}
    
	public static String getIMEI() {// calculator IMEI
	 int r1 = 1000000 + new java.util.Random().nextInt(9000000);
	 int r2 = 1000000 + new java.util.Random().nextInt(9000000);
	String input = r1 + "" + r2;
	char[] ch = input.toCharArray();
		          int a = 0, b = 0;
		          for (int i = 0; i < ch.length; i++) {
		              int tt = Integer.parseInt(ch[i] + "");
		              if (i % 2 == 0) {
		                 a = a + tt;
		             } else {
		                 int temp = tt * 2;
		                 b = b + temp / 10 + temp % 10;
		             }
		        }
		        int last = (a + b) % 10;
		        if (last == 0) {
	             last = 0;
		         } else {
		             last = 10 - last;
		        }
		        return input + last;
	     }
	public static String getMac(){
	         char[] char1 = "abcdef".toCharArray();
		         char[] char2 = "0123456789".toCharArray();
		         StringBuffer mBuffer = new StringBuffer();
		         for (int i = 0; i < 6; i++) {
		             int t = new java.util.Random().nextInt(char1.length);
		             int y = new java.util.Random().nextInt(char2.length);
		             int key = new java.util.Random().nextInt(2);
		             if (key ==0) {
		                 mBuffer.append(char2[y]).append(char1[t]);
		             }else {
		                 mBuffer.append(char1[t]).append(char2[y]);
		             }
		             
		             if (i!=5) {
		                 mBuffer.append(":");
		             }
		         }
		         return mBuffer.toString();
		     }
	
	
	public static String getDeviceId(String bankcard){
		int nextInt = new Random().nextInt(999);
		String deviceID = "";
		int number = Integer.valueOf(bankcard.substring(bankcard.length()-1));
		if (number%2 == 0) {
			deviceID = "35"+nextInt+bankcard.substring(bankcard.length()-10);
		}else {
			deviceID = "86"+nextInt+bankcard.substring(bankcard.length()-10);
		}
		return deviceID;
	}
	
	public static String getTxzfDeviceId(String bankcard){
		int nextInt = new Random().nextInt(999);
		String deviceID = "";
		int number = Integer.valueOf(bankcard.substring(bankcard.length()-1));
		if (number%2 == 0) {
			deviceID = "35"+nextInt+bankcard.substring(bankcard.length()-10);
		}else {
			deviceID = getRandomString(8)+"-"+getRandomString(4)+"-"+getRandomString(4)+"-"+getRandomString(4)+"-"+getRandomString(12);
		}
		return deviceID;
	}
	
    public static void main(String[] args) {
		System.out.println(randomMac4Qemu());
		System.out.println(getIp());
		System.out.println(getIMEI());
		System.out.println(getMac());
		System.out.println(getTxzfDeviceId("1"));
	}
}
