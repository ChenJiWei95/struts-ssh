<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- start site nav -->
	<div style="width: 100%;  ">
		<div class="layui-container" style="height: 30px;">  
			<span class="layui-breadcrumb" style="float: left; ">
				<a href="" style="line-height: 40px; padding: 10px;"><s:text name="shop.common.welcome" /> ${username}</a>
			</span>
			<span class="layui-breadcrumb" lay-separator="|" style="float: right;">
			  <a href="<%=basePath%>fore_main_login" style="line-height: 40px; padding: 10px;">
			  	<s:text name="shop.common.login"/> 
			  </a>
			  <a href="<%=basePath%>fore_main_register" style="line-height: 40px; padding: 10px;">
			  	<s:text name="shop.common.register"/> 
			  </a>
			  <a href="<%=basePath%>ucenter_chtml_pcenter" style="line-height: 40px; padding: 10px;">
			  	<s:text name="shop.common.personalCenter"/></a> 
			  <a class="default-color" style="line-height: 40px; padding: 10px;"
			  	href="<%=basePath%>${URIPath}?request_locale=zh_CN">
			  	<s:text name="shop.common.chinese"/></a>
			  <a class="default-color" style="line-height: 40px; padding: 10px;"
			  	href="<%=basePath%>${URIPath}?request_locale=en_US">
			  	<s:text name="shop.common.english"/></a>
			</span>
		</div>
		<hr>
	</div>