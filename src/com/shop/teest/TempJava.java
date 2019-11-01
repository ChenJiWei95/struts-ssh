package com.shop.teest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.shop.util.CharStreamImpl;
/**
 * 操作一下文件 增删
 * dao
 * service
 * entity
 * mapper
 * service
 * 配置文件
 * @author cjw
 */
public class TempJava {
	// 判断是否用textarea标签
	private static String[] descField = new String[] {"desc", "noticeContent", "remark"}; 
	// 不可操作字段
	private static String[] disableField = new String[] {"create_time", "update_time", "createDate", "modifyDate"};
	// 隐藏字段
	private static String[] layuihideField = new String[] {"id"};
	//前缀 顺序不可修改--需要改动命令值
	private static String[] prefix = {"dao", "service", "entity", "mapper", "service/impl", "control/admin"};
	//后缀 顺序不可修改--需要改动命令值
	private static String[] suffix = {"Dao.java", "Service.java", ".java", "Mapper.xml", "ServiceImpl.java", "Control.java"};
	// 模板文件
	private static String[] temp = {"dao.txt", "service.txt", "", "mapper.xml", "serviceImpl.txt"};
	// 命令描述
	private static String[] commomdText = {"操作dao层代码", "操作service层代码", "操作实体层代码", "操作mapper配置代码", "操作service实现层代码", "操作control层代码", "操作mybatise配置文件代码", "操作前端代码"};
	// 复制的路径前缀
//	private static String copyPrevpath = TempJava.class.getResource("/").getPath().substring(1).replace("build/classes", "src")+"com/blog/";
	private static String copyPrevpath = TempJava.class.getResource("/").getPath().substring(1).replace("build/classes", "src")+"com/blog/";
	// mybatis-config.xml 的路径 在ssm中需要
	private static String mybatisConfig = srcPath("config/mybatis-config.xml"); 
	// 要执行的命令下标  对应 前缀和后缀
	// 0 	操作dao层代码 接口模式
	// 1 	操作service层代码
	// 2 	操作实体层代码
	// 3 	操作mapper配置代码
	// 4 	操作service实现层代码 
	// 5 	操作control层代码
	// 6 	操作mybatise配置文件代码
	// 7  	操作前端代码
//	private static Integer[] commond = {0, 1, 2, 3, 4, 5, 6, 7};
//	private static Integer[] commond = {};
//	private static Integer[] commond = {0, 1, 2, 3, 4, 5, 6};
	private static Integer[] commond = {2, 1, 0, 4, 5};
	
	public static void main(String[] args) throws IOException {
		String fileds = "id name";
		String texts = "ID 描述 时间 状态";
		
		// 生成js和java代码 调用这个  
		do1_1("usertest", "usertest", "admin", fileds, texts, "消息中心");

		// 下面为测试
//		do1("tagBrige", "article_tag_brige", "tagBrige", fileds.split(" "));
		// 生成前端模板文件     字段name值， 字段显示值，分类，title
//		do7(fileds.split(" "), texts.split(" "), "cjw2", "测试2");
//		doHTML(fileds.split(" "), texts.split(" "));
//		doJSTableHead(fileds.split(" "), texts.split(" "));
//		doJSEdit(fileds.split(" "));
		
		// 只操作字符串类型的数据
		// 类名称-小写	 	表名称 	实体类字段 
//		delete("notice");
		
		// 生成控制类
//		do6("TempText", "tempText", "temp"); 
	}
	
	public static void delete(String name_) {
		String name = upFirst(name_); 
		for (Integer index : commond) {
			System.out.println("当前执行："+commomdText[index]);
			if(index == 6) {
				// 清除 mybatis-config.xml 配置mapper项
				delete2(name);
			} else {
				String copyPath = copyPrevpath+prefix[index]+"/"+name+suffix[index];
				File file = new File(copyPath);
				if(file.exists()) {
					file.delete();
					System.out.println("删除文件," + copyPath);
				}
			}
		}
	}
	static boolean fristLine = true;
	private static void delete2(String name) {
		fristLine  =true;
		// mybatis-config.xml 配置mapper项
		CharStreamImpl c = new CharStreamImpl(mybatisConfig);
		StringBuilder sb = new StringBuilder();
		String configFileName = name+"Mapper.xml";
		
		c.read(line -> {
			String str = (String) line;
			if(str.indexOf(configFileName) == -1) 
				sb.append(fristLine ? "" : System.lineSeparator()).append(line);
			else 
				System.out.print("删除mapper配置信息：找到删除项，"+str);	
			fristLine = false;
		});
		
		c.write(sb.toString());
		c.close();
		System.out.println("--已删除！");		
	}
	
