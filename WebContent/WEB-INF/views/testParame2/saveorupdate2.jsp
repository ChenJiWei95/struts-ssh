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
			<input type="text" name="user.id" disabled autocomplete="off" class="layui-input layui-disabled">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">username</label>
		<div class="layui-input-inline">
			<input type="text" name="user.username" placeholder="请输入..." autocomplete="off" class="layui-input">
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
			url: '<%=basePath%>testParame2_save'
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
	,form.on("submit("+b+")", function(data){
		admin.cajax({
			method: 'update'
			,id: l
			,data: data.field  
		});
		return false;
	}) 
	
  })
  	/**
	 * 
	 * 将json数据转化为字符串  用于get请求方式时，处理url的向后台传递的参数的处理
	 * @author 威 
	 * 2017年7月3日 下午11:07:25 
	 *
	 */
	 
	function jsonToStr(data){
		if(data == null){
			return null;
		}
		var array = [];
		for(var key in data){
			var str = key+"="+data[key];
			array.push(str);  //入栈 将值压入数组结尾
		}
		return array.join("&");  //将数组以对定的字符分隔转化为字符串
	}
  	function ajax(jsondata){
		//设置默认值
		jsondata.type = jsondata.type || "get";
		// 
		if(jsondata.async == undefined) jsondata.async = true; // false时是同步，将会耗费资源同时锁死浏览器知道返回结果为止，不建议使用。true是异步
		var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
		// 判断返回结果
		switch(jsondata.dataType){
			case 'json' :
			case 'JSON' :
				jsondata.dataType = 'application/json;charset=UTF-8'; break;
			case 'script' :
			case 'SCRIPT' : // 返回纯文本 JavaScript 代码
				jsondata.dataType = '*/*'; 
				break;
			case 'text' :
			case 'TEXT' : 	// 返回纯文本字符串
				jsondata.dataType = '*/*';
				break;
			case 'html' :
			case 'HTML' : 	// 返回纯文本 HTML 信息
				jsondata.dataType = '*/*';
				break;
			case 'xml' :
			case 'XML' : 	// 返回 XML 文档
				jsondata.dataType = '*/*';
				break;
		}
		//响应状态
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4) {
				console.log("（此处是ajax源码）响应状态码："+xhr.status);
				if(xhr.status == 0) {
					alert("无法与服务器建立连接");
					jsondata.error("无法与服务器建立连接");
				} else if(xhr.status == 200) {
					jsondata.success && typeof jsondata.success == "function" && jsondata.success(xhr.responseText); 
				} else if(xhr.status == 500)
					jsondata.error("服务器异常");
			}
			
		}
		// 分别判断处理post，get请求
		if(jsondata.type.toLowerCase() == "post"){
			xhr.open(jsondata.type, jsondata.url, jsondata.async);
			
			// Request Headers参数
			// application/x-www-form-urlencoded, application/json;charset=UTF-8
			// xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			// application/json, text/javascript, */*, q=0.01
			xhr.setRequestHeader("Accept", jsondata.dataType);
			xhr.send(jsondata.data);
		}else{
			var url = jsondata.url+"?"+jsonToStr(jsondata.data);
			xhr.open(jsondata.type, url, jsondata.async);
			xhr.send();
		}
	}
  </script>
</body>
</html>
