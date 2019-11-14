<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ include file="../../include/forePage/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><s:text name="shop.common.goodsDetails"/></title> 
  <link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>resource/style/mall.css" media="all">
 </head>
 <body>
 	<%@ include file="../../include/forePage/mall/languageBt.jsp" %>
	
	<%@ include file="../../include/forePage/mall/siteNav.jsp" %>
	<%@ include file="../../include/forePage/mall/search.jsp" %>
	<%@ include file="../../include/forePage/mall/mallNav.jsp" %>
	<!-- nav text -->
	<div class="layui-container" style="">
		<span class="layui-breadcrumb" lay-separator=">" style="line-height: 55px;">
		  <a href="<%=basePath%>fore_main_index"><s:text name="shop.common.nav1"/></a>
		  <a href="<%=basePath%>mall_chtml_furnishing"><s:text name="shop.common.nav2"/></a>
		  <a href="javascript:void(0)" class="var-color"><s:text name="shop.common.goodsDetails"/></a>
		</span>
	</div>
	 
	<div class="layui-container" style="overflow: hidden;"> 
		<div style="float: left; width: 45%; height: 398px;" class="">
			<div style="float: left; width: 80%; height: 100%; background-image:url(<%=basePath%>${goods.url}); background-size: cover;" ></div>
			<div style="float: left; width: 20%; height: 100%;">
				<div style="width: 85%; float: right; height: 100%;"> 
					<div style="width: 96px; height: 90px; background-image:url(<%=basePath%>${goods.url}); background-size: cover;"></div>
					<div style="width: 96px; height: 90px; margin-top: 13px; background-image:url(<%=basePath%>${goods.url}); background-size: cover;"></div>
					<div style="width: 96px; height: 90px; margin-top: 13px; background-image:url(<%=basePath%>${goods.url}); background-size: cover;"></div>
					<div style="width: 96px; height: 90px; margin-top: 13px; background-image:url(<%=basePath%>${goods.url}); background-size: cover;"></div>
				</div>
			</div> 
		</div>
		<div style="float: left; width: 55%; height: 400px;">
			<div style="padding-left: 45px;">
				<div style="width: 100%; height: 46px; ">

					<span class="layui-badge" style="background: #a77f2a69; font-size: 12px; padding: 2px 6px;"><s:text name="shop.common.hot" /></span>
					<label style="font-size: 16px; font-weight: bold;">${goods.name}</label>
					<label style="float: right; " class="default-color"><s:text name="shop.common.favorableRate" /> 95%</label>
				</div>
				<div style="width: 100%; height: 60px; background: #fbf6f2; line-height: 60px;">
					<div style="padding-left: 20px;">
					<span style="color: red;">￥</span><span style="color: red; font-size: 30px;">${goods.price}</span>
					<s:text name="shop.common.originalPrice" />
					<span class="default-color">￥</span><span style="font-size: 20px;"><del class="default-color">${goods.price}</del></span>
					</div>
				</div>
				<div style="width: 100%; line-height: 60px;">
					<div style="padding-left: 10px;">
					<span class="default-color"><s:text name="shop.common.colour" /></span>
					<span class="border colorBtn">白色</span>
					<span class="border colorBtn">雅灰</span>
					<span class="border colorBtn">银灰</span>
					<span class="border colorBtn">墨绿</span> 
					</div>
				</div>
				<div style="width: 100%; line-height: 60px;">
					<div style="padding-left: 10px;">
					<span class="default-color"><s:text name="shop.common.size" /></span>
					<span class="border colorBtn">S</span>
					<span class="border colorBtn">M</span>
					<span class="border colorBtn">L</span>
					<span class="border colorBtn">XL</span> 
					</div>
				</div>	
				<div style="width: 100%; line-height: 60px; overflow: hidden;">
					<div style="padding-left: 10px;">
						<div style="float: left;"><span class="default-color"><s:text name="shop.common.count" /></span></div>
						<div style="float: left; margin-left: 20px;">
							<span class="subtraction border" shop-click="subtraction">-</span>
							<input type="text" class="border number" value="1"/>
							<span class="addition border" shop-click="addition">+</span>
						</div>
					</div>
				</div>
				<div style="width: 100%; line-height: 80px;">
					<div style="padding-left: 10px;">
						<span class="buyNow border" shop-click="buy"><s:text name="shop.common.buy" /></span>
						<span class="addCarList border" shop-click="addCart"><i class="layui-icon layui-icon-cart" style="color: #fff;font-size: 20px; margin-right: 4px;"></i><s:text name="shop.common.addShopCart" /></span>
						<span class="addColect border" shop-click="addCol"><s:text name="shop.common.addCollection" /><i class="layui-icon layui-icon-rate" style="color: #a77f2a8c;font-size: 20px; margin-right: 4px;"></i></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="layui-container"  style="margin-top: 20px;">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this"><s:text name="shop.common.details" /></li>
		    <li><s:text name="shop.common.comment" /></li>
		  </ul>
		  <div class="layui-tab-content" style="height: 100px;">
		    <!-- 	详情 -->
		    <div class="layui-tab-item layui-show" style="width: 100%; height: 500px; background-size: cover; background-image:url(<%=basePath%>resource/img/xihu.jpg);">
		    </div>
		    <!-- 	评论 -->
		    <div class="layui-tab-item">
				<div class="layui-fluid layadmin-message-fluid">
	  			 <div class="layui-row">
				 <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
			       <div class="media-body">
			          <a href="javascript:;" class="media-left" style="float: left;">
			             <img src="<%=basePath%>resource/img/good4.png" height="46px" width="46px">
			          </a>
			          <div class="pad-btm">
			            <p class="fontColor"><a href="javascript:;">胡歌</a></p>
			            <p class="min-font">
			              <span class="layui-breadcrumb" lay-separator="-">
			                <a href="javascript:;" class="layui-icon layui-icon-cellphone"></a>
			                <a href="javascript:;">从移动</a>
			                <a href="javascript:;">11分钟前</a>
			              </span>
			            </p>         
			         </div>
			          <p class="message-text">历经打磨，@索尼中国 再献新作品—OLED电视A8F完美诞生。很开心一起参加了A8F的“首映礼”！[鼓掌]正如我们演员对舞台的热爱，索尼对科技与艺术的追求才创造出了让人惊喜的作品。作为A1兄弟款，A8F沿袭了黑科技“屏幕发声技术”和高清画质，色彩的出众表现和高端音质，让人在体验的时候如同身临其境。A8F，这次的“视帝”要颁发给你！  索尼官网预售： O网页链接 索尼旗舰店预售：</p>
			       </div>
			       <div class="media-body">
			          <a href="javascript:;" class="media-left" style="float: left;">
			             <img src="<%=basePath%>resource/img/good2.png" height="46px" width="46px">
			          </a>
			          <div class="pad-btm">
			            <p class="fontColor"><a href="javascript:;">胡歌</a></p>
			            <p class="min-font">
			              <span class="layui-breadcrumb" lay-separator="-">
			                <a href="javascript:;" class="layui-icon layui-icon-cellphone"></a>
			                <a href="javascript:;">从移动</a>
			                <a href="javascript:;">11分钟前</a>
			              </span>
			            </p>         
			         </div>
			          <p class="message-text">历经打磨，@索尼中国 再献新作品—OLED电视A8F完美诞生。很开心一起参加了A8F的“首映礼”！[鼓掌]正如我们演员对舞台的热爱，索尼对科技与艺术的追求才创造出了让人惊喜的作品。作为A1兄弟款，A8F沿袭了黑科技“屏幕发声技术”和高清画质，色彩的出众表现和高端音质，让人在体验的时候如同身临其境。A8F，这次的“视帝”要颁发给你！  索尼官网预售： O网页链接 索尼旗舰店预售：</p>
			       </div>
			       <div class="layui-row message-content-btn">
			          <a href="javascript:;" class="layui-btn more-btn">更多</a>
			       </div>
			     </div>
			    </div>
			   </div>
			</div>
		  </div>
		</div>
	</div>
	
	<!-- start bom --> 
	<%-- <%@ include file="../../include/forePage/mall/bom.jsp" %> --%>
	<script src="<%=basePath%>resource/layui/layui.js"></script>
	<script>
 	layui.use(['layer', 'element', 'util'], function(){
 		var element = layui.element
 		,util = layui.util
 		,$ = layui.$
 		,layer = layui.layer;
 		util.addSubCtrlbtn(function(data){ // 加减器
 			layer.msg(data.type); 
 		});
 		util.shopClick({
 			buy: function(){
 				util.formAjax({
 					url: '<%=basePath%>'
 					,data: {id: '${goods.id}'}
 				});
 			}
 			,addCart: function(){
 				util.formAjax({
 					url: '<%=basePath%>cartList_save'
 					,data: {id: '${goods.id}', count: $(".number").eq(0)}
 				});
 			}
 			,addCol: function(){
 				util.formAjax({
 					url: '<%=basePath%>collection_save'
 					,data: {id: '${goods.id}'}
 				});
 			}
 		});
 		layer.msg("<s:text name="shop.common.homeLayuiAlert"/>");
 	})
	</script>
 </body>
</html>