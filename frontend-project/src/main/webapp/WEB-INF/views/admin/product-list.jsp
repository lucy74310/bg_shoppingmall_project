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
<title>상품목록</title>
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
			<c:param name="active" value="product-list" />
		</c:import>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<!-- navigation -->
			<c:import url='/WEB-INF/views/admin/includes/navigation.jsp' />
			<!-- /navigation -->

			<div class="container-fluid">
				<h2 class="mt-4">상품목록</h2>
				<br>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">NO</th>
							<th scope="col">상품명</th>
							<th scope="col">가격</th>
							<th scope="col">요약설명</th>
							<th scope="col">진열여부</th>
							<th scope="col">판매여부</th>
							<th scope="col">옵션사용</th>
							<th scope="col">재고사용</th>
							<th scope="col">재고</th>
							<th scope="col">품절표시</th>
							<th scope="col">적립율</th>
							<th scope="col">배송비</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${products}" var="p" varStatus="i">
							<tr>
								<th scope="row">${i.index}+1</th>
								<td>${p.product_name }</td>
								<td>${p.product_price }</td>
								<td>${p.product_short_explain}</td>
								<td>${p.displayed}</td>
								<td>${p.selling}</td>
								<td>${p.use_option}</td>
								<td>${p.use_stock}</td>
								<td>${p.stock}</td>
								<td>${p.soldout_mark}</td>
								<td>${p.save_percentage}</td>
								<td>${p.shipping_price}</td>
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
	</script>
</body>

</html>
