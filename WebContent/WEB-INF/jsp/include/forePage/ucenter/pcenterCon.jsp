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
		    <div class="layui-tab-item layui-show" style="">
			    <c:forEach begin="0" items="${orders}" step="1" var="Order" varStatus="varsta">
			    <div class="layui-card p-order">
					<div class="layui-card-header">
						<label class="p-label-number-id default-color">订单号：${Order.id}</label>
						<label class="p-label-status var-color">
						<c:choose>
							<c:when test="${Order.paymentStatus eq '02'}">
								待付款
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}">
								等待发货
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '01'}">
								已发货
							</c:when>
							<c:when test="${Order.paymentStatus eq '03'}">
								订单取消
							</c:when> 
							<c:otherwise>
								订单已完成
							</c:otherwise>
						</c:choose>
						</label>
					</div>
					<div class="layui-card-body">
						<div class="layui-row cartlist">
							<div class="layui-col-md4"><s:text name="shop.common.goods"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.colour"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.size"/></div>
						</div>  
						<c:set value="0" var="sum" />
						<c:forEach begin="0" items="${orderItems}" step="1" var="OrderItem" varStatus="varsta">
							<c:if test="${OrderItem.orderId eq Order.id}">
							<c:set value="${OrderItem.count+sum}" var="sum" />
							<div class="layui-row cartlist-etc pointer" style="overflow: hidden;" data-id="${OrderItem.orderItemId}">
								<div class="layui-col-md4" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${OrderItem.goodsId}">
									<img src="<%=basePath%>${OrderItem.url}" width="50px" height= "50px"/>
									<span>${OrderItem.name}</span>
								</div>
								<div class="layui-col-md2">
									<span>￥<fmt:formatNumber type="number" value="${OrderItem.price*OrderItem.discount}" maxFractionDigits="2"/><del class="ori-price">￥${OrderItem.price}</del></span>
								</div>
								<div class="layui-col-md2"><span>${OrderItem.count}</span></div>
								<div class="layui-col-md2"><span class="color">${OrderItem.colour}</span></div>
								<div class="layui-col-md2"><span class="size">${OrderItem.size}</span></div>
							</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="p-order-total">
						<label class="default-color">共${sum}件</label>
						<label class="p-label-totalAmount default-color">应付总额：${Order.totalAmount}</label>
					</div>
					<div class="p-order-details">
						<label class="default-color">${Order.createDate}</label> 
						<c:if test="${Order.paymentStatus eq '02'}"><label class="p-label-btn pointer label-red" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">支付</label></c:if>
						<label class="p-label-btn pointer" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">订单详情</label>
						<c:if test="${(Order.paymentStatus eq '00' or Order.paymentStatus eq '02') and Order.logisticsStatus eq '02'}"><label class="p-label-btn pointer" shop-click="cancelOrder">取消订单</label></c:if> 
					</div>
			    </div>
			    </c:forEach>
		    </div>
		    <!-- END 全部订单 -->
		    
		    <!-- 	等待发货 
		    ${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}
		    -->
		    <div class="layui-tab-item">
				<c:forEach begin="0" items="${orders}" step="1" var="Order" varStatus="varsta">
			    <c:if test ="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}">
			    <div class="layui-card p-order">
					<div class="layui-card-header">
						<label class="p-label-number-id">订单号：${Order.id}</label>
						<label class="p-label-status">
						<c:choose>
							<c:when test="${Order.paymentStatus eq '02'}">
								待付款
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}">
								等待发货
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '01'}">
								已发货
							</c:when> 
							<c:otherwise>
								订单已完成
							</c:otherwise>
						</c:choose>
						</label>
					</div>
					<div class="layui-card-body">
						<div class="layui-row cartlist">
							<div class="layui-col-md4"><s:text name="shop.common.goods"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.colour"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.size"/></div>
						</div>  
						<c:set value="0" var="sum" />
						<c:forEach begin="0" items="${orderItems}" step="1" var="OrderItem" varStatus="varsta">
							<c:if test="${OrderItem.orderId eq Order.id}">
							<c:set value="${OrderItem.count+sum}" var="sum" />
							<div class="layui-row cartlist-etc pointer" style="overflow: hidden;" data-id="${OrderItem.orderItemId}">
								<div class="layui-col-md4" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${OrderItem.orderItemId}">
									<img src="<%=basePath%>${OrderItem.url}" width="50px" height= "50px"/>
									<span>${OrderItem.name}</span>
								</div>
								<div class="layui-col-md2">
									<span>￥<fmt:formatNumber type="number" value="${OrderItem.price*OrderItem.discount}" maxFractionDigits="2"/><del class="ori-price">￥${OrderItem.price}</del></span>
								</div>
								<div class="layui-col-md2"><span>${OrderItem.count}</span></div>
								<div class="layui-col-md2"><span class="color">${OrderItem.colour}</span></div>
								<div class="layui-col-md2"><span class="size">${OrderItem.size}</span></div>
							</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="p-order-total">
						<label>共${sum}件</label>
						<label class="p-label-totalAmount">应付总额：${Order.totalAmount}</label>
					</div>
					<div class="p-order-details">
						<label>${Order.createDate}</label> 
						<c:if test="${Order.paymentStatus eq '02'}"><label class="p-label-btn pointer label-red" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">支付</label></c:if>
						<label class="p-label-btn pointer" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">订单详情</label>
						<c:if test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}"><label class="p-label-btn pointer" shop-click="cancelOrder">取消订单</label></c:if> 
					</div>
			    </div>
			    </c:if>
			    </c:forEach> 
			</div>
			<!-- 已发货 
			Order.paymentStatus eq '00' and Order.logisticsStatus eq '01'}
			-->
			<div class="layui-tab-item">
		    	<c:forEach begin="0" items="${orders}" step="1" var="Order" varStatus="varsta">
			    <c:if test ="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '01'}">
			    <div class="layui-card p-order">
					<div class="layui-card-header">
						<label class="p-label-number-id">订单号：${Order.id}</label>
						<label class="p-label-status">
						<c:choose>
							<c:when test="${Order.paymentStatus eq '02'}">
								待付款
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}">
								等待发货
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '01'}">
								已发货
							</c:when> 
							<c:otherwise>
								订单已完成
							</c:otherwise>
						</c:choose>
						</label>
					</div>
					<div class="layui-card-body">
						<div class="layui-row cartlist">
							<div class="layui-col-md4"><s:text name="shop.common.goods"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.colour"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.size"/></div>
						</div>  
						<c:set value="0" var="sum" />
						<c:forEach begin="0" items="${orderItems}" step="1" var="OrderItem" varStatus="varsta">
							<c:if test="${OrderItem.orderId eq Order.id}">
							<c:set value="${OrderItem.count+sum}" var="sum" />
							<div class="layui-row cartlist-etc pointer" style="overflow: hidden;" data-id="${OrderItem.orderItemId}">
								<div class="layui-col-md4" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${OrderItem.orderItemId}">
									<img src="<%=basePath%>${OrderItem.url}" width="50px" height= "50px"/>
									<span>${OrderItem.name}</span>
								</div>
								<div class="layui-col-md2">
									<span>￥<fmt:formatNumber type="number" value="${OrderItem.price*OrderItem.discount}" maxFractionDigits="2"/><del class="ori-price">￥${OrderItem.price}</del></span>
								</div>
								<div class="layui-col-md2"><span>${OrderItem.count}</span></div>
								<div class="layui-col-md2"><span class="color">${OrderItem.colour}</span></div>
								<div class="layui-col-md2"><span class="size">${OrderItem.size}</span></div>
							</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="p-order-total">
						<label>共${sum}件</label>
						<label class="p-label-totalAmount">应付总额：${Order.totalAmount}</label>
					</div>
					<div class="p-order-details">
						<label>${Order.createDate}</label> 
						<c:if test="${Order.paymentStatus eq '02'}"><label class="p-label-btn pointer label-red" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">支付</label></c:if>
						<label class="p-label-btn pointer" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">订单详情</label>
						<c:if test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}"><label class="p-label-btn pointer" shop-click="cancelOrder">取消订单</label></c:if> 
					</div>
			    </div>
			    </c:if>
			    </c:forEach> 
		    </div>
		  	<!-- 完成订单 -->
		    <div class="layui-tab-item">
		   		<c:forEach begin="0" items="${orders}" step="1" var="Order" varStatus="varsta">
			    <c:if test ="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '00'}">
			    <div class="layui-card p-order">
					<div class="layui-card-header">
						<label class="p-label-number-id">订单号：${Order.id}</label>
						<label class="p-label-status">
						<c:choose>
							<c:when test="${Order.paymentStatus eq '02'}">
								待付款
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}">
								等待发货
							</c:when>
							<c:when test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '01'}">
								已发货
							</c:when> 
							<c:otherwise>
								订单已完成
							</c:otherwise>
						</c:choose>
						</label>
					</div>
					<div class="layui-card-body">
						<div class="layui-row cartlist">
							<div class="layui-col-md4"><s:text name="shop.common.goods"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.price"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.count"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.colour"/></div>
							<div class="layui-col-md2"><s:text name="shop.common.size"/></div>
						</div>  
						<c:set value="0" var="sum" />
						<c:forEach begin="0" items="${orderItems}" step="1" var="OrderItem" varStatus="varsta">
							<c:if test="${OrderItem.orderId eq Order.id}">
							<c:set value="${OrderItem.count+sum}" var="sum" />
							<div class="layui-row cartlist-etc pointer" style="overflow: hidden;" data-id="${OrderItem.orderItemId}">
								<div class="layui-col-md4" lay-href="<%=basePath%>mall_chtml_goodsdetails?id=${OrderItem.orderItemId}">
									<img src="<%=basePath%>${OrderItem.url}" width="50px" height= "50px"/>
									<span>${OrderItem.name}</span>
								</div>
								<div class="layui-col-md2">
									<span>￥<fmt:formatNumber type="number" value="${OrderItem.price*OrderItem.discount}" maxFractionDigits="2"/><del class="ori-price">￥${OrderItem.price}</del></span>
								</div>
								<div class="layui-col-md2"><span>${OrderItem.count}</span></div>
								<div class="layui-col-md2"><span class="color">${OrderItem.colour}</span></div>
								<div class="layui-col-md2"><span class="size">${OrderItem.size}</span></div>
							</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="p-order-total">
						<label>共${sum}件</label>
						<label class="p-label-totalAmount">应付总额：${Order.totalAmount}</label>
					</div>
					<div class="p-order-details">
						<label>${Order.createDate}</label> 
						<c:if test="${Order.paymentStatus eq '02'}"><label class="p-label-btn pointer label-red" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">支付</label></c:if>
						<label class="p-label-btn pointer" lay-href="<%=basePath%>mall_chtml_payment?id=${Order.id}">订单详情</label>
						<c:if test="${Order.paymentStatus eq '00' and Order.logisticsStatus eq '02'}"><label class="p-label-btn pointer" shop-click="cancelOrder">取消订单</label></c:if> 
					</div>
			    </div>
			    </c:if>
			    </c:forEach> 
		    </div>
		  </div>
	</div>
</div>