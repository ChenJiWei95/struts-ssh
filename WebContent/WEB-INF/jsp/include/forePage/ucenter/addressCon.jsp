<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="right-con border" style="float: right; width: 868px; overflow: hidden; border-color: #e6e6e6bf; background: #fff;" >
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<!-- <ul class="layui-tab-title">
		    <li class="layui-this"><s:text name="shop.common.message" /></li> 
		</ul> -->
		<div class="layui-tab-content">
		   	<div class="layui-tab-item layui-show" style="width: 100%; height: 500px;">
		   		<div class="layui-card">
		   			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
		   				<button class="layui-btn C-btn-address c-button" data-type="add">添加</button>
						<button class="layui-btn C-btn-address c-button" data-type="edit">编辑</button>
						<button class="layui-btn C-btn-address c-button" data-type="del">删除</button>
		   			</div>
		   			<div class="layui-card-body"> 
		   				<table id="C-table-address" lay-filter="C-table-address" ></table>
		   			</div>
		   		</div>
			</div> 
		</div>
		
	</div>
</div>