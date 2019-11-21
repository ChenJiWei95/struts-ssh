package com.shop.teest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.shop.util.CharStreamImpl;
 
/**
 * <b>自动生成代码</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年11月2日 下午3:48:58 
 * @see
 * @since 2.0
 */
public class TempJava {
	public final static String GENERAL_STRATEGY			= "1";// 普通
	public final static String HIBER_ANNOTATION_STRATEGY= "2";// hibernate 注解
	// 判断是否用textarea标签
	private static String[] descField = new String[] {"desc", "noticeContent", "remark"}; 
	// 不可操作字段
	private static String[] disableField = new String[] {"create_time", "update_time", "createDate", "modifyDate"};
	// 隐藏字段
	private static String[] layuihideField = new String[] {"id"};
	//前缀 顺序不可修改--需要改动命令值 文目录前缀 在copyPrevpath尾部拼接
	private static String[] prefix = {"dao/", "service/", "entity/", "mapper/", "service/impl/", "control/fore/", "", "", "dao/impl/"};
	//后缀 顺序不可修改--需要改动命令值
	private static String[] suffix = {"Dao.java", "Service.java", ".java", "Mapper.xml", "ServiceImpl.java", "Action.java", "", "",  "DaoImpl.java"};
	// 模板文件名 顺序不可修改
	private static String[] temp = {"dao.txt", "service.txt", "", "mapper.xml", "serviceImpl.txt", "TempControl.txt", "", "",  "daoImpl.txt"};
	// 命令描述 顺序不可修改
	private static String[] commomdText = {"自动生成dao层代码", "自动生成service层代码", "自动生成实体层代码", "自动生成mapper配置代码", "自动生成service实现层代码", "自动生成control层代码", "自动生成mybatise配置文件代码", "自动生成前端代码", "自动生成dao实现层代码"};
	// 复制的路径前缀
//	private static String copyPrevpath = srcPath("com/blog/");
	private static String copyFilepathPrev = srcPath("com/shop/");
	private static String tempFilepathPrev = srcPath("temp/");
	// mybatis-config.xml 的路径 在ssm中需要
	private static String mybatisConfig = srcPath("config/mybatis-config.xml"); 
	// 要执行的命令下标  对应 前缀和后缀
	// 0 	自动生成dao层代码 接口模式
	// 8  	自动生成dao实现层代码 接口模式
	// 1 	自动生成service层代码
	// 2 	自动生成实体层代码
	// 3 	自动生成mapper配置代码
	// 4 	自动生成service实现层代码
	// 5 	自动生成control层代码
	// 6 	自动生成mybatise配置文件代码
	// 7  	自动生成前端代码
//	private static Integer[] commond = {0, 1, 2, 3, 4, 5, 6, 7};
//	private static Integer[] commond = {};
//	private static Integer[] commond = {0, 1, 2, 3, 4, 5, 6};
	private static Integer[] commond = {2, 1, 0, 4, 5, 8};
//	private static Integer[] commond = {2, 1, 0, 4, 8};
//	private static Integer[] commond = {5};
	// 实体类策略  普通通【GENERAL_STRATEGY】 hibernate注解【HIBER_ANNOTATION_STRATEGY】
	private static String entityStrategy = HIBER_ANNOTATION_STRATEGY;
	
