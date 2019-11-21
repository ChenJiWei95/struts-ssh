<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.address"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 </head>
 <body>
 	<%@ include file="../../include/forePage/mall/languageBt.jsp" %>
	
	<%@ include file="../../include/forePage/mall/siteNav.jsp" %>
	<%@ include file="../../include/forePage/mall/search.jsp" %>
	<%@ include file="../../include/forePage/mall/mallNav.jsp" %>
	
	<div class="cnt">
		<!-- nav text -->
		<div class="layui-container" style="height: 30px;">
			<span class="layui-breadcrumb" lay-separator=">" style="line-height: 30px;">
			  <a href="<%=basePath%>fore_main_index"><s:text name="shop.common.nav1"/></a>
			  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.address"/></a>
			</span>
		</div>
		<div class="layui-container" style="margin-top: 20px; ">
			<%@ include file="../../include/forePage/ucenter/centerNav.jsp" %>
			<!-- <div class="right-con " style="float: right; width: 905px;">
				
			</div>  -->
			<%@ include file="../../include/forePage/ucenter/addressCon.jsp" %>
		</div>
	</div>
	
	
	<!-- start bom --> 
	<%@ include file="../../include/forePage/mall/bom.jsp" %>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
	var table;
 	layui.use(['layer', 'element', 'table', 'form', 'util'], function(){
 		table = layui.table;
 		var element = layui.element
 		,form = layui.form 
 		,util = layui.util
 		,$ = layui.$ 
 		,a = "C-iframe-address-add" 
 		,b = "C-iframe-address-update"
 		,f = "iframe"
 		,t = 'C-from-address'
 		,l = 'C-table-address'
 		layui.layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 		layui.$(".address").eq(0).addClass("var-color");
 		table.render({//角色的加载
 	        elem: "#"+l,
 	        url: '<%=basePath%>address_list',
 	        cols: [[
 	        	{type:"checkbox"}
 	        	,{field:"province", title:"省份"}
 	        	,{field:'city', title:'城市'}
 				,{field:'area', title:'区'}
 	        	,{field:"street", title:"街道"}
 	        	,{field:"name", title:"姓名"}
 	        	,{field:"phone", title:"手机号码"}
 	        ]],
 	        text: "对不起，加载出现异常！"
 	    }); 
 		var active = {
 				del: function(){
 					var arr = []; 
 					var checkStatus = table.checkStatus(l)
 					,checkData = checkStatus.data; //得到选中的数据
 					
 					checkData.length == 0 ? layer.msg("请选中") :
 					layer.confirm('确定删除吗？', function(data) {
 						var url, contentType;
 						if(checkData.length == 1){
 							url = 'address_delete';
 							var data = {};
 							data['address.id'] = checkData[0].id;
 							arr = data;
 						}else {
 							url = 'address_delBatch'
 							,contentType = 'application/json';
 							for(var index in checkData){
 	 						  	var data = {};
 	 						  	data["id"] = checkData[index].id;
 	 						  	arr[index] = data;
 	 					  	}
 							arr = JSON.stringify(arr);
 						}
 					  	layer.close(layer.index);
 						util.formAjax({
 							url: url
 							,data: arr 
 							,contentType: contentType
 							,success: function(){
 								table.reload(l);
 							}
 						}); 	  
 					});
 					
 				},
 				add: function(){
 					layer.open({
 						type: 2
 						,title: '添加地址'
 						,content: 'ucenter_chtml_addressfrom'
 						,area: ['420px', '480px']
 						,btn: ['确定', '取消']
 						,yes: function(index, layero){
 							layero.find(f).contents().find("#"+a).click();
 						}
 						,success: function(e, index) {
 							var iframe = e.find(f).contents().find("#"+t);
 						}
 					});
 				}
 				,edit: function(){
 					var checkStatus = table.checkStatus(l)
 					,data = checkStatus.data; //得到选中的数据
 					//console.log(data);
 					data.length == 0 ? layer.msg("请选中一项") : data.length > 1 ? layer.msg("只能选中一项") :
 					layer.open({
 		                type: 2,
 		                title: "编辑地址",
 		                content: "ucenter_chtml_addressfrom?id="+data[0].id,
 		                area: ["420px", "480px"],
 		                btn: ["确定", "取消"],
 		                yes: function(index, layero) {
 		                    layero.find(f).contents().find("#"+b).click();
 		                },
 		                success: function(e, index) {
 							//这是渲染完之后调用 可以用于初始化
 							var form = e.find(f).contents().find("#"+t);
 							form.find('input[name="address.id"]').val(data[0].id)
 							,form.find('input[name="address.province"]').val(data[0].province)
 							,form.find('input[name="address.city"]').val(data[0].city)
 							,form.find('input[name="address.area"]').val(data[0].area)
 							,form.find('input[name="address.street"]').val(data[0].street)
 							,form.find('input[name="address.name"]').val(data[0].name)
 							,form.find('input[name="address.phone"]').val(data[0].phone) 
 						}
 		            })
 				}
 		    }; 
 		$('.layui-btn.C-btn-address').on('click', function(){
 			var type = $(this).data('type');
 			active[type] ? active[type].call(this) : '';
 	    });
 	})
	</script>
 </body>
</html>