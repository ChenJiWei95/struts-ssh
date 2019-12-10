<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.personalCenter"/></title> 
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
			  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.personalCenter"/></a>
			</span>
		</div>
		<div class="layui-container" style="margin-top: 20px; ">
			<%@ include file="../../include/forePage/ucenter/centerNav.jsp" %>
			<%@ include file="../../include/forePage/ucenter/pcenterCon.jsp" %>
		</div>
	</div>
	
	
	<!-- start bom --> 
	<%@ include file="../../include/forePage/mall/bom.jsp" %>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element', 'util'], function(){
 		var element = layui.element
 		,$ = layui.$
 		,layer = layui.layer
 		,util = layui.util;
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		layui.$(".pcenter").eq(0).addClass("var-color");
 		util.layHref(); // 链接跳转
 		util.shopClick({
 			payment: function(e){
 				console.log("-----");
 				console.log(e.parents(".p-order").eq(0).find(".p-label-number-id").eq(0));
 				//self.location = "<%=basePath%>mall_chtml_payment?id="+data
 			}
 			,orderDetails: function(e){
 				
 			}
 			,
 			cancelOrder: function(e){
 				var data1 = {};
 				var str = e.parents(".p-order").eq(0).find(".p-label-number-id").eq(0).text();
 				console.log(str);
 				console.log(str.substring(str.indexOf("：")+1));
 				data1["Up_paymentStatus_s"] = '03'// 已退款
 				,data1["Qu_id_eq_s"] = str.substring(str.indexOf("：")+1);
 				; 
 				util.formAjax({
	 				url: '<%=basePath%>order_update'
	 				,contentType: 'application/x-www-form-urlencoded'
	 				,data: data1
	 			});
 			}
 		});
 	})
	</script>
 </body>
</html>