	private static int num = 0;
	private static String upFirst(String str) {
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	
	// 生成js代码
	/**
	 * 
	 * @param name_		类名前缀小写 例：‘UserService.java’ 那么 ‘name_=user’
	 * @param table		表名
	 * @param classify	分类 前端文件夹 
	 * @param fileds	表字段  实体类生成、前端文件生成
	 * @param Texts		表字段名称 前端文件生成
	 * @param title		前端页面标题
	 */
	public static void do1_1(String name_, String table, String classify,  String fileds, String Texts, String title){
		do1(name_, table, classify, fileds.split(" "), Texts.split(" "), title);
	}

	public static void do1(String name_, String table, String classify,  String[] fileds, String[] Texts, String title) {
		String name = name_.substring(0,1).toUpperCase() + name_.substring(1);
		for (Integer index : commond) {
			System.out.println("当前执行："+commomdText[index]);
			if(index == 2) {
				// 生成实体类
				do3(name, fileds);
			} else if(index == 5) {
				// 生成控制类
				do6(name, name_, classify);
			}  else if(index == 6) {
				// 配置文件
				do5(name);
			} else if(index == 7){
				//args 根据字段生成表头js文
				try {
					do7(fileds, Texts, classify, title);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String copyPath = copyPrevpath+prefix[index]+"/"+name+suffix[index]; 
				File file = new File(copyPath); if(file.exists()) file.delete();// 删除存在文件
				do2(copyPrevpath+temp[index], copyPath, name, name_, table);
			}
		} 
	}
	
	// 生成前端模板文件
	public static void do7 (String[] args, String[] texts, String classify, String title) throws IOException{
		
		String dir = webContentPath("views/admin/"+classify+"/save_or_update.jsp");
		dir = dir.substring(0, dir.lastIndexOf("/"));
		File file = null;
		if(!(file = new File(dir)).isDirectory()){
			System.out.println("创建文件夹【"+file.getPath()+"】");
			file.mkdir();
		}
		
		CharStreamImpl c = new CharStreamImpl(srcPath("config/temp/save_or_update.txt"));
		
		CharStreamImpl copy = new CharStreamImpl(webContentPath("views/admin/"+classify+"/save_or_update.jsp"));
		c.read(line->{
			String str = (String) line;
			copy.write(str
					.replaceAll("#form-item#", doHTML(args, texts))
					.replaceAll("#title#", title)
					.replaceAll("#classify#", classify)
					.replaceAll("#js-edit#", doJSEdit(args))
					.replaceAll("#table-head#", doJSTableHead(args, texts))
					.replaceAll("#classify#", classify), true);
		});
		copy.close();
		System.out.println("完成文件【"+webContentPath("views/admin/"+classify+"/save_or_update.jsp")+"】的生成");
		
		c = new CharStreamImpl(srcPath("config/temp/list.txt"));
		CharStreamImpl copy_ = new CharStreamImpl(webContentPath("views/admin/"+classify+"/list.jsp"));
		c.read(line->{
			String str = (String) line;
			copy_.write(str
					.replaceAll("#form-item#", doHTML(args, texts))
					.replaceAll("#title#", title)
					.replaceAll("#classify#", classify)
					.replaceAll("#js-edit#", doJSEdit(args))
					.replaceAll("#table-head#", doJSTableHead(args, texts))
					.replaceAll("#classify#", classify), true);
		});
		copy_.close();
		System.out.println("完成文件【"+webContentPath("views/admin/"+classify+"/list.jsp")+"】的生成");
	}
	private static String doHTML(String[] args, String[] texts){
		StringBuilder sb = new StringBuilder();
		String t = "\t";
		for(int i = 0; i < args.length; i++){
			if(isLayuiHide(args[i])){
				sb.append("<div class=\"layui-hide\">"+"\n"+
						t+"\t<label class=\"layui-form-label\">ID</label>"+"\n"+
						t+"\t<div class=\"layui-input-inline\">"+"\n"+
						t+"\t\t<input type=\"text\" name=\"id\" disabled autocomplete=\"off\" class=\"layui-input layui-disabled\">"+"\n"+
						t+"\t</div>"+"\n"+
						t+"</div>"+"\n");
			}else if(isTextarea(args[i])){
				sb.append(t+"<div class=\"layui-form-item\">"+"\n"+
						t+"\t<label class=\"layui-form-label\">"+texts[i]+"</label>"+"\n"+
						t+"\t<div class=\"layui-input-inline\">"+"\n"+
						t+"\t\t<textarea class=\"layui-textarea\" name=\""+args[i]+"\" placeholder=\"请输入描述信息\"></textarea>"+"\n"+
						t+"\t</div>"+"\n"+
						t+"</div>"+"\n");
			}else if(isDisable(args[i])){
				sb.append(t+"<div class=\"layui-form-item\">"+"\n"+
						t+"\t<label class=\"layui-form-label\">"+texts[i]+"</label>"+"\n"+
						t+"\t<div class=\"layui-input-inline\">"+"\n"+
						t+"\t\t<input type=\"text\" name=\""+args[i]+"\" disabled placeholder=\"请输入"+texts[i]+"\" autocomplete=\"off\" class=\"layui-input layui-disabled\">"+"\n"+
						t+"\t</div>"+"\n"+
		        		t+"</div>"+"\n");
			}else {
				sb.append(t+"<div class=\"layui-form-item\">"+"\n"+
						t+"\t<label class=\"layui-form-label\">"+texts[i]+"</label>"+"\n"+
						t+"\t<div class=\"layui-input-inline\">"+"\n"+
						t+"\t\t<input type=\"text\" name=\""+args[i]+"\" placeholder=\"请输入"+texts[i]+"\" autocomplete=\"off\" class=\"layui-input\">"+"\n"+
						t+"\t</div>"+"\n"+
						t+"</div>"+"\n");
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	private static boolean isTextarea(String str) {
		for(String s : descField) 
			if(s.indexOf(str) != -1) return true;
		return false;
	} 
	private static boolean isDisable(String str) {
		for(String s : disableField) 
			if(s.equals(str)) return true;
		return false;
	} 
	private static boolean isLayuiHide(String str) {
		for(String s : layuihideField) 
			if(s.equals(str)) return true;
		return false;
	} 
	private static String doJSEdit(String[] args){
		StringBuilder sb = new StringBuilder();
		for(String arg : args){
			sb.append(("id".equals(arg) ? "" : "\t\t\t\t\t,")+"iframe.find('"+("desc".equals(arg)?"textarea" : "input")+"[name=\""+arg+"\"]')[0].value = data[0]."+arg);
			sb.append("\n");
		}
		return sb.deleteCharAt(0).toString();
	}
	private static String doJSTableHead(String[] args, String[] texts){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < args.length; i++){
			sb.append(("id".equals(args[i]) ? "" : "\t\t\t")+",{field:'"+args[i]+"', title:'"+texts[i]+"'}");
			sb.append("\n");	
		}
		return sb.toString();
	}
	public static void do6(String name, String name_, String classify) {
		
		CharStreamImpl c = new CharStreamImpl(srcPath("config/temp/TempControl.txt"));
		CharStreamImpl copy = new CharStreamImpl(srcPath("com/blog/control/admin/"+name+"Control.java"));
		c.read(line -> {
			String str = (String) line;
			copy.write(str
					.replaceAll("#name#", name)
					.replaceAll("#name_#", name_)
					.replaceAll("#classify#", classify), true);
		});
		
		copy.close();
		
		System.out.println("生成控制类："+srcPath("com/blog/control/admin/"+name+"Control.java"));
		
	}
	public static String srcPath(String path){
		return TempJava.class.getResource("/").getPath().substring(1).replace("build/classes", "src")+path;
	}
	public static String webContentPath(String path){
		//C:/Users/Administrator.USER-20160224QQ/git/repository6/MyBlog/WebContent/
		return TempJava.class.getResource("/").getPath().substring(1).replace("build/classes", "WebContent")+path;
	}
	public static void do5(String name) {
		// mybatis-config.xml 配置mapper项
		CharStreamImpl c = new CharStreamImpl(srcPath("config/mybatis-config.xml"));
		StringBuilder sb = new StringBuilder();
		c.read(line -> sb.append(line).append(System.lineSeparator()));
		
		String repStr = "<!--#MAPPER#-->";
		String configClassPath = "<mapper resource=\"com/blog/mapper/"+name+"Mapper.xml\" />";
		String sign = "\t\t<!--#MAPPER#-->";
		if(sb.toString().indexOf(configClassPath) == -1) {
			c.write(sb.toString().replaceAll(repStr, 
					configClassPath+System.lineSeparator()+sign));
			System.out.println("添加mapper配置信息：<mapper resource=\"com/blog/mapper/"+name+"Mapper.xml\" />");
		}else System.out.println("添加mapper配置信息：配置已存在！");
		c.close();
		
	}		
	public static void do3(String name, String[] args) {
		// name 作为实体类的名称 生成实体类 
				//	收集实体类属性 属性包含 修饰符，属性名，属性类型
				//	根据属性生成getter，setter方法
		CharStreamImpl c = new CharStreamImpl(srcPath("com/blog/entity/"+name+".java"));
		
		c.write("package com.blog.entity;");
		c.write("public class "+name+" extends Base{", true);
		
		for(String arg : args) {
			c.write("\tprivate String "+arg+";", true);
		}
		
		c.write(System.lineSeparator());
		
		for(String arg : args) {
			c.write("\tpublic String get"+upFirst(arg)+"() {", true);
			c.write("\t\treturn "+arg+";", true);
			c.write("\t}", true);
			c.write("\tpublic void set"+upFirst(arg)+"(String "+arg+") {", true);
			c.write("\t\tthis."+arg+" = "+arg+";", true);
			c.write("\t}", true);
		}
		
		c.write("}");
		c.close();
		
		System.out.println("生成实体类："+srcPath("com/blog/entity/"+name+".java"));
		
	}
	public static void do2(String path, String copyPath, String name, String name_, String table) {
		BufferedReader in = null;
		BufferedWriter bw = null;
		String line = null;
		try {
			in = new BufferedReader(new FileReader(path));
			bw = new BufferedWriter(new FileWriter(copyPath, true));
			bw.write(""); // 清空
			num = 0;
			while((line = in.readLine()) != null){
				bw.write(line.replaceAll("#name#", name).replaceAll("#name_#", name_).replaceAll("#table#", table));
				bw.newLine();
				num++;
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("已完成文件【"+copyPath+"】的复制。总复制行数："+num);
			try {
				if(bw != null)
					bw.close();
				if(in != null)
					in.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
