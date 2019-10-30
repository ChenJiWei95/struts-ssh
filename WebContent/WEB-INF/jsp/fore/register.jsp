<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
// basePath：localhost:8080/项目名/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:text name="shop.common.loginPage"/></title>
	<link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
	<style>
	.border { border: 1px solid #ddd;} 
	.red { background: red; height: 100%;} 
	.blue { background: blue; height: 100%;}
	.aqua { background: aqua; height: 100%;}
	.white { background: white; height: 100%;}
	* {
		padding: 0px; 
		margin: 0px;
		font-family: "SFMono-Regular",Consolas,"Liberation Mono",Menlo,Courier,monospace;
	}
	
	.c-nav { 
		color: #555;
    	text-align: center;
   	 	line-height: 55px;
   		height: 100%;
   		cursor: pointer;
    }
    .c-nav a:hover {
    	color: #a77f2a69;
    }
    .span-separator a:hover {
    	color: #fff !important;
    }
	</style>
  </head>
  <body style="height: 100%;" >
  	<a href="<%=basePath%>fore_main_register?request_locale=zh_CN"><s:text name="shop.common.chinese"/></a>
    <a href="<%=basePath%>fore_main_register?request_locale=en_US"><s:text name="shop.common.english"/></a>
	<!-- start mall nav -->
	<%@ include file="../include/forePage/mall/mallNav.jsp" %>
	
	<!-- start picture and register form -->
	<div style="width: 100%; height: 680px; background-image: url(<%=basePath%>resource/img/bg.jpg); overflow: hidden; background-size: cover;" class="aqua">
		<div class="layui-form" style="width: 254px; height: 466px; background: #fff; margin: 80px auto; padding: 29px; ">
			<div class="layui-form-item" style="text-align: center; line-height: 65px; font-family: cursive; color: #a77f2a69;"><h1><s:text name="shop.common.mallName"/></h1></div>
			<div class="layui-form-item" style="margin-top: 0px;">
				<!-- <i class="layui-icon layui-icon-user" style="position: absolute; font-size: 28px; "></i> -->
				<input class="layui-input" type="text" name="username" placeholder='<s:text name="shop.login.username"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<!-- <i class="layui-icon"></i> -->
				<input class="layui-input" type="text" name="password" placeholder='<s:text name="shop.login.password"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<!-- <i class="layui-icon"></i> -->
				<input class="layui-input" type="text" name="repassword" placeholder='<s:text name="shop.login.repassword"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<input class="layui-input" type="text" name="email" placeholder='<s:text name="shop.login.email"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<input class="layui-input" type="text" name="phone" placeholder='<s:text name="shop.login.phone"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 30px;">
				<button lay-submit class="layui-btn layui-btn-fluid" style="background: #a77f2a69; "><s:text name="shop.common.register"/></button>
			</div>
		</div>
	</div>
	
	<!-- start bom -->
	<%@ include file="../include/forePage/mall/bom.jsp" %>
	</body>
</html>