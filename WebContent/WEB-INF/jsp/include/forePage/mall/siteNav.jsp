<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- start site nav -->
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
	</div>