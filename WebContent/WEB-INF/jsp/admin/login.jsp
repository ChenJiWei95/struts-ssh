<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:text name="shop.common.loginPage"/></title>
	<link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
	 
  </head>
  <body style="height: 100%;">
  	<h1>后台管理系统 登录页面 </h1>
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
				url: '<%=basePath%>user_login'
				,data: data.field
				,layCallback: function() {
					location.href = '<%=basePath%>fore_main_index';
				}  
			});
			return false;
		})
  	})
  </script>
</body>
</html>