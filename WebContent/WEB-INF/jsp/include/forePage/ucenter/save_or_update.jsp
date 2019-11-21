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
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
</head>
<body style="padding-right=20px">
  <div class="layui-form" lay-filter="layuiadmin-form-role" id="layuiadmin-form-role" style="padding: 20px 30px 0 0;">
	<div class="layui-hide">
		<label class="layui-form-label">ID</label>
		<div class="layui-input-block">
			<input type="text" name="id" lay-verify="id" autocomplete="off" class="layui-input">
		</div>
	</div> 
	<div class="layui-form-item">
      <label class="layui-form-label">名称</label>
      <div class="layui-input-inline">
        <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div> 
    <div class="layui-form-item">
      <label class="layui-form-label">创建时间</label>
      <div class="layui-input-inline">
        <input type="text" name="create_time" disabled autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
	<div class="layui-form-item">
      <label class="layui-form-label">修改时间</label>
      <div class="layui-input-inline">
        <input type="text" name="update_time" disabled autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">启用状态</label>
      <div class="layui-input-inline">
        <select name="state">
        	<option value="01">禁用</option>
			<option value="00">启用</option>
        </select>
      </div>
    </div>
	<div class="layui-form-item">
      <label class="layui-form-label">权限范围</label>
      <div class="layui-input-block"> 
        <c:choose>
        	<c:when test="${type}">
        		<!-- 循环输出所有未授权的模块 -->
	        	<c:forEach begin="0" items="${apps}" step="1" var="Menu" varStatus="varsta">
				   	 <input type="checkbox" name="${Menu.id}|${Menu.name}" lay-skin="primary" title="${Menu.name}">
				</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<!-- 循环判断已授权和未授权的模块 -->
        		<c:forEach begin="0" items="${apps}" step="1" var="Menu" varStatus="varsta"> 
        			<c:set var="iscontain" value="false" />
        			<c:forEach begin="0" items="${roleItems}" step="1" var="Role" varStatus="varsta">
        				<c:if test="${Menu.id eq Role.app_id}">
        					<c:set var="iscontain" value="true"/>
        				</c:if> 
        			</c:forEach>  
        			<c:if test="${iscontain}"><input type="checkbox" checked  name="${Menu.id}|${Menu.name}" lay-skin="primary" title="${Menu.name}"/></c:if> 
        			<c:if test="${!iscontain}"><input type="checkbox" name="${Menu.id}|${Menu.name}" lay-skin="primary" title="${Menu.name}"/></c:if> 
				</c:forEach>
				
        	</c:otherwise>
        </c:choose>  
      </div>
    </div>
	<div class="layui-form-item">
      <label class="layui-form-label">描述信息</label>
      <div class="layui-input-block">
        <textarea class="layui-textarea" name="desc" placeholder="请输入描述信息"></textarea>
      </div>
    </div>
    <div class="layui-form-item layui-hide">
	  <button class="layui-btn" lay-submit lay-filter="LAY-user-role-add" id="LAY-user-role-add">添加</button>
	  <button class="layui-btn" lay-submit lay-filter="LAY-user-role-update" id="LAY-user-role-update">修改</button>
    </div>
  </div>

  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径	
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'admin'], function(){
    var $ = layui.$ 
    ,form = layui.form 
	,index = layui.index 
	,admin = layui.admin 
	,a = "LAY-user-role-add" 
	,b = "LAY-user-role-update"
	,l = "LAY-user-back-role";
	form.on("submit("+a+")", function(data){
		admin.cajax({
			method: 'add'
			,id: l
			,data: data.field   
		});	  		  
		
		return false;
	})
	,form.on("submit("+b+")", function(data){
		admin.cajax({
			method: 'update'
			,id: l
			,data: data.field   
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