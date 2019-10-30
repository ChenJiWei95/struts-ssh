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
  <title><s:text name="shop.common.furnis"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <style contenteditable="" draggable="true">
	.border { border: 1px solid #ddd;} 
	.red { background: red; height: 100%;} 
	.blue { background: blue; height: 100%;}
	.aqua { background: aqua; height: 100%;}
	.white { background: white; height: 100%;}
	.pointer {cursor: pointer;} .pointer:hover {box-shadow: 1px 1px 20px -2px #ddd;}
	.default-color {color: #999 !important;}
	.var-color {color: #a77f2a69!important;}
	* {
		padding: 0px; 
		margin: 0px;
		font-family: "SFMono-Regular",Consolas,"Liberation Mono",Menlo,Courier,monospace;
	}
	
	.c-nav { 
		color: #555;
    	text-align: center;
   	 	line-height: 71px;
   		height: 100%;
   		cursor: pointer;
    }
    .c-nav:hover {
    	color: #a77f2a69;
    }
    .layui-breadcrumb a:hover {
    	color: #a77f2a69 !important;
    }
    .span-separator a:hover {
    	color: #fff !important;
    }
    .filter label {
	    color: #999;
	    cursor: pointer;
	}
	.filter span {
		color: #999;
	}
	.filter label:hover {
	    color: #a77f2a69;
	}
	
  </style>
 </head>
 <body>
 	<a href="<%=basePath%>fonts_chtml_furnishing?request_locale=zh_CN"><s:text name="shop.common.chinese"/></a>
    <a href="<%=basePath%>fonts_chtml_furnishing?request_locale=en_US"><s:text name="shop.common.english"/></a>
	
	<!-- nav text -->
	<div class="layui-container" style="height: 30px;">
		<span class="layui-breadcrumb" lay-separator=">" style="line-height: 30px;">
		  <a href="<%=basePath%>main_main_index"><s:text name="shop.common.nav1"/></a>
		  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.nav2"/></a>
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
 	layui.use(['layer', 'element'], function(){
 		var element = layui.element;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 	})
	</script>
 </body>
</html>