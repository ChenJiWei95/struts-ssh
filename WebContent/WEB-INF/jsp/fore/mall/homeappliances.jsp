<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.nav3"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 </head>
 <body>
 	<%@ include file="../../include/forePage/mall/languageBt.jsp" %>
	
	<!-- nav text -->
	<div class="layui-container" style=" ">
		<span class="layui-breadcrumb" lay-separator=">" style="line-height: 55px;">
		  <a href="<%=basePath%>fore_main_index"><s:text name="shop.common.nav1"/></a>
		  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.nav3"/></a>
		</span>
	</div>
	
	<!-- start picture -->
	<div class="layui-container" style="height: 180px; background-image: url(<%=basePath%>resource/img/temp2.png); background-size: cover;" class="aqua">
	</div>
	
	<!-- start filter --> 
	<%@ include file="../../include/forePage/mall/searchFilter.jsp" %>
	
	<!-- start goods list -->
	<%@ include file="../../include/forePage/mall/goodsList.jsp" %>
	
	<!-- start bom --> 
	<%@ include file="../../include/forePage/mall/bom.jsp" %>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element', 'util'], function(){
 		var element = layui.element;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		layui.util.layHref(); // 链接跳转
 	})
	</script>
 </body>
</html>