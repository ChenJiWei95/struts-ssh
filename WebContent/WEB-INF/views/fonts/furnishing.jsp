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
	<!-- nav alert -->
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
	<div class="layui-container" style="margin-top: 30px;" >	
		<div class="layui-row layui-col-space10">
			<div class="layui-col-md12">
				<div class="grid-demo grid-demo-bg1">
					<div class="filter" style="height: 40px; line-height: 40px; border:1px solid #dddddd73; padding-left: 10px;">
						<span style="width: 56px; display: inline-block;"><s:text name="shop.common.classification"/></span>
						<label style="padding-right: 12px;padding-left: 12px; color: #a77f2a69 !important;">全部</label>
						<label style="padding-right: 12px;padding-left: 12px;">床品</label>
						<label style="padding-right: 12px;padding-left: 12px;">灯具</label>
						<label style="padding-right: 12px;padding-left: 12px;">布艺</label>
						<label style="padding-right: 12px;padding-left: 12px;">收纳</label>
						<label style="padding-right: 12px;padding-left: 12px;">小家具</label>
					</div>
					<div class="filter" style="height: 40px; line-height: 40px; border:1px solid #dddddd73; border-top: none; padding-left: 10px;">
						<span style="width: 56px; display: inline-block;"><s:text name="shop.common.season"/></span>
						<label style="padding-right: 12px;padding-left: 12px; color: #a77f2a69 !important;">全部</label>
						<label style="padding-right: 12px;padding-left: 12px;">春</label>
						<label style="padding-right: 12px;padding-left: 12px;">夏</label>
						<label style="padding-right: 12px;padding-left: 12px;">秋</label>
						<label style="padding-right: 12px;padding-left: 12px;">冬</label>
					</div>
					<div class="filter" style="height: 40px; line-height: 40px; border:1px solid #dddddd73; border-top: none; padding-left: 10px;">
						<span style="width: 56px; display: inline-block;"><s:text name="shop.common.sort"/></span>
						<label style="padding-right: 12px;padding-left: 12px; color: #a77f2a69 !important; ">全部</label>
						<label style="padding-right: 12px;padding-left: 12px;">价格</label>
						<label style="padding-right: 12px;padding-left: 12px;">上架时间</label>
					</div>
				</div>
			</div> 
		</div> 
	</div> 
	
	<!-- start goods -->
	<div class="layui-container " style="overflow: hidden; margin-top: 30px;">
		<div class="layui-row layui-col-space20">
        <div class="layui-col-md3">
          <div class="grid-demo grid-demo-bg1 pointer" style="height: 300px; border:1px solid #dddddd73;">
			<div style="background-image:url(<%=basePath%>resource/img/good1.png); background-size: cover; width: 100%; height: 248px;"></div>
			<div class="default-color" style="color: #555; padding-left: 10px; line-height: 30px; height: 26px;">轻奢休闲套桌</div>
			<div style="color: red; padding-left: 10px; line-height: 26px; height: 26px;">￥200</div>
		  </div>
        </div>
        <div class="layui-col-md3">
          <div class="grid-demo pointer" style="height: 300px; border:1px solid #dddddd73;">	
          	<div style="background-image:url(<%=basePath%>resource/img/good2.png); background-size: cover; width: 100%; height: 248px;"></div>
			<div class="default-color" style="color: #555; padding-left: 10px; line-height: 30px; height: 26px;">轻奢休闲套桌</div>
			<div style="color: red; padding-left: 10px; line-height: 26px; height: 26px;">￥200</div>
		  </div>
        </div>
        <div class="layui-col-md3">
          <div class="grid-demo grid-demo-bg1 pointer" style="height: 300px; border:1px solid #dddddd73;">
          	<div style="background-image:url(<%=basePath%>resource/img/good3.png); background-size: cover; width: 100%; height: 248px;"></div>
			<div class="default-color" style="color: #555; padding-left: 10px; line-height: 30px; height: 26px;">轻奢休闲套桌</div>
			<div style="color: red; padding-left: 10px; line-height: 26px; height: 26px;">￥200</div>
          </div>
        </div>
        <div class="layui-col-md3">
          <div class="grid-demo pointer" style="height: 300px; border:1px solid #dddddd73;">
          	<div style="background-image:url(<%=basePath%>resource/img/good4.png); background-size: cover; width: 100%; height: 248px;"></div>
			<div class="default-color" style="color: #555; padding-left: 10px; line-height: 30px; height: 26px;">轻奢休闲套桌</div>
			<div style="color: red; padding-left: 10px; line-height: 26px; height: 26px;">￥200</div>
          </div>
        </div>
        <div class="layui-col-md3">
          <div class="grid-demo grid-demo-bg1 pointer" style="height: 300px; border:1px solid #dddddd73;">
          	<div style="background-image:url(<%=basePath%>resource/img/good3.png); background-size: cover; width: 100%; height: 248px;"></div>
			<div class="default-color" style="color: #555; padding-left: 10px; line-height: 30px; height: 26px;">轻奢休闲套桌</div>
			<div style="color: red; padding-left: 10px; line-height: 26px; height: 26px;">￥200</div>
          </div>
        </div>
        <div class="layui-col-md3">
          <div class="grid-demo pointer" style="height: 300px; border:1px solid #dddddd73;">
          	<div style="background-image:url(<%=basePath%>resource/img/good4.png); background-size: cover; width: 100%; height: 248px;"></div>
			<div class="default-color" style="color: #555; padding-left: 10px; line-height: 30px; height: 26px;">轻奢休闲套桌</div>
			<div style="color: red; padding-left: 10px; line-height: 26px; height: 26px;">￥200</div>
          </div>
        </div>
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