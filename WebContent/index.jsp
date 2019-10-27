<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// basePath：localhost:8080/项目名/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>商城首页- 模块- 整体框架</title> 
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
  </style>
 </head>
 <body>
	<!-- start top -->
	<div style="width: 100%;  ">
		<div class="layui-container" style="height: 30px;">  
			<span class="layui-breadcrumb" style="float: left; ">
				<a href="" style="line-height: 40px; padding: 10px;">欢迎 123787客官 光临有间商城</a>
			</span>
			<span class="layui-breadcrumb" lay-separator="|" style="float: right;">
			  <a href="" style="line-height: 40px; padding: 10px;">登录</a>
			  <a href="" style="line-height: 40px; padding: 10px;">我的订单</a>
			  <a href="" style="line-height: 40px; padding: 10px;">个人中心</a> 
			</span>
		</div>
		<hr>
		<div class="layui-container" style="overflow: hidden;">
			<div style="height: 60px; " class="layui-row" style="padding: 11px;">
				<div class="layui-col-md2"> 
					<h1 style="color: #a77f2a69; font-size: 35px; line-height: 65px; margin-left: 10px; font-family: cursive;">有间商城</h1>
   	 			</div>
   	 			<div class="layui-col-md5" style="height: 100%;"> 
   	 			</div>
   	 			<div class="layui-col-md5" > 
   	 				<div class="layui-form" style="height: 34px; line-height: 36px; border: 1px solid #a77f2a69; width: 90%; padding: 3px; margin: 8px; ">
   	 					<input type="text" class="layui-input" placeholder="搜索商品" style="width: 382px; border: none; float: left; height: 36px;" />
   	 					<i class="layui-icon layui-icon-search" style="font-size: 20px; cursor: pointer; color: #a77f2a69;"></i>
   	 					<button lay-submit lay-filter="c-form-add" class="layui-hide">search</button>
   	 				</div>
   	 			</div>
			</div>
			<div style="height: 60px; " class="layui-row" style="padding: 11px;">
				<div class="layui-col-md1 white"> </div>
				<div class="layui-col-md10">
					<div style="height: 60px; " class="layui-row" style="padding: 11px;">
						<div class="layui-col-md2 c-nav" style="color: #a77f2a69"><font>首页</font></div> 
						<div class="layui-col-md2 c-nav"><font>家具用品</font></div>
						<div class="layui-col-md2 c-nav"><font>小家电</font></div>
						<div class="layui-col-md2 c-nav"><font>洗护</font></div>
						<div class="layui-col-md2 c-nav"><font>厨具</font></div>
						<div class="layui-col-md2 c-nav"><font>日用品</font></div> 
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- start search&nav -->
	
	<!-- start flow picture -->
	<div style="width: 100%; height: 480px; background-image: url(<%=basePath%>resource/img/bg.jpg);" class="aqua">
		
	</div>
	<!-- start con goods red -->
	<div class="layui-container">
		<div style="height: 50px; " class="layui-row">
		</div>
		<div style="height: 50px; " class="layui-row">
			<h2 style="font-size: 20px; font-weight: bold; color: #555;">热门分类</h2>
		</div>
		<div style="" class="layui-row">
			<div class="layui-col-md4 red" style="height: 200px">
				<div  style="babackground-repeat: repeat-x; background: url(<%=basePath%>resource/img/goods1.jpg); height: 100%; cursor: pointer;"></div></div> 
			<div class="layui-col-md4 red" style="height: 200px">
				<div  style="babackground-repeat:repeat-x; background-image:url(<%=basePath%>resource/img/goods17.jpg); height: 100%; cursor: pointer;"></div></div> 
			<div class="layui-col-md4 red" style="height: 200px">
				<div  style="babackground-repeat:repeat-x; background-image:url(<%=basePath%>resource/img/goods2.jpg); height: 100%; cursor: pointer;"></div></div> 
		</div>
	</div>
	<!-- start bom --> 
	<div class="layui-container">
		<div style="height: 50px; " class="layui-row">
		</div>
		<li style="color:#586069;list-style:none;padding:10px 245px; text-align: center;">Copyright ©2019 <span style="color:#a71d5d;cursor:pointer;" title="shop">shop</span> Powered By <span  style="color:#a71d5d;cursor:pointer;" title="程序目录">Login</span> Version 1.0.0</li>
	</div>	
	<!-- bom end -->
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element'], function(){
 		var element = layui.element;
 		layui.layer.msg("正在使用layui。")
 	})
	</script>
 </body>
</html>