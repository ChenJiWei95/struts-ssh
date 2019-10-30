<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.homePage"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 	
 </head>
 <body> 
 	<%@ include file="../include/forePage/mall/languageBt.jsp" %>
	
	<!-- start site nav -->
	
	<%@ include file="../include/forePage/mall/siteNav.jsp" %>
	
	<!-- start search -->
	<%@ include file="../include/forePage/mall/search.jsp" %>
	
	<!-- start mall nav -->
	<%@ include file="../include/forePage/mall/mallNav.jsp" %>
	
	<!-- start flow picture -->
	<%@ include file="../include/forePage/mall/flowPic.jsp" %>
	
	<!-- start popularClassifi -->
	<%@ include file="../include/forePage/mall/popularClassifi.jsp" %>
	
	<!-- start bom -->
	<%@ include file="../include/forePage/mall/bom.jsp" %>
	
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['carousel', 'layer', 'element'], function(){
 		var element = layui.element
 		,carousel = layui.carousel;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		carousel.render({
 		    elem: '#test2'
 		    ,interval: 1800
 		    ,width: '100%'
 		    ,height: '480px'
 		    ,anim: 'fade' 
 		});
 	})
	</script>
 </body>
</html>