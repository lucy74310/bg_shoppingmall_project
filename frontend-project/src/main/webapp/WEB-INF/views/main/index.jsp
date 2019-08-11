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
	href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->

	<div class="container">
		<div class="row">
			<!-- col-lg-3 -->
			<!-- Category Navigation -->
			<c:import url='/WEB-INF/views/includes/category.jsp'/>
			<!-- /.Navigation -->
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<br><br><br><br>
				<div class="row">
					<c:if test="${empty products}">
						<h4>등록된 상품이 없습니다.</h4>
					</c:if>
					<c:forEach items="${products}" var="p">
						<c:if test="${p.displayed =='Y' }">
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="card h-100">
									<c:choose>
										<c:when test="${empty p.image_list}">
											<a href="${pageContext.servletContext.contextPath }/product/detail/${p.no}"><img class="card-img-top"
												src="${pageContext.servletContext.contextPath }/assets/image/icon/present_custom.png"
												alt="${p.product_name}이미지"></a>
										</c:when>
										<c:otherwise>
											<c:forEach items="${p.image_list}" var="i">
												<c:if test="${i.is_main == 'Y'}">
													<a href="${pageContext.servletContext.contextPath }/product/detail/${p.no}"><img class="card-img-top"
														style="max-height: 190px;"
														src="${pageContext.servletContext.contextPath }${i.url}"
														onerror="this.src='${pageContext.servletContext.contextPath }/assets/image/icon/present_custom.png'"
														alt="${p.product_name}_이미지"></a>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<div class="card-body">
										<h4 class="card-title">
											<a href="${pageContext.servletContext.contextPath }/product/detail/${p.no}">${p.product_name}</a>
										</h4>
										<h5>${p.product_price}</h5>
										<p class="card-text">${p.product_short_explain}</p>
									</div>
									<div class="card-footer">
										<small class="text-muted">&#9733; &#9733; &#9733;
											&#9733; &#9734;</small>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<!-- /.row -->
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
	
</body>

</html>
