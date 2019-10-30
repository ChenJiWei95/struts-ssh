<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.goodsDetails"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 </head>
 <body>
 	<%@ include file="../../include/forePage/mall/languageBt.jsp" %>
	
	<%@ include file="../../include/forePage/mall/siteNav.jsp" %>
	<%@ include file="../../include/forePage/mall/search.jsp" %>
	<%@ include file="../../include/forePage/mall/mallNav.jsp" %>
	<!-- nav text -->
	<div class="layui-container" style="height: 30px;">
		<span class="layui-breadcrumb" lay-separator=">" style="line-height: 30px;">
		  <a href="<%=basePath%>fore_main_index"><s:text name="shop.common.nav1"/></a>
		  <a href="<%=basePath%>mall_chtml_furnishing"><s:text name="shop.common.nav2"/></a>
		  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.goodsDetails"/></a>
		</span>
	</div>
	 
	
	<!-- start bom --> 
	<%@ include file="../../include/forePage/mall/bom.jsp" %>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element'], function(){
 		var element = layui.element;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 	})
	</script>
 </body>
</html>