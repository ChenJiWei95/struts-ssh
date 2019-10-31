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
	
	<%@ include file="../../include/forePage/mall/mallNav.jsp" %>
	<form class="layui-form" action="">
	<div class="layui-container cartlist-cnt" style="margin-top: 40px;">
		<div class="layui-row cartlist">
			<div class="layui-col-md1 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-skin="primary" title="<s:text name="shop.common.addCheck"/>">
				</div>
			</div>
			<div class="layui-col-md4" ><s:text name="shop.common.goods"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.subtotal"/></div>
			<div class="layui-col-md1"><s:text name="shop.common.operation"/></div>
		</div>
		<div class="layui-row cartlist-etc pointer" style="overflow: hidden;">
			<div class="layui-col-md1 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-skin="primary" title="">
				</div>
			</div>
			<div class="layui-col-md4">
				<%-- <div style="background-image: url(<%=basePath%>resource/img/good2.png); width: 100px; height: 100px; float: left;"></div>
				 --%><img src="<%=basePath%>resource/img/good2.png" width="100px" height= "100px"/>
				<span>轻奢休闲套桌</span>
			</div>
			<div class="layui-col-md2"><span><font style="color: red; font-size: 16px;">￥200</font><del>￥240</del></span></div>
			<div class="layui-col-md2">
				<div style="float: left; margin-left: 20px;">
					<span class="subtraction border">-</span>
					<input type="text" class="border number" value="2"/>
					<span class="addition border">+</span>
				</div>
			</div>
			<div class="layui-col-md2"><span>￥400</span></div>
			<div class="layui-col-md1"><span class="delete"><s:text name="shop.common.delete"/></span></div>
		</div>
		<div class="layui-row cartlist-etc pointer" style="overflow: hidden;">
			<div class="layui-col-md1 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-skin="primary" title="">
				</div>
			</div>
			<div class="layui-col-md4">
				<%-- <div style="background-image: url(<%=basePath%>resource/img/good2.png); width: 100px; height: 100px; float: left;"></div>
				 --%><img src="<%=basePath%>resource/img/good2.png" width="100px" height= "100px"/>
				<span>轻奢休闲套桌</span>
			</div>
			<div class="layui-col-md2">
				<span><font style="color: red; font-size: 16px;">￥200</font>
				<del>￥240</del></span></div>
			<div class="layui-col-md2">
				<div style="float: left; margin-left: 20px;">
					<span class="subtraction border">-</span>
					<input type="text" class="border number" value="2"/>
					<span class="addition border">+</span>
				</div>
			</div>
			<div class="layui-col-md2"><span>￥400</span></div>
			<div class="layui-col-md1"><span class="delete"><s:text name="shop.common.delete"/></span></div>
		</div>
	</div>
	<div class="layui-container settlement-bar" style="padding-left: 0; padding-right: 0; height: 80px; background: #99999930;">
		<div class="layui-row">
			<div class="layui-col-md2 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-skin="primary" title="<s:text name="shop.common.selected"/>"><span>3</span>
				</div>
			</div>
			<div class="layui-col-md2"><font class="all-delete"><s:text name="shop.common.batchdelete"/></font></div>
			<!-- <div class="layui-col-md1"></div> -->
			<div class="layui-col-md1">&nbsp;</div>
			<div class="layui-col-md2">&nbsp;</div>
			<div class="layui-col-md3">	 
				<s:text name="shop.common.payment"/><font style="color: red; font-size: 24px;"><s:text name="shop.common.currency"/>1114.00</font><del><s:text name="shop.common.currency"/>1314.00</del>
			</div>
			<div class="layui-col-md2">
				<div style="background: #a77f2a69; font-size: 18px; width: 100%; height: 100%; color: #fff; font-weight: 600;" class="layui-btn"><s:text name="shop.common.settlement"/></div>
			</div>
		</div>
	</div>
	</form> 
		
	<!-- start bom --> 
	<%-- <%@ include file="../../include/forePage/mall/bom.jsp" %> --%>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element', 'form'], function(){
 		var element = layui.element
 		$ = layui.$
 		form = layui.form;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		$(".service").eq(0).addClass("var-color");
 	})
	</script>
 </body>
</html>