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
  <title><s:text name="shop.common.homePage"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <style contenteditable="" draggable="true">
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
	
	.c-nav{ 
		color: #555;
    	text-align: center;
   	 	line-height: 71px;
   		height: 100%;
   		cursor: pointer;
    }
    .c-nav a:hover {
    	color: #a77f2a69;
    }
    .layui-breadcrumb a:hover {
    	color: #a77f2a69 !important;
    }
    .span-separator a:hover {
    	color: #fff !important;
    }
  </style>
 </head>
 <body>
 	<a href="<%=basePath%>fore_main_index?request_locale=zh_CN"><s:text name="shop.common.chinese"/></a>
    <a href="<%=basePath%>fore_main_index?request_locale=en_US"><s:text name="shop.common.english"/></a>
	
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
 	layui.use(['layer', 'element'], function(){
 		var element = layui.element;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 	})
	</script>
 </body>
</html>