<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   	 				<a href="<%=basePath%>ucenter_chtml_shopcart"><i class="layui-icon layui-icon-cart" style="font-size: 32px; cursor: pointer; color: #a77f2a69; float: right; margin-top: 14px;">
   	 					<c:if test="${cartListCount>0}">
   	 					<span class="layui-badge cartListCount" style="position: absolute; margin: -35px 20px; border-radius: 10px;">${cartListCount}</span>
   	 					</c:if>
   	 				</i></a>
   	 			</div>
			</div>
		</div>