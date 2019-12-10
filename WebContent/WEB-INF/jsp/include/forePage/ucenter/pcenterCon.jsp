<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="right-con" style="float: right; width: 868px; overflow: hidden;">
	<!-- person info -->
	<div class="" style="width: 100%; line-height: 120px; background: #fff; padding: 20px 30px; overflow: hidden;">
		<div style="float: left;  width: 10%; height: 100%; text-align: center;">
			<img src="<%=basePath%>resource/img/xihu.jpg" width="80" height="80"/>
		</div>
		<div style="float: left; line-height: 20px; padding-top: 24px; border-right: 1px solid #e6e6e6bf; width: 15%; height: 56px; text-align: center; ">
			<label style="display: block;">13430394603</label>
			<label style="display: block;"><i class="layui-icon layui-icon-star"></i></label>
		</div>
		<div class="info-tab">
			<label style="display: block;">
				<span>优惠券</span>
				<span class="var-color">3张</span>
			</label>
			<label style="display: block;">
				<span>待发货</span>
				<span class="var-color">1个</span>
			</label>
		</div>	
		<div  class="info-tab">
			<label style="display: block;">
				<span>礼品卡</span>
				<span class="var-color">1张</span>
			</label>
			<label style="display: block;">
				<span>待收货</span>
				<span class="var-color">6个</span>
			</label>
		</div>	
		<div class="info-tab">
			<label style="display: block;">
				<span>积分</span>
				<span class="var-color">6666</span>
			</label>
			<label style="display: block;">
				<span>待评价</span>
				<span class="var-color">2个</span>
			</label>
		</div>
	</div>
	<!-- logistics and order s -->
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this"><s:text name="shop.common.allOrder" /></li>
		    <li><s:text name="shop.common.waitOrder" /></li>
		    <li><s:text name="shop.common.shipped" /></li>
		    <li><s:text name="shop.common.completedOrder" /></li>
		  </ul>
		  <div class="layui-tab-content">
		    <!-- 	全部订单 -->
		    <div class="layui-tab-item layui-show" style="width: 100%; height: 500px; background-size: cover; background-image:url(<%=basePath%>resource/img/xihu.jpg);">
			    <c:forEach begin="0" items="${orders}" step="1" var="Order" varStatus="varsta">
			    <div class="layui-card">
					<div class="layui-card-header">
						订单号：${Order.id}&nbsp;&nbsp;&nbsp;&nbsp;创建时间：${Order.createDate}
					</div>
					<div class="layui-card-body">
						<div>
							item
						</div>
						<div>
							<c:if test="${Order.paymentStatus eq '02'}"><a href="<%=basePath%>mall_chtml_payment?id=${Order.id}">前往支付</a></c:if>
							<c:if test="${Order.logisticsStatus eq '00'}"><a href="<%=basePath%>mall_chtml_payment?id=${Order.id}">查看物流</a></c:if>
						</div>
					</div>
			    </div>
			    </c:forEach>
		    </div>
		    <!-- 	等待发货 -->
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
			<!-- 已发货 -->
			<div class="layui-tab-item layui-show" style="width: 100%; height: 500px; background-size: cover; background-image:url(<%=basePath%>resource/img/xihu.jpg);">
		    </div>
		  	<!-- 完成订单 -->
		    <div class="layui-tab-item layui-show" style="width: 100%; height: 500px; background-size: cover; background-image:url(<%=basePath%>resource/img/xihu.jpg);">
		    </div>
		  </div>
	</div>
</div>