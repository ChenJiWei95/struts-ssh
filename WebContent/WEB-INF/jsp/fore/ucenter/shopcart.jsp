<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.addShopCart"/></title> 
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
					<input type="checkbox" name="all" lay-skin="primary" title="<s:text name="shop.common.addCheck"/>" shop-click="selectedAll">
				</div>
			</div>
			<div class="layui-col-md4" ><s:text name="shop.common.goods"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.subtotal"/></div>
			<div class="layui-col-md1"><s:text name="shop.common.operation"/></div>
		</div>
		<c:forEach begin="0" items="${cartlist}" step="1" var="CartList" varStatus="varsta">
		<div class="layui-row cartlist-etc pointer" style="overflow: hidden;">
			<div class="layui-col-md1 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-skin="primary" title="" shop-click="selected" data-id="${CartList.id}" class="select-box">
				</div>
			</div>
			<div class="layui-col-md4" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${CartList.goodsId}">
				<%-- <div style="background-image: url(<%=basePath%>resource/img/good2.png); width: 100px; height: 100px; float: left;"></div>
				 --%><img src="<%=basePath%>${CartList.url}" width="100px" height= "100px"/>
				<span>${CartList.name}</span>
			</div>
			<div class="layui-col-md2"><span><font style="color: red; font-size: 16px;">￥${CartList.price*CartList.discount}</font><del>￥${CartList.price}</del></span></div>
			<div class="layui-col-md2">
				<div style="float: left; margin-left: 20px;">
					<span class="subtraction border" shop-click="subtraction">-</span>
					<input type="text" class="border number" value="${CartList.count}"/>
					<span class="addition border" shop-click="addition">+</span>
				</div>
			</div>
			<div class="layui-col-md2">￥<span class="mini-total-amount">${CartList.price*CartList.count*CartList.discount}</span></div>
			<div class="layui-col-md1"><span class="delete" shop-click="goodsDelete" data-id="${CartList.id}"><s:text name="shop.common.delete"/></span></div>
		</div>
		</c:forEach> 
	</div>
	<div class="layui-container settlement-bar" style="padding-left: 0; padding-right: 0; height: 80px; background: #99999930;">
		<div class="layui-row">
			<div class="layui-col-md2 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-skin="primary" title="<s:text name="shop.common.selected"/>"><span class="select-number">0</span>
				</div>
			</div>
			<div class="layui-col-md2"><font class="all-delete"><s:text name="shop.common.batchdelete"/></font></div>
			<!-- <div class="layui-col-md1"></div> -->
			<div class="layui-col-md1">&nbsp;</div>
			<div class="layui-col-md2">&nbsp;</div>
			<div class="layui-col-md3">	 
				<s:text name="shop.common.payment"/><font class="total-amount" style="color: red; font-size: 24px;"><s:text name="shop.common.currency"/>1114.00</font><del><s:text name="shop.common.currency"/>1314.00</del>
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
 	layui.use(['layer', 'element', 'form', 'util'], function(){
 		var element = layui.element
 		,$ = layui.$
 		,form = layui.form
 		,layer = layui.layer
 		,util = layui.util;
 		util.layHref(); // 链接跳转
 		util.addSubCtrlbtn(function(data){  // 加减器
 			layer.msg(data.type + " - " + data.value + " - " + data.e)
 		});
 		util.shopClick({
 			goodsDelete: function(e){
 				var data = {};
 				data["cartList.id"] = e.data("id");
 				util.formAjax({
 					url: '<%=basePath%>cartList_delete'
 					,data: data
 				});
 			}
 			,selected: function(e){
 				if(e.checked){ 
 					<%-- var data = {};
 					data["cartList.id"] = e.data("id");
 					util.formAjax({
 	 					url: '<%=basePath%>cartList_update'
 	 					,data: data
 	 				}); --%>
 	 				$(".select-number").eq(0).text($(".select-number").eq(0).text()+1);
 				} else {
 					<%-- util.formAjax({
 	 					url: '<%=basePath%>cartList_delete'
 	 					,data: {cartList.id: e.data("id")}
 	 				}); --%>
 	 				$(".select-number").eq(0).text($(".select-number").eq(0).text()-1);
 				}
 				// 触发金额计算
 			}
 			,selectedAll: function(e){
 				if(e.checked) $(".select-box").forEach(item => item.removeAttr("checked"))
 				else {
 					$(".select-box").forEach(item => item.prop("checked","checked"))
 					$(".total-amount").eq(0).text('<s:text name="shop.common.currency"/>0.00')
 				}
 				// 触发金额计算
 			}
 		});
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		$(".service").eq(0).addClass("var-color");
 		// 计算总金额
 		function calAmount(){
 			// 1 加减数量    2 勾选     
 			var sum = 0; 
 			$(".cartlist-etc").forEach(item=>{
 				// 找到勾选计算总计
 				if(item.find(".select-box").checked){
 					sum += item.find(".mini-total-amount").text();
 				}
 			});
 		}
 		// 总数据 根据id选择数据
 	})
	</script>
 </body>
</html>