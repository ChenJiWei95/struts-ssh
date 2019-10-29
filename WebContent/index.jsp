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
 	<a href="<%=basePath%>main_main_index?request_locale=zh_CN"><s:text name="shop.common.chinese"/></a>
    <a href="<%=basePath%>main_main_index?request_locale=en_US"><s:text name="shop.common.english"/></a>
	<!-- start top -->
	<div style="width: 100%;  ">
		<div class="layui-container" style="height: 30px;">  
			<span class="layui-breadcrumb" style="float: left; ">
				<a href="" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.welcome" /> 双十一采购队长<%-- ${user.name} --%></a>
			</span>
			<span class="layui-breadcrumb" lay-separator="|" style="float: right;">
			  <a href="<%=basePath%>main_main_login" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.login"/></a>
			  <a href="" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.orders"/></a>
			  <a href="" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.personalCenter"/></a> 
			</span>
		</div>
		<hr>
		<!-- start search -->
		<div class="layui-container" style="overflow: hidden;">
			<div style="height: 66px; " class="layui-row" style="padding: 11px;">
				<div class="layui-col-md2"> 
					<h1 style="color: #a77f2a69; font-size: 41px; line-height: 58px; margin-left: 10px; font-family: cursive;"><s:text name="shop.common.mallName" /></h1>
   	 			</div>
   	 			<div class="layui-col-md5" style="height: 100%;"> 
   	 			</div>
   	 			<div class="layui-col-md5" > 
   	 				<div class="layui-form" style="height: 34px; line-height: 36px; border: 1px solid #a77f2a69; width: 86%; padding: 1px; margin: 13px -5px; float: left;">
   	 					<input type="text" class="layui-input" placeholder='<s:text name="shop.common.search"/>' style="width: 382px; border: none; float: left; height: 35px;" />
   	 					<i class="layui-icon layui-icon-search" style="font-size: 20px; cursor: pointer; color: #a77f2a69;"></i>
   	 					<button lay-submit lay-filter="c-form-add" class="layui-hide">search</button>
   	 				</div>
   	 				<i class="layui-icon layui-icon-cart" style="font-size: 32px; cursor: pointer; color: #a77f2a69; float: right; margin-top: 14px;">
   	 					<span class="layui-badge" style="position: absolute; margin: -35px 20px; border-radius: 10px;">2</span>	
   	 				</i>
   	 			</div>
			</div>
			<!-- start nav -->
			<div style="height: 60px; " class="layui-row" style="padding: 11px;">
				<div class="layui-col-md1 white"> </div>
				<div class="layui-col-md10">
					<div style="height: 60px; " class="layui-row" style="padding: 11px;">
						<div class="layui-col-md2 c-nav"><a href="javascript:void(0)" style="color: #a77f2a69"><s:text name="shop.common.nav1"/></a></div> 
						<div class="layui-col-md2 c-nav"><a href="<%=basePath%>fonts_chtml_furnishing"><s:text name="shop.common.nav2"/></a></div>
						<div class="layui-col-md2 c-nav"><a href="javascript:void(0)"><s:text name="shop.common.nav3"/></a></div>
						<div class="layui-col-md2 c-nav"><a href="javascript:void(0)"><s:text name="shop.common.nav4"/></a></div>
						<div class="layui-col-md2 c-nav"><a href="javascript:void(0)"><s:text name="shop.common.nav5"/></a></div>
						<div class="layui-col-md2 c-nav"><a href="javascript:void(0)"><s:text name="shop.common.nav6"/></a></div> 
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- start flow picture -->
	<div style="width: 100%; height: 480px; background-image: url(<%=basePath%>resource/img/temp2.png); background-size: cover;" class="aqua">
		
	</div>
	<!-- start con goods red -->
	<div class="layui-container">
		<div style="height: 50px; " class="layui-row"> </div>
		<div style="height: 50px; " class="layui-row">
			<h2 style="font-size: 20px; font-weight: bold; color: #555;"><s:text name="shop.common.hotClassification"/></h2>
		</div>
		<div class="layui-row" style="height: 174px; background-image: url(<%=basePath%>resource/img/hot.png); background-size: cover;">
			<div class="layui-col-md4" style="height: 174px; cursor: pointer;"></div>
			<div class="layui-col-md4" style="height: 174px; cursor: pointer"></div>
			<div class="layui-col-md4" style="height: 174px; cursor: pointer "></div>
			<%-- <div class="layui-col-md4 red" style="height: 200px">
				<div  style="babackground-repeat: repeat-x; background: url(<%=basePath%>resource/img/goods1.jpg); height: 100%; cursor: pointer;"></div></div> 
			<div class="layui-col-md4 red" style="height: 200px">
				<div  style="babackground-repeat:repeat-x; background-image:url(<%=basePath%>resource/img/goods17.jpg); height: 100%; cursor: pointer;"></div></div> 
			<div class="layui-col-md4 red" style="height: 200px">
				<div  style="babackground-repeat:repeat-x; background-image:url(<%=basePath%>resource/img/goods2.jpg); height: 100%; cursor: pointer;"></div></div>  --%>
		</div>
	</div>
	<!-- start bom --> 
	<div style="height: 100px; " class="layui-row"> </div>
	<div style="width: 100%;  background: #000000d4; position: fixed; bottom: 0;">
		<div class="layui-container" style="height: 50px; ">
			<div class="layui-row">
				<div style="height: 60px; padding: 11px; text-align: center;" class="layui-row"> 
					<span class="span-separator">
					  <a href="" style="line-height: 40px; padding: 28px; color: #ffffffb3;"><s:text name="shop.common.aboutUs"/></a>
					  <span class="lay-separator" style="color: #ffffffb3;">|</span>
					  <a href="" style="line-height: 40px; padding: 28px; color: #ffffffb3;"><s:text name="shop.common.help"/></a>
					  <span class="lay-separator" style="color: #ffffffb3;">|</span>
					  <a href="" style="line-height: 40px; padding: 28px; color: #ffffffb3;"><s:text name="shop.common.aftersaleService"/></a> 
					  <span class="lay-separator" style="color: #ffffffb3;">|</span>
					  <a href="" style="line-height: 40px; padding: 28px; color: #ffffffb3;"><s:text name="shop.common.deliveryService"/></a> 
					  <span class="lay-separator" style="color: #ffffffb3;">|</span>
					  <a href="" style="line-height: 40px; padding: 28px; color: #ffffffb3;"><s:text name="shop.common.supplySource"/></a> 
					</span>	
				</div>
			</div>
		</div>
		<div class="layui-container">
			<li style="color: #ffffffb3	; list-style: none; padding:10px 245px; text-align: center; ">Copyright ©2019 Powered By <span  style="color: #ffffffb3; font-family: cursive; cursor:pointer;" title="<s:text name="shop.common.mallName" />"><s:text name="shop.common.mallName" /></span> Version 1.0.0</li>
		</div>
	</div> 
	<!-- bom end -->
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element'], function(){
 		var element = layui.element;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 	})
	</script>
 </body>
</html>