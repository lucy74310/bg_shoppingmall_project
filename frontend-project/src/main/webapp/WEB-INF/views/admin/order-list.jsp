<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>카테고리 관리</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.servletContext.contextPath }/assets/css/shop-admin.css"
	rel="stylesheet">
</head>
<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<c:import url='/WEB-INF/views/admin/includes/sidebar.jsp'>
			<c:param name="active" value="order" />
		</c:import>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<!-- navigation -->
			<c:import url='/WEB-INF/views/admin/includes/navigation.jsp' />
			<!-- /navigation -->

			<div class="container-fluid">
				<h1 class="mt-4">주문 관리</h1>
				<br>
				<div class="table-responsive">
					<table class="table table-hover" style="overflow-y: hidden;">
						<thead class="thead-dark">
							<tr class="text-center">
								<th scope="col">NO</th>
								<th scope="col">주문코드</th>
								<th scope="col">날짜</th>
								<th scope="col">상품명</th>
								<th scope="col">총액</th>
								<th scope="col">주문상태</th>
								<th scope="col">회원번호</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orders}" var="o" varStatus="status">
								<tr class="text-center parent-row" data-no="${o.no}" data-flag="false">
									<td>${status.count}</td>
									<td>${o.order_code}</td>
									<td>${o.order_date}</td>
									<td>${o.order_name}</td>
									<td>${o.pay_amount }</td>
									<td>${o.order_state }</td>
									<td>${o.member_no }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="7"><strong>상품정보</strong></td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="7">
										<table class="table">
											<thead>
												<tr>
													<th scope="col" class="border-0 bg-light">
														<div class="p-2 px-3 text-uppercase">Product</div>
													</th>
													<th scope="col" class="border-0 bg-light">
														<div class="py-2 text-uppercase">Quantity</div>
													</th>
													<th scope="col" class="border-0 bg-light">
														<div class="py-2 text-uppercase">Price</div>
													</th>
													<th scope="col" class="border-0 bg-light">
														<div class="py-2 text-uppercase">주문처리상태</div>
													</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${not empty o.order_list}">
													<c:forEach items="${o.order_list}" var="order"
														varStatus="s">
														<tr>
															<th>
																<div class="p-2">
																	<img
																		src="${pageContext.servletContext.contextPath }/${order.main_image_url}"
																		alt="" width="70" class="img-fluid rounded shadow-sm">
																	<div class="ml-3 d-inline-block align-middle">
																		<h5 class="mb-0">
																			<a href="#"
																				class="text-dark d-inline-block align-middle">${order.product_name }</a>
																		</h5>
																		<span
																			class="text-muted font-weight-normal font-italic d-block">${order.po_name }</span>
																	</div>
																</div>
															</th>
															<td class="align-middle"><strong>${order.count }</strong></td>
															<td class="align-middle"><strong>￦
																	${order.price * order.count} </strong></td>
															<td class="align-middle text-center">${order.order_handling_state }</td>
														</tr>
														<c:set var="totalprice"
															value="${totalprice + (order.price * order.count) }" />
													</c:forEach>
												</c:if>
											</tbody>
										</table>
									</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="7"><strong>주문정보</strong></td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">주문자</td>
									<td colspan="5">${o.orderer_name }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">주소</td>
									<td colspan="5">${o.orderer_zipcode }${o.orderer_addr1 }
										${o.orderer_addr2 }</td>
								</tr >
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">일반전화</td>
									<td colspan="5">${o.orderer_phone }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">휴대전화</td>
									<td colspan="5">${o.orderer_telephone }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">이메일</td>
									<td colspan="5">${o.orderer_email }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="7"><strong>배송정보</strong></td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">받으시는분</td>
									<td colspan="5">${o.receiver_name}</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">주소</td>
									<td colspan="5">${o.receiver_zipcode }${o.receiver_addr1 }
										${o.receiver_addr2 }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">일반전화</td>
									<td colspan="5">${o.receiver_phone }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">휴대전화</td>
									<td colspan="5">${o.receiver_telephone }</td>
								</tr>
								<tr data-parent="${o.no }" style="display:none;">
									<td colspan="2">배송메세지</td>
									<td colspan="5">${o.shipping_msg }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /#page-content-wrapper -->

		</div>
		<!-- /#wrapper -->


		<!-- Bootstrap core JavaScript -->
		<script
			src="${pageContext.servletContext.contextPath }/assets/vendor/jquery/jquery.min.js"></script>
		<script
			src="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

		<!-- Menu Toggle Script -->
		<script>
			$("#menu-toggle").click(function(e) {
				e.preventDefault();
				$("#wrapper").toggleClass("toggled");
			});
			
			$(function(){
				var pastNo = 0; 
				var currNo = 0;
				$('.parent-row').click(function(e){
					console.log(e);
					currNo= e.currentTarget.dataset.no;
					var flag = e.currentTarget.dataset.flag;
					console.log(flag);
					if(flag == "false"){
						if(pastNo != 0) {
							$('*[data-parent="'+pastNo+'"]').each(function(i2,v2){
								v2.style.display = 'none';
							});
						}
						
						$('*[data-parent="'+currNo+'"]').each(function(i,v){
							
							v.style.display= '';
						});
						e.currentTarget.dataset.flag = "true";
					
					} else {
						$('*[data-parent="'+currNo+'"]').each(function(i,v){
							v.style.display= 'none';
						});
						e.currentTarget.dataset.flag = "false";
					}
					pastNo = currNo;
				})
			});
			
		</script>
</body>

</html>
