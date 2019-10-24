package com.shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUploadUtil {
	/**
	 * 这里用一句话描述这个方法的作用
	 * <p>	 
	 * @param sourceFile		源文件
	 * @param uploadDir			上传的目录
	 * @param fileName			上传文件的文件名称
	 * @throws IOException
	 * void
	 * @see
	 * @since 1.0
	 */
	public void wirteTo(File sourceFile, String uploadDir, String fileName) throws IOException{
		File drecotry = new File(uploadDir) ;
		if(! drecotry.exists())
			drecotry.mkdirs() ;
		System.out.print("上传中。。。") ;
		//获取需要上传的文件流
		InputStream is = null ;
		//输出需要上传的文件流
		OutputStream os = null ;
		System.out.print("上传中。。。") ;
		//上传文件保存File对象
		//判断一下file是否为空
		if(sourceFile == null){
			System.out.print("上传文件为空") ;
		}
		System.out.print("上传中。。。") ;
		File toFile = new File(uploadDir, fileName) ;
		System.out.print("上传中。。。") ;
		//二进制对象
		byte[] buffer = new byte[1025] ;
		//Map parameterMap=context.getParameters();
		//String callback= ((String[]) parameterMap.get("callbackObj"))[0] ;
		System.out.print("上传中。。。") ;
		is = new FileInputStream(sourceFile) ;
		os = new FileOutputStream(toFile) ;
		while(is.read(buffer, 0, buffer.length) != -1){
			os.write(buffer) ;
		}
		//关闭输入流
		if(is != null){
			is.close() ;
		}
		if(os != null){
			os.close() ;
		}	
	}
}
