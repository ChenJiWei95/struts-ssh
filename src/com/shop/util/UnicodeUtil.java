package com.shop.util;

public class UnicodeUtil {
	public static String parseU(String str){
		String result = "";  
        for (int i = 0; i < str.length(); i++) {  
            int chr1 = (char) str.charAt(i);  
            if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)  
                result += "\\u" + Integer.toHexString(chr1);  
            } else {  
                result += str.charAt(i);  
            }  
        }  
        return result ;  
	}
	public static String parseC(String dataStr){
		int start = 0;     
        int end = 0;     
        final StringBuffer buffer = new StringBuffer();   
        while (start > -1) {     
            end = dataStr.indexOf("\\u", start) ;     
            String charStr = "" ;     
            if (end == -1) {   
                charStr = dataStr.substring(start, dataStr.length()) ;   
                System.out.println("1 - " +charStr) ;
            } else { 
                charStr = dataStr.substring(start, end) ;    
                System.out.println(charStr) ;
            }     
            //System.out.println("执行") ;
            //System.out.println(charStr) ; 
            //char letter = (char) Integer.parseInt(charStr, 16) ; // 16进制parse整形字符串。     
            //System.out.println(charStr) ;
            //buffer.append(new Character(letter).toString()) ;
            buffer.append(charStr) ;
            start = end ;
        }
        return buffer.toString() ; 
	}
	public static boolean isChiness(char c){
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c) ;
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true ;
        }  
        return false ;
	}
}
