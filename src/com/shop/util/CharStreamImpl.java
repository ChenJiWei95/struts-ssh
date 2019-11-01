package com.shop.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * <b>获取流之后的操作</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年1月25日 下午1:17:20 
 * @see
 * @since 1.0
 */
 /*
	CharStreamImpl cimpl = new CharStreamImpl("1.txt");
	//写入
	StringBuilder sb = new StringBuilder();
	sb.append("第一行\r\n");
	sb.append("第二行\r\n");
	sb.append("第三行\r\n");
	sb.append("第四行");
	cimpl.write(sb.toString());
	//读取
	cimpl.read(new ParamOfCharStreamImpl(){
		@Override
		public void impl(String readLine) {
			System.out.println("读取文本："+readLine);
		}
	});
 */
public class CharStreamImpl {
	private CharStreamEtc cs = null;
	public CharStreamImpl(String filePath){
		cs = new CharStreamEtc(filePath);
	}
	public CharStreamImpl(File file){
		cs = new CharStreamEtc(file);
	}
	//循环读取每一行，最后关闭流
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void read(Consumer impl){
		BufferedReader in = cs.getRead();
		String line = null;
		try {
			while((line = in.readLine()) != null){
				impl.accept(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//写入所有的字符，最后关闭流
	//在StringBuilder中换行使用“\r\n” 
	//append:默认false 重开始位置写起，覆盖 
	public void write(String str){
		write(str, false);
	}
	//附加
	private BufferedWriter bw = null;
	//append:true 写入在尾部/false 重开始位置写起，覆盖 
	public void write(String str, boolean append){
		if(bw == null) bw = cs.getWrite(append);
		try {
			bw.write(str);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public void close() {
		try {
			CharStream.free(bw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void free(Object stream) throws IOException{
		CharStream.free(stream);
	}
}

class CharStream {
	public static BufferedReader getRead(File file){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	//获取文件写入流
	public static BufferedWriter getWrite(File file){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bw;
	}
	public static void free(Object stream) throws IOException{
		if(stream instanceof BufferedReader && stream != null){
			((BufferedReader) stream).close();
		}else if(stream instanceof BufferedWriter && stream != null){
			((BufferedWriter) stream).close();
		}else{
			if(stream != null){
				System.out.println("关闭文件流异常：" + stream + "-" + stream.getClass().getName());
			}else System.out.println(stream+"为空");
			
		}
	}
}

class CharStreamEtc {
	private String filePath = null;
	private File file = null;
	public CharStreamEtc(String filePath){
		this.filePath = filePath;
	}
	public CharStreamEtc(File file){
		this.file = file;
	}
	//append:默认false 重开始位置写起，覆盖 
	public BufferedReader getRead(){
		FileReader fr = null;
		try {
			fr = filePath != null 
				? new FileReader(filePath) 
				: new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new BufferedReader(fr);
	}
	//append:true 写入在尾部/false 重开始位置写起，覆盖 
	public BufferedWriter getWrite(boolean append){
		FileWriter fw = null;
		try {
			fw = filePath != null 
				? new FileWriter(filePath, append) 
				: new FileWriter(file, append);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new BufferedWriter(fw);
	}
	public BufferedWriter getWrite(){
		return getWrite(false);
	}
}