	public static void main(String[] args) throws IOException {
		String fileds 	= "id province city area street userId name phone nickname";
		String columns	= "id province city area street user_id name phone nickname";	// 其中的值对应 fileds 用于数据库表字段的对应
		String types  	= "String String String String String String String String String"; // BigDecimal String Integer
		String texts 	= "ID 描述 时间 状态";
		
		ContextConfig c = new ContextConfig();
		c.setTable("address");
		c.setName_("address");
		c.setFileds(fileds);
		c.setColumns(columns);
		c.setTypes(types);
		doStart(c);
		
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
				String copyPath = copyFilepathPrev+prefix[index]+"/"+name+suffix[index];
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
	
	/**
	 * 
	 * @param name_		类名前缀小写 例：‘UserService.java’ 那么 ‘name_=user’
	 * @param table		表名
	 * @param classify	分类 前端文件夹 
	 * @param fileds	表字段  实体类生成、前端文件生成
	 * @param columns	数据库字段
	 * @param Texts		表字段名称 前端文件生成
	 * @param title		前端页面标题
	 */
	public static void doStart(ContextConfig c){
		if(	prefix.length != suffix.length 
			|| suffix.length != temp.length 
			|| prefix.length != temp.length){  // 配置长度不一致， 可能会导致异常
			System.err.println("prefix[len:"+prefix.length+"]，suffix[len:"+suffix.length+"]， temp[len:"+temp.length+"] 配置异常：长度不一致");
			return ;}
		
		core(c);	
	}

	@SuppressWarnings("resource")
	public static void core(ContextConfig c) {
		
		Scanner	sc = new Scanner(System.in);
		System.out.println("###################################################");
		System.out.println("####################代码自动生成工具####################");
		System.out.println("###################################################");
		System.err.println("运行中...");
		System.err.println("实体类【"+upFirst(c.getName_())+"】、table【"+c.getTable()+"】");
		System.err.println("将要执行的命令：");
		for(Integer index : commond)
			System.err.println("\t"+commomdText[index]);
		System.err.print("输入y继续，其他结束。请输入命令:");
		if(!"y".equals(sc.nextLine()))
		{ System.err.println("已结束。");return ;}
		sc.close();
		System.err.println("运行中...");
		for (Integer index : commond) {
			Configure conf = setParame(c, index);
			if(index == 2) {		// 生成实体类
				if(GENERAL_STRATEGY.equals(entityStrategy))
					do3(conf);
				else if (HIBER_ANNOTATION_STRATEGY.equals(entityStrategy))
					do3_2(conf);
				else 
					System.err.println("策略设置有误！");
			} else if(index == 6) { // 配置文件
				do5(upFirst(c.getName_()));
			} else if(index == 7){
				//args 根据字段生成表头js文
				try {
					do7(c.getFileds(), c.getTexts(), c.getClassify(), c.getTitle());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				do6(conf);	
			}
		} 
	}

	protected static Configure setParame(ContextConfig c, Integer index) {
		
		Configure conf = new Configure();
		conf.setCommomdText(commomdText[index]);
		conf.setFileds(c.getFileds());
		conf.setColumns(c.getColumns());
		conf.setTypes(c.getTypes());
		conf.setName(upFirst(c.getName_()));
		conf.setName_(c.getName_());
		conf.setPrefix(prefix[index]);
		conf.setSuffix(suffix[index]);
		conf.setTable(c.getTable());
		conf.setTemp(temp[index]);
		return conf;
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

	public static void do6(Configure conf) {
		
		CharStreamImpl c = new CharStreamImpl(tempFilepathPrev+conf.getTemp());
		CharStreamImpl copy = new CharStreamImpl(copyFilepathPrev+conf.getPrefix() + conf.getName() + conf.getSuffix());
		
		c.read(line -> {
			String str = (String) line;
			copy.write(str
					.replaceAll("#name#", conf.getName())
					.replaceAll("#name_#", conf.getName_())
					.replaceAll("#classify#", conf.getClassify())
					.replaceAll("#table#", conf.getTable()), true);
		});
		
		copy.close();
		
		print(conf);
		
	}
	
	/**
	 * 与do6方法相同，现已废弃
	 * <p>
	 * @param path
	 * @param copyPath
	 * @param name
	 * @param name_
	 * @param table
	 * void
	 * @see
	 * @since 1.0
	 */
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
				bw.write(line.replaceAll("#name#", name)
						.replaceAll("#name_#", name_)
						.replaceAll("#table#", table));
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
	
	/**
	 * 普通实体类
	 * <p>	 
	 * @param conf
	 * void
	 * @see
	 * @since 1.0
	 */
	public static void do3(Configure conf) {
		// name 作为实体类的名称 生成实体类 
		// 收集实体类属性 属性包含 修饰符，属性名，属性类型
		// 根据属性生成getter，setter方法
		CharStreamImpl c = new CharStreamImpl(copyFilepathPrev+conf.getPrefix()+conf.getName()+".java");
		
		c.write("package com.blog.entity;");
		c.write("public class "+conf.getName()+" extends Base{", true);
		
		for(String arg : conf.getFileds()) {
			c.write("\tprivate String "+arg+";", true);
		}
		
		c.write(System.lineSeparator());
		
		for(String arg : conf.getFileds()) {
			c.write("\tpublic String get"+upFirst(arg)+"() {", true);
			c.write("\t\treturn "+arg+";", true);
			c.write("\t}", true);
			c.write("\tpublic void set"+upFirst(arg)+"(String "+arg+") {", true);
			c.write("\t\tthis."+arg+" = "+arg+";", true);
			c.write("\t}", true);
		}
		
		c.write("}");
		c.close();
		
		print(conf);
	}
	
	/**
	 * hibernate注解模式 生成实体类 
	 * 注意 传字段第一个必须为主键
	 * <p>	 
	 * @param conf
	 * void
	 * @see
	 * @since 1.0
	 */
	public static void do3_2(Configure conf) {
		
		CharStreamImpl c = new CharStreamImpl(copyFilepathPrev+conf.getPrefix()+conf.getName()+conf.getSuffix());
		
		c.write("package com.shop.entity;");
		
		c.write(System.lineSeparator(), true);
		c.write("import javax.persistence.Column;", true);
		c.write("import javax.persistence.Entity;", true);
		c.write("import javax.persistence.Id;", true);
		c.write("import javax.persistence.Table;", true);
		c.write(System.lineSeparator(), true);
		
		c.write("@Entity", true);
		c.write("@Table(name = \""+ conf.getTable() +"\")", true);
		c.write("public class "+conf.getName()+" {", true);
		
		String[] fileds = conf.getFileds();
		String[] columns = conf.getColumns();
		String[] types = conf.getTypes();
		boolean onceFlag = true;
		for(int i = 0, len = fileds.length; i < len; i++) {
			if(onceFlag) {c.write("\t@Id", true); onceFlag = false;}
			c.write("\t@Column(name = \""+columns[i]+"\")", true);
			c.write("\tprivate "+types[i]+" "+fileds[i]+";", true);
		}
		
		c.write(System.lineSeparator(), true);
		
		for(int i = 0, len = fileds.length; i < len; i++) {
			c.write("\tpublic "+types[i]+" get"+upFirst(fileds[i])+"() {", true);
			c.write("\t\treturn "+fileds[i]+";", true);
			c.write("\t}", true);
			c.write("\tpublic void set"+upFirst(fileds[i])+"("+types[i]+" "+fileds[i]+") {", true);
			c.write("\t\tthis."+fileds[i]+" = "+fileds[i]+";", true);
			c.write("\t}", true);
		}
		
		c.write("}", true);
		c.close();
		
		print(conf);
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
	public static String srcPath(String path){
		return TempJava.class.getResource("/").getPath().substring(1).replace("build/classes", "src")+path;
	}
	public static String webContentPath(String path){
		//C:/Users/Administrator.USER-20160224QQ/git/repository6/MyBlog/WebContent/
		return TempJava.class.getResource("/").getPath().substring(1).replace("build/classes", "WebContent")+path;
	}
	private static String upFirst(String str) {
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	private static void print(Configure conf){
		System.err.println("执行命令："+conf.getCommomdText()+"，生成文件："+copyFilepathPrev+conf.getPrefix()+conf.getName()+conf.getSuffix());
	}
}

class ContextConfig {
	private String table;
	private String name_;
	private String columns;
	private String fileds;
	private String types;
	private String classify;
	private String texts;
	private String title;
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String[] getColumns() {
		return columns.split(" ");
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String[] getFileds() {
		return fileds.split(" ");
	}
	public void setFileds(String fileds) {
		this.fileds = fileds;
	}
	public String[] getTypes() {
		return types.split(" ");
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String[] getTexts() {
		return texts.split(" ");
	}
	public void setTexts(String texts) {
		this.texts = texts;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}

class Configure {
	private String name;
	private String name_;
	private Integer index;	// 下标 根据下标获取指定配置
	private String prefix; 	// com/shop/dao   				该值为dao
	private String suffix;  // com/shop/dao/UserDao.java  	该值为Dao.java
	private String temp;	// 模板文件 用于生成指定文件的模板文件
	private String commomdText;// 命令描述
	private String table;	// 
	private String[] columns;	// 数据库字段名 {"userId":"user_id"} 键值对的数据模式 
	private String[] fileds;// 属性集
	private String[] types;// 属性集
	private String classify;// 在博客项目中的控制类使用的分类参数
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getCommomdText() {
		return commomdText;
	}
	public void setCommomdText(String commomdText) {
		this.commomdText = commomdText;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	} 
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public String[] getFileds() {
		return fileds;
	}
	public void setFileds(String[] fileds) {
		this.fileds = fileds;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	} 
	
}
