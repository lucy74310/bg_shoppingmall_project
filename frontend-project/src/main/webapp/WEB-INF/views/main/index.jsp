<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
	<!-- Font Family Noto Sans KR -->
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
</head>
<style>
div.bg-wrap{
	margin : -1px 0;
	
}
</style>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<h1 class="my-4" id="shop-title">BSHOP</h1>
				<div class="list-group">
					<c:forEach items="${categories}" var="c" >
						<div class="list-group-item" >
							<a href="${pageContext.servletContext.contextPath }/${c.no}" class="bg-category-name" >${c.category_name}</a>
							<c:if test="${not empty c.sub_categories }">
								<span class="bg-drop-down" onclick="javascript:dropdown2(${c.no})"></span>
							</c:if>
						</div>
						<c:if test="${not empty c.sub_categories }">
							<div class="bg-wrap" data-parent="${c.no}" data-flag=false style="display:none;">
								<c:forEach items="${c.sub_categories}" var="sub1">
									<div class="list-group-item">
										<a href="${pageContext.servletContext.contextPath }/${sub1.no}" class="bg-category-name" style="margin-left:20px;">${sub1.category_name}</a>
										<c:if test="${not empty sub1.sub_categories }">
											<span class="bg-drop-down" onclick="javascript:dropdown2(${sub1.no})"></span>
										</c:if>
									</div>
									<c:if test="${not empty sub1.sub_categories }">
										<div class="bg-wrap" data-parent="${sub1.no}" data-flag=false style="display:none;">
											<c:forEach items="${sub1.sub_categories}" var="sub2">
												<div class="list-group-item">
													<a href="${pageContext.servletContext.contextPath }/${sub2.no}" class="bg-category-name" style="margin-left:40px;">${sub2.category_name}</a>
													<c:if test="${not empty sub2.sub_categories }">
														<span class="bg-drop-down" onclick="javascript:dropdown2(${sub2.no})"></span>
													</c:if>
												</div>
												<c:if test="${not empty sub2.sub_categories }">
													<div class="bg-wrap" data-parent="${sub2.no}" data-flag=false style="display:none;">
														<c:forEach items="${sub2.sub_categories}" var="sub3">
															<div class="list-group-item">
																<a href="${pageContext.servletContext.contextPath }/${sub3.no}" class="bg-category-name" style="margin-left:60px;">${sub3.category_name}</a>
															</div>
														</c:forEach>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</c:if> 
					</c:forEach>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="d-block img-fluid" src="http://placehold.it/900x350"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="http://placehold.it/900x350"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="http://placehold.it/900x350"
								alt="Third slide">
						</div>
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

				<div class="row">
					<c:if test="${empty products}">
						<h4> 등록된 상품이 없습니다.</h4>
					</c:if>
					<c:forEach items="${products}" var="p">
						<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<c:if test="${empty p.image_list}">
								<a href="#"><img class="card-img-top"
								src="${pageContext.servletContext.contextPath }/assets/image/icon/present_custom.png" alt="${p.product_name}이미지"></a>
							</c:if>
							<c:forEach items="${p.image_list}" var="i">
								<c:if test="${i.is_main == 'Y'}">
								<a href="#"><img class="card-img-top"
								src="${i.url}" onerror="this.src='${pageContext.servletContext.contextPath }/assets/image/icon/present_custom.png'"
								alt="${p.product_name}_이미지"></a>
								</c:if>
							</c:forEach>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">${p.product_name}</a>
								</h4>
								<h5>${p.product_price}</h5>
								<p class="card-text">${p.product_short_explain}</p>
							</div>
							<div class="card-footer">
								<!-- <small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small> -->
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.col-lg-9 -->
			
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
	
	
<script>

$(document).ready(function(){
	//dropDownList.click(drop_down_click);
	/* Object.values(dropDownList).forEach(function(value, index){
		value.addEventListener('click', drop_down_click);
	})
	console.log(typeof dropDownList); */
});

/* var drop_down_click = function(event) {
	console.log('drop_down_click');
	
	console.log(event.target);
	console.log(event.target.dataset.no);
	var data_no = event.target.dataset.no;
	console.log($('div.list-group-item'));
	console.log($('div.list-group-item[data-parent="'+data_no+'"]'));
	$('div.list-group-item[data-parent="'+data_no+'"]').css('display', 'block');
} */

var dropdown2 = function(no) {
	/* var changeSubject = document.querySelectorAll('div.list-group-item[data-parent="'+no+'"]');
	var lists = $('div.list-group-item[data-parent="'+no+'"]')
	var flag = changeSubject[0].dataset.flag;
	if(flag == "false") {
		lists.css('display', 'block');
		$('.list-group-item span[data-no="'+no+'"]').css('background-image', 'url(${pageContext.servletContext.contextPath }/assets/image/icon/drop-up-arrow.png)');
		Object.values(changeSubject).forEach(function(value, index){
			value.dataset.flag = true;
		});
	} else {
		lists.css('display', 'none');
		$('.list-group-item span[data-no="'+no+'"]').css('background-image', 'url(${pageContext.servletContext.contextPath }/assets/image/icon/drop-down-arrow.png)');
		Object.values(changeSubject).forEach(function(value, index){
			value.dataset.flag = false;
		});
	} */
	var subject = $('div.bg-wrap[data-parent="'+no+'"]');
	var s =document.querySelector('div.bg-wrap[data-parent="'+no+'"]');
	var flag = s.dataset.flag;
	if(flag == "false") {
		s.dataset.flag=true;
		subject.css('display', 'block');
	} else {
		s.dataset.flag=false;
		subject.css('display', 'none');
	}
	
}


</script>
	
</body>

</html>
