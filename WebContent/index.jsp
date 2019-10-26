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
	<body>
		<h1>你好</h1>
		<a href="<%=basePath%>testAjax_chtml_success">点击跳转</a> 
		<div class="layui-form" lay-filter="c-form-input" id="c-form-input" style="padding: 20px 30px 0 0;">
			<div class="layui-hide">
				<label class="layui-form-label">ID</label>
				<div class="layui-input-inline">
					<input type="text" name="id" disabled autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-inline">
					<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
		      	<button lay-submit class="layui-button" lay-filter="c-form-add" id="c-form-add">提交</button> 
			</div>
		</div>  
		<script src="<%=basePath%>resource/layui/layui.js"></script>
		<script>
		layui.use(['form', 'layer'], function(){
			
			var form = layui.form
			,layer = layui.layer
			,b = 'c-form-add'
			,f = 'c-form-input';
			layer.msg('正在使用layui！'); 
			form.on("submit("+b+")", function(data){
				//执行 Ajax 后重载 
				layer.msg(data)
				/* admin.cajax({
					method: 'add'
					,id: l
					,data: data.field  
				});	 */	  
				return !1;
			});
			
			ajax({ // 增
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
			,ajax({ // 删
				type: "post"
				,url: "<%=basePath%>testAjax_delete"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			,ajax({ // 查
				type: "post"
				,url: "<%=basePath%>testAjax_list"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			,ajax({ // 改
				type: "post"
				,url: "<%=basePath%>testAjax_update"
				,dataType: 'json'
				,data: {id: "1234567895461254", username: "xiaoming"}
				,success: function(data){
					console.log(data)
				}
				,error: function(data){
					console.log(data);
				}
			}) 
			<%-- ,ajax({ // 返回普通数据 直接访问action的execute方法
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
		
		/**
		 * 
		 * ajax前后台交互封装方法 -- 使用方法类似于Jquery 中的ajax
		 * 默认值解析 ：
		 * 	   type 请求类型 默认get
		 *     async 是否异步交互 默认true
		 * @author 威 
		 * 2017年7月3日 下午11:07:25 
		 *
		 */
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