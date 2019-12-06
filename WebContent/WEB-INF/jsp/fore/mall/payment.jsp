<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	 
	<%@ include file="../../include/forePage/mall/mallNav.jsp" %>
	<div class="layui-container cartlist-cnt" style="margin-top: 40px;">
		<div class="layui-card">
			<div class="layui-card-header">收货地址</div>
		   	<div class="layui-card-body"> 
				<select id="">
				<c:forEach begin="0" items="${address}" step="1" var="Address" varStatus="varsta">
					<option value="${Address.id}">${Address.province+"-"+Address.city+"-"+Address.area+"-"+Address.street}</option>
				</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-card">
			<div class="layui-card-header">订单详细</div>
			<div class="layui-card-body">
				订单号：${order.id} &nbsp; &nbsp; &nbsp; &nbsp; 下单时间：${order.createDate}
			</div>
		</div>
		
		<div class="layui-card">
			<div class="layui-card-header">商品明细</div>
			<div class="layui-card-body">
				<div class="layui-row cartlist">
					<div class="layui-col-md4"><s:text name="shop.common.goods"/></div>
					<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
					<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
					<div class="layui-col-md2"><s:text name="shop.common.colour"/></div>
					<div class="layui-col-md2"><s:text name="shop.common.size"/></div>
				</div>
				<c:forEach begin="0" items="${orderItems}" step="1" var="OrderItem" varStatus="varsta">
				<div class="layui-row cartlist-etc pointer" style="overflow: hidden;" data-id="${OrderItem.id}">
					<div class="layui-col-md4" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${OrderItem.goodsId}">
						<img src="<%=basePath%>${OrderItem.url}" width="50px" height= "50px"/>
						<span>${OrderItem.name}</span>
					</div>
					<div class="layui-col-md2"><span>￥<fmt:formatNumber type="number" value="${OrderItem.price*OrderItem.discount}" maxFractionDigits="2"/><del class="ori-price">￥${CartList.price}</del></span></div>
					<div class="layui-col-md2"><span>${OrderItem.count}</span></div>
					<div class="layui-col-md2"><span class="color">${OrderItem.colour}</span></div>
					<div class="layui-col-md2"><span class="size">${OrderItem.size}</span></div>
				</div>
				</c:forEach> 
			</div>
		</div>
		
		<!-- 价格明细 -->
		<!-- <div class="layui-card">
			<div class="layui-card-header">价格明细</div>
			<div class="layui-card-body">
			
			</div>
		</div> -->
		
		<!-- 支付方式 -->
		<div class="layui-card">
			<div class="layui-card-header">支付方式</div>
			<div class="layui-card-body">
			
			</div>
		</div>
		
		<!-- 确认支付 -->
		<div class="layui-card">
			<span>
				<font class="total-amount" style="color: red; font-size: 24px;">${order.totalAmount}元</font>
				<del class="orig-total-amount">${order.originalAmount}元</del>
			</span>
			<span class="buyNow border" shop-click="buy"><s:text name="shop.common.buy"/></span>
		</div>
		
	</div>
	
	<!-- start bom --> 
	<%@ include file="../../include/forePage/mall/bom.jsp" %>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element', 'util'], function(){
 		var element = layui.element
 		,util = layui.util
 		,$ = layui.$
 		,layer = layui.layer;
 		util.addSubCtrlbtn(function(data){ // 加减器
 		});
 		util.shopClick({
 			buy: function(){
 				var data1 = {};
 				data1['logistics.id'] = '';
 				util.formAjax({
 					url: '<%=basePath%>logistics_save'
 					,data: data1
 				});
 				var data2 = {};
 				data2["Up_paymentStatus_s"] = "00"
 				//,data2["order.logisticsStatus"] = "02"
 				,data2["Qu_id_eq_s"] = "${order.id}"
 				;
 				util.formAjax({
 					url: '<%=basePath%>order_update'
 					,data: data2
 				});
 			}
 			,addCart: function(){
 				var data = {};
 				data["cartList.goodsId"] = '${goods.id}';
 				data["cartList.count"] = parseInt($(".number").eq(0).val());
 				data["cartList.colour"] = $(".color-selected-btn").eq(0).text();
 				data["cartList.size"] = $(".size-selected-btn").eq(0).text();
 				data["cartList.url"] = '${goods.url}';
 				data["cartList.name"] = '${goods.name}';
 				data["cartList.price"] = '${goods.price}';
 				data["cartList.discount"] = '${goods.discount}';
 				util.formAjax({
 					url: '<%=basePath%>cartList_save'
 					,data: data
 					,success: function(){
 						util.CAjax({
 							url: '<%=basePath%>cartList_count'
 							,isHints: !1
 							,success: function(data){
 								$(".cartListCount").eq(0).text(data.count)
 							}
 						})
 						
 					}
 				});
 			}
 			,addCol: function(){
 				var data = {};
 				data["collection.url"] = '${goods.url}';
 				data["collection.goodsId"] = '${goods.id}';
 				data["collection.price"] = '${goods.price}';
 				data["collection.name"] = '${goods.name}';
 				util.formAjax({
 					url: '<%=basePath%>collection_save'
 					,data: data
 				});
 			}
 			,colorSelect: function(e){
 				e.siblings().removeClass("color-selected-btn");
 				e.addClass("color-selected-btn");
 			}
 			,sizeSelect: function(e){
 				e.siblings().removeClass("size-selected-btn");
 				e.addClass("size-selected-btn");
 				//$(".size-selected-btn").eq.removeClass("size-selected-btn").addClass("size-btn");
 				//e.removeClass("size-btn").addClass("size-selected-btn");
 			}
 		});
 		layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 	})
	</script>
 </body>
</html>