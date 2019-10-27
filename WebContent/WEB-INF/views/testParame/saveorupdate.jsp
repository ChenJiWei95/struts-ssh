<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>测试参数获取</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
</head>
<body style="padding-right:20px">
  <h1 style="padding-top: 10px; padding-left: 30px;">${text}</h1>
  <div class="layui-form" lay-filter="c-form-data" id="c-form-data" style="padding: 20px 30px 0 0;">
    <div class="layui-hide">
		<label class="layui-form-label">ID</label>
		<div class="layui-input-inline">
			<input type="text" name="id" disabled autocomplete="off" class="layui-input layui-disabled">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">username</label>
		<div class="layui-input-inline">
			<input type="text" name="username" placeholder="请输入..." autocomplete="off" class="layui-input">
		</div>
		<button lay-submit lay-filter="c-form-add" class="layui-btn layui-btn-primary" id="c-form-add">添加</button>
	</div> 
  </div>
  <script src="<%=basePath%>resource/layui/layui.js"></script>  
  <script>
  layui.use(['form', 'layer'], function(){
    var $ = layui.$
    ,layer = layui.layer
    ,form = layui.form
	,a = "c-form-add"
	,b = 'c-form-update'
	form.on("submit("+a+")", function(data){
		console.log(data.field);
		$.ajax({
			url: '<%=basePath%>testParame_save'
			,type: 'post'
			,dataType: 'json'
			,data: data.field
			,success: function(msg){
				console.log(msg);
			}
			,error: function(msg){
				console.log(msg);
			}
		});
		return false;
	})
	 
  })
  </script>
</body>
</html>
