<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// basePath：localhost:8080/项目名/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
	</head>
	<body style="padding: 30px;">
		<a class="layui-btn layui-btn-primary layui-btn-radius" href="<%=basePath%>testAjax_chtml_success">请求uri：testAjax_chtml_success跳转到success.jsp页面，实现方式通配符动态匹配</a> 
		<a class="layui-btn layui-btn-primary layui-btn-radius" href="<%=basePath%>testParame_chtml_saveorupdate">前往测试获取参数-ModelDriven</a> 
		<a class="layui-btn layui-btn-primary layui-btn-radius" href="<%=basePath%>testParame2_chtml_saveorupdate2">前往测试获取参数-对象驱动</a> 
		<a class="layui-btn layui-btn-primary layui-btn-radius" href="<%=basePath%>testParame3_chtml_saveorupdate3">前往测试获取参数-属性驱动</a> 
		<a class="layui-btn layui-btn-primary layui-btn-radius" href="<%=basePath%>testParame4_chtml_saveorupdate4">前往测试获取参数-request驱动</a> 
		<a class="layui-btn layui-btn-primary layui-btn-radius" href="<%=basePath%>main_main_index">前往主页</a> 
		<script src="<%=basePath%>resource/layui/layui.js"></script>
		<script>
		layui.use(['layer'], function(){
			
			var $ = layui.$
			,layer = layui.layer;
			layer.msg('正在使用layui！'); 
			
			$.ajax({ // 增
				type: "post"
				,url: "<%=basePath%>testAjax_save"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			,$.ajax({ // 删
				type: "post"
				,url: "<%=basePath%>testAjax_delete"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming2"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			,$.ajax({ // 查
				type: "post"
				,url: "<%=basePath%>testAjax_list"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming3"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			,$.ajax({ // 改
				type: "post"
				,url: "<%=basePath%>testAjax_update"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming4"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			<%-- ,$.ajax({ // 返回普通数据 直接访问action的execute方法
				type: "post"
				,url: "<%=basePath%>testAction"
				,data: {id: "1234567895461254", username: "xiaoming"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) --%>
		});
	</body>
</html>