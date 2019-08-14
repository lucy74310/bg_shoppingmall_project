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
<meta name="description" content="">
<meta name="author" content="">
<title>BSHOP</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Family Noto Sans KR -->
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.servletContext.contextPath }/assets/css/shop-cart.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="cart" />
	</c:import>
	<!-- /.Navigation -->

	<div class="container">
		<div class="row">
			<!-- col-lg-3 -->
			<!-- Category Navigation -->
			<c:import url='/WEB-INF/views/includes/category.jsp' />
			<!-- /.Navigation -->
			<!-- /.col-lg-3 -->

			<div class="col-lg-9  p-5 bg-white rounded shadow-sm mb-5 custom">
				<!-- Shopping cart table -->
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th scope="col" class="border-0 bg-light">
									<div class="p-2 px-3 text-uppercase">Check</div>
								</th>
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
									<div class="py-2 text-uppercase">Remove</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<form id="cart_list_info" method="post" action="${pageContext.servletContext.contextPath }/orderform" >
							<c:if test="${not empty cart_list}">
								<c:forEach items="${cart_list}" var="c" varStatus="status">
									<tr>
										<td class="p-2 text-center align-middle"><input
											type="checkbox" name="selpo"
											data-pono="${c.product_option_no}"></td>
										<th scope="row">
											<div class="p-2">
												<img src="${pageContext.servletContext.contextPath }/${c.main_url}" alt="" width="70" class="img-fluid rounded shadow-sm">
												<div class="ml-3 d-inline-block align-middle">
													<h5 class="mb-0">
														<a href="#" class="text-dark d-inline-block align-middle">${c.product_name }</a>
													</h5>
													<span class="text-muted font-weight-normal font-italic d-block">${c.po_name }</span>
												</div>
											</div>
										</th>
										<td class="align-middle"><strong>${c.count }</strong></td>
										<td class="align-middle"><strong>￦ ${c.price * c.count}
										</strong></td>
										<td class="align-middle text-center"><a href="#"
											class="text-dark delete-one"
											data-apono="${c.product_option_no}"><i
												class="fa fa-trash"></i></a></td>
										<td style="display:none;">
											<input type="hidden" name='cart_list[${status.index}].product_name' value="${c.product_name}"/>
											<input type="hidden" name='cart_list[${status.index}].po_name' value="${c.po_name}"/>
											<input type="hidden" name='cart_list[${status.index}].product_option_no' value="${c.product_option_no}"/>
											<input type="hidden" name='cart_list[${status.index}].member_no' value="${c.member_no}"/>
											<input type="hidden" name='cart_list[${status.index}].price' value="${c.price}"/>
											<input type="hidden" name='cart_list[${status.index}].count' value="${c.count}"/>
											<input type="hidden" name='cart_list[${status.index}].main_url' value="${c.main_url}"/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</form>
						</tbody>
					</table>
				</div>
				<!-- End -->
				<button type="button" class="btn btn-info custom-btn order-all">전체주문</button>
				<button type="button" class="btn btn-info custom-btn order-sel">선택주문</button>
				<button type="button" class="btn btn-light custom-btn delete-all">전체삭제</button>
				<button type="button" class="btn btn-light custom-btn delete-sel">선택삭제</button>
			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
	<br>
	<br>
	<br>
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
	<script>
	$(function(){
		
		/* 휴지통 버튼 눌러서 삭제 => 1개 삭제*/	
		$('.delete-one').click(function(e) {
			e.preventDefault();
			var pono = e.currentTarget.dataset.apono;
			$.ajax({
				url: '${pageContext.servletContext.contextPath }/cart/delete/' + pono,
				type: 'delete',
				success: function(res) {
					console.log(res);
					if(res.result == "success") {
						$('*[data-pono="'+pono+'"]').closest('tr').remove();
					} else {
						alert("삭제에 실패하였습니다. 관리자에게 문의해 주세요.");
					}
				},
				error: function(xhr, err) {
					console.error('err : ', err);	
				}
			});
		});
		/* 선택삭제 */
		$('.delete-sel').click(function(e) {
			var list = [];
			$('input[name="selpo"]:not(:checked)').each(function(i, v){
				var tr = v.closest('tr');
				console.log(tr);
			})
		});
		/* 전체삭제 */
		$('.delete-all').click(function(e) {
			var list = [];
			$('input[name="selpo"]:not(:checked)').each(function(i, v){
				var tr = v.closest('tr');
				console.log(tr);
			})
		});
		/* 선택주문 */
		$('.order-sel').click(function(e) {
			var list = [];
			$('input[name="selpo"]:not(:checked)').each(function(i, v){
				 v.closest('tr').remove();
				 $('#cart_list_info').submit();
			})
		});
		/* 전체주문 */
		$('.order-all').click(function(e){
			$('#cart_list_info').submit();
		})	;
		
		
	})
	
	
	
	</script>
</body>

</html>
