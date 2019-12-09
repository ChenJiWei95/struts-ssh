<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../../include/forePage/common.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	<input type="text" class="layui-hide" name="" class="total-amount-input"/>
	<input type="text" class="layui-hide" name="" class="orig-total-amount-input"/>
	<div class="layui-container cartlist-cnt" style="margin-top: 40px;">
		<div class="layui-row cartlist">
			<div class="layui-col-md1 check">
				<div class="layui-form-item">
					<input type="checkbox" name="all" lay-filter="all" lay-skin="primary" title="<s:text name="shop.common.addCheck"/>" shop-click="selectedAll">
				</div>
			</div>
			<div class="layui-col-md3" ><s:text name="shop.common.goods"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
			<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
			<div class="layui-col-md1"><s:text name="shop.common.colour"/></div>
			<div class="layui-col-md1"><s:text name="shop.common.size"/></div>
			<div class="layui-col-md1"><s:text name="shop.common.subtotal"/></div>
			<div class="layui-col-md1"><s:text name="shop.common.operation"/></div>
		</div>
		<c:forEach begin="0" items="${cartlist}" step="1" var="CartList" varStatus="varsta">
		<div class="layui-row cartlist-etc pointer" style="overflow: hidden;" data-id="${CartList.id}">
			<div class="layui-col-md1 check check-div">
				<div class="layui-form-item">
					<input type="checkbox" name="${CartList.id}" lay-skin="primary" title="" lay-filter="selected" class="select-box">
				</div>
			</div>
			<div class="layui-col-md3" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${CartList.id}">
				<%-- <div style="background-image: url(<%=basePath%>resource/img/good2.png); width: 100px; height: 100px; float: left;"></div>
				 --%><img src="<%=basePath%>${CartList.url}" width="100px" height= "100px"/>
				<span>${CartList.name}</span>
			</div>
			<div class="layui-col-md2"><span><font class="price" style="color: red; font-size: 16px;">￥<fmt:formatNumber type="number" value="${CartList.price*CartList.discount}" maxFractionDigits="2"/></font><del class="ori-price">￥${CartList.price}</del></span></div>
			<div class="layui-col-md2">
				<div style="float: left; margin-left: 20px;">
					<span class="subtraction border" shop-click="subtraction">-</span>
					<input type="text" class="border number" value="${CartList.count}"/>
					<span class="addition border" shop-click="addition">+</span>
				</div>
			</div>
			<div class="layui-col-md1 m-total-div"><span class="color">${CartList.colour}</span></div>
			<div class="layui-col-md1 m-total-div"><span class="size">${CartList.size}</span></div>
			<div class="layui-col-md1 m-total-div">￥<span class="mini-total-amount"><fmt:formatNumber type="number" value="${CartList.price*CartList.count*CartList.discount}" maxFractionDigits="2"/></span></div>
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
				<s:text name="shop.common.payment"/><font class="total-amount" style="color: red; font-size: 24px;">0.00</font><del class="orig-total-amount"><s:text name="shop.common.currency"/>0.00</del>
			</div>
			<div class="layui-col-md2">
				<div style="background: #a77f2a69; font-size: 18px; width: 100%; height: 100%; color: #fff; font-weight: 600;" class="layui-btn" shop-click="settlement"><s:text name="shop.common.settlement"/></div>
				<button lay-submit lay-filter="settlement-submit" class="layui-hide" id="settlement-submit"></button>
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
 		/**/ 
 		util.addSubCtrlbtn(function(data){  // 加减器
 			var current = data.e.parents(".cartlist-etc").eq(0);
 			var requestData = {query: {id: current.data("id")}, set: {count: data.value}}; 
 			util.CAjax({
				url: '<%=basePath%>cartList_update'
				,data: JSON.stringify({query: {id: current.data("id")}, set: {count: parseInt(data.value)}})
				,success: function(){
					current.find(".mini-total-amount").eq(0).text((data.value * current.find(".price").eq(0).text().substring(1)).toFixed(2));
		 			calAmount();
				}
			});	
 			
 		}); 
 		form.on('checkbox(all)', function(obj){
 	 		$(".select-box").prop("checked", obj.elem.checked ? true : false);
 	 		layui.form.render();
 			calAmount();
 		})
 		,form.on('checkbox(selected)', function(obj){
 			calAmount();
 		})
 		,form.on("submit(settlement-submit)", function(data){
 			// 结算
 			util.formAjax({
					url: '<%=basePath%>order_save'
					,data: data.field
					,success: function(data){
						 // 跳转下单成功页
						 self.location = "<%=basePath%>mall_chtml_payment?id="+data
					}
			});
 			return !1;
 		});
 		// 点击事件集中再此
 		util.shopClick({
 			goodsDelete: function(e){
 				var data = {};
 				data["cartList.id"] = e.data("id");
 				util.formAjax({
 					url: '<%=basePath%>cartList_delete'
 					,data: data
 					,success: function(){
 						e.parents(".cartlist-etc").eq(0).remove();
 						calAmount();
 					}
 				});
 			} 
 			,settlement: function(){
 				console.log("提交结算");
 				$("#settlement-submit").click();
 			}
 		});
 		
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		$(".service").eq(0).addClass("var-color");
 		// 计算总金额
 		
 		function calAmount(){
 			var sum = 0;
 	 		var oriSum = 0;
 			// 1 加减数量    2 勾选 
 			$(".cartlist-etc").each(function(index, val){
 				var item = $(val);
 				// 找到勾选计算总计
 				//console.log(item.find(".layui-form-checked").eq(0).prop("outerHTML"));
 				if(item.find(".layui-form-checked").eq(0).prop("outerHTML")){
 					sum += parseFloat(item.find(".mini-total-amount").eq(0).text());
 					oriSum += (parseFloat(item.find(".ori-price").eq(0).text().substring(1)) * parseFloat(item.find(".number").eq(0).val()));
 				}
 			});
 			$(".total-amount").eq(0).text('￥'+sum.toFixed(2));
 			$(".orig-total-amount").eq(0).text('￥'+oriSum.toFixed(2));
 			$(".total-amount-input").eq(0).val(sum.toFixed(2));
 			$(".orig-total-amount-input").eq(0).val(oriSum.toFixed(2));
 		}
 		 
 		// 总数据 根据id选择数据
 		/**/ $(".select-box").eq(0).prop("checked", true);
 	 	layui.form.render(); 	// 渲染
 	 	calAmount(); 			// 初始计算
 	})
	</script>
 </body>
</html>