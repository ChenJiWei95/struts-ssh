<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- start site nav -->
	<div style="width: 100%;  ">
		<div class="layui-container" style="height: 30px;">  
			<span class="layui-breadcrumb" style="float: left; ">
				<a href="" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.welcome" />${user.username}</a>
			</span>
			<span class="layui-breadcrumb" lay-separator="|" style="float: right;">
			  <a href="<%=basePath%>fore_main_login" style="line-height: 40px; padding: 10px;">
			  	<s:text name="shop.common.login"/> 
			  </a>
			  <a href="<%=basePath%>fore_main_register" style="line-height: 40px; padding: 10px;">
			  	<s:text name="shop.common.register"/> 
			  </a>
			  <a href="<%=basePath%>ucenter_chtml_myorder" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.orders"/></a>
			  <a href="<%=basePath%>ucenter_chtml_pcenter" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.personalCenter"/></a> 
			</span>
		</div>
		<hr>
	</div>