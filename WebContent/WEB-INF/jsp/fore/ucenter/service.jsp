<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.service"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 </head>
 <body>
 	<%@ include file="../../include/forePage/mall/languageBt.jsp" %>
	
	<%@ include file="../../include/forePage/mall/siteNav.jsp" %>
	<%@ include file="../../include/forePage/mall/search.jsp" %>
	<%@ include file="../../include/forePage/mall/mallNav.jsp" %>
	
	<div class="cnt">
		<!-- nav text -->
		<div class="layui-container" style="height: 30px;">
			<span class="layui-breadcrumb" lay-separator=">" style="line-height: 30px;">
			  <a href="<%=basePath%>fore_main_index"><s:text name="shop.common.nav1"/></a>
			  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.service"/></a>
			</span>
		</div>
		<div class="layui-container" style="margin-top: 20px; ">
			<%@ include file="../../include/forePage/ucenter/centerNav.jsp" %>
			<!-- <div class="right-con " style="float: right; width: 905px;">
				
			</div>  -->
			<%@ include file="../../include/forePage/ucenter/serviceCon.jsp" %>
		</div>
	</div>
	
	
	<!-- start bom --> 
	<%@ include file="../../include/forePage/mall/bom.jsp" %>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element'], function(){
 		var element = layui.element
 		$ = layui.$;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		$(".service").eq(0).addClass("var-color");
 	})
	</script>
 </body>
</html>