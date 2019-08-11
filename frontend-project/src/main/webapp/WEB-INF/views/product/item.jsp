<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Shop Homepage - Start Bootstrap Template</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.servletContext.contextPath }/assets/css/shop-item.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>

<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<!-- s:카테고리 -->
			<c:import url='/WEB-INF/views/includes/category.jsp' />
			<!-- e:카테고리 -->

			<div class="col-lg-9 wrap-div">
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<c:if test="${not empty p.image_list }">
							<c:forEach items="${p.image_list }" var="i" varStatus="index">
								<c:choose>
									<c:when test="${index.first }">
										<li data-target="#carouselExampleIndicators"
											data-slide-to="${index.index }" class="active"></li>
									</c:when>
									<c:otherwise>
										<li data-target="#carouselExampleIndicators"
											data-slide-to="${index.index }"></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</ol>
					<div class="carousel-inner" role="listbox">
						<c:if test="${not empty p.image_list }">
							<c:forEach items="${p.image_list }" var="i" varStatus="index">
								<c:choose>
									<c:when test="${index.first }">
										<div class="carousel-item active">
											<img class="d-block img-fluid"
												src="${pageContext.servletContext.contextPath }/${i.url}"
												alt="product image">
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item ">
											<img class="d-block img-fluid"
												src="${pageContext.servletContext.contextPath }/${i.url}"
												alt="product image">
										</div>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</c:if>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<div class="card detail-info my-4">
					<div class="card-header"><h4>${p.product_name}</h4></div>
					<div class="card-body">
						<br>
						<div class="card-text ">
						<h6 class="info">￦  ${p.product_price }</h6>
						<h6 class="info">(배송비)<c:choose><c:when test="${p.shipping_price eq 0}">  무료배송</c:when><c:otherwise>  ${p.shipping_price }</c:otherwise></c:choose></h6>
						<h6 class="info"> ${p.save_percentage } % 적립</h6>
						</div>
						<br>
						<p class="card-text">
							<h5 class="info">옵션</h5>
							<c:if test="${not empty p.po_list }" >
								<select name="select_product_option" class="option-box">
									<c:forEach items="${p.po_list }" var="po" varStatus="i">
										<option value="${po.no}" class="opd">${po.po_name }&nbsp;&nbsp;&nbsp;&nbsp;+${po.plus_price }</option>
									</c:forEach>
								</select>
							</c:if>
						</p>
						<div class="text-center">
							<a href="#" class="btn btn-success buy-btn">구매하기</a> <a href="#"
								class="btn btn-warning">장바구니 담기</a>
						</div>
					</div>
				</div>

				<div class="card card-outline-secondary my-4">
					<div class="card-header">Product Details</div>
					<div class="card-body">
						<p>${p.product_detail }</p>
						<!-- <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small> -->
						<hr>
						<a href="#" class="btn btn-success" onclick="goTop()"> 위로 가기 </a>
					</div>
				</div>
			</div>
			<!-- /.card -->


		</div>
		<!-- /.col-lg-9 -->

	</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
	<script>
		function goTop(e) {
			e.preventDefault();
			$('html').scrollTop(0);
		}
	</script>
</body>

</html>
