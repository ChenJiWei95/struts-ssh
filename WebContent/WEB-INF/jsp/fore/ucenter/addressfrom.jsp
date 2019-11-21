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
  <title>角色管理 操作</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
</head>
<body style="padding-right=20px">
  <div class="layui-form" lay-filter="C-from-address" id="C-from-address" style="padding: 20px 30px 0 0;">
	<div class="layui-hide">
		<label class="layui-form-label">ID</label>
		<div class="layui-input-block">
			<input type="text" name="address.id" lay-verify="id" autocomplete="off" class="layui-input">
		</div>
	</div> 
	<div class="layui-form-item">
      <label class="layui-form-label">省份</label>
      <div class="layui-input-inline">
        <input type="text" name="address.province" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
	<div class="layui-form-item">
      <label class="layui-form-label">城市</label>
      <div class="layui-input-inline">
        <input type="text" name="address.city" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
	<div class="layui-form-item">
      <label class="layui-form-label">区</label>
      <div class="layui-input-inline">
        <input type="text" name="address.area" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
	<div class="layui-form-item">
      <label class="layui-form-label">街道</label>
      <div class="layui-input-inline">
        <input type="text" name="address.street" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
	<div class="layui-form-item">
      <label class="layui-form-label">姓名</label>
      <div class="layui-input-inline">
        <input type="text" name="address.name" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
	<div class="layui-form-item">
      <label class="layui-form-label">电话</label>
      <div class="layui-input-inline">
        <input type="text" name="address.phone" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
    <div class="layui-form-item layui-hide">
	  <button class="layui-btn" lay-submit lay-filter="C-iframe-address-add" id="C-iframe-address-add">添加</button>
	  <button class="layui-btn" lay-submit lay-filter="C-iframe-address-update" id="C-iframe-address-update">修改</button>
    </div>
  </div>

  <script src="<%=basePath%>resource/layui/layui.js"></script>
  <script>
  layui.use(['layer', 'element', 'form', 'util', 'table'], function(){
	var element = layui.element
    ,$ = layui.$ 
    ,form = layui.form 
    ,util = layui.util 
    ,table = layui.table 
	,a = "C-iframe-address-add" 
	,b = "C-iframe-address-update"
	,l = "C-table-address";
	form.on("submit("+a+")", function(data){
		util.iframeAjax({
			url: '<%=basePath%>address_save'
			,data: data.field
			,id: l
		});
		return false;
	})
	,form.on("submit("+b+")", function(data){
		util.iframeAjax({
			url: '<%=basePath%>address_update'
			,data: data.field
			,id: l
		});
		return false;
	});
	function Message(data, fn){
		data.code == '0' && 'function' == typeof fn.success && fn.success(data.data, data.msg);
		data.code == '2' && 'function' == typeof fn.error && fn.error(data.data, data.msg);
	}  
  })
  </script>
</body>
</html>