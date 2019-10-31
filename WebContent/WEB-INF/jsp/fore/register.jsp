<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:text name="shop.common.registerPage"/></title>
	<link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 	 
  </head>
  <body style="height: 100%;" >
  	<%@ include file="../include/forePage/mall/languageBt.jsp" %>
  	
	<!-- start mall nav -->
	<%@ include file="../include/forePage/mall/mallNav.jsp" %>
	
	<!-- start picture and register form -->
	<div style="width: 100%; height: 680px; background-image: url(<%=basePath%>resource/img/bg.jpg); overflow: hidden; background-size: cover;" class="aqua">
		<div class="layui-form" lay-filter="c-register-form" id="c-register-form" style="width: 254px; height: 518px; background: #fff; margin: 80px auto; padding: 29px; ">
			<div class="layui-form-item" style="text-align: center; line-height: 65px; font-family: cursive; color: #a77f2a69;"><h1><s:text name="shop.common.mallName"/></h1></div>
			<div class="layui-form-item" style="margin-top: 0px;">
				<!-- <i class="layui-icon layui-icon-user" style="position: absolute; font-size: 28px; "></i> -->
				<input class="layui-input" type="text" name="user.username" lay-verify="required" placeholder='<s:text name="shop.login.username"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<!-- <i class="layui-icon"></i> -->
				<input class="layui-input" type="password" name="user.password" lay-verify="pass" placeholder='<s:text name="shop.login.password"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<!-- <i class="layui-icon"></i> -->
				<input class="layui-input" type="password" name="repassword" lay-verify="repass" placeholder='<s:text name="shop.login.repassword"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<input class="layui-input" type="text" name="userInfor.name" lay-verify="required" placeholder='<s:text name="shop.login.name"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<input class="layui-input" type="text" name="userInfor.email" placeholder='<s:text name="shop.login.email"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 10px;">
				<input class="layui-input" type="text" name="userInfor.phone" lay-verify="required" placeholder='<s:text name="shop.login.phone"/>' style="height: 46px;"/>
			</div>
			<div class="layui-form-item" style="margin-top: 30px;">
				<button lay-submit lay-filter="c-form-sub" class="layui-btn layui-btn-fluid" style="background: #a77f2a69; "><s:text name="shop.common.register"/></button>
			</div>
		</div>
	</div>
	
	<!-- start bom -->
	<%@ include file="../include/forePage/mall/bom.jsp" %>
  <script src="<%=basePath%>resource/layui/layui.js"></script> 
  <script>
  layui.use(['form', 'layer', 'util'], function(){
    var layer = layui.layer
    ,form = layui.form
    ,util = layui.util
	,a = "c-form-sub"
	form.on("submit("+a+")", function(data){
		util.formAjax({
			url: '<%=basePath%>user_save'
			,data: data.field
			,layCallback: function() {
				location.href = '<%=basePath%>fore_main_login';
			}
			,success: function(msg){
				console.log(msg);
			}
			,error: function(msg){
				console.log(msg);
			}
		});
		return false;
	})
  })
  </script>
</body>
</html>