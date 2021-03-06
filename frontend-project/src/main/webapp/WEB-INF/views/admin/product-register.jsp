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
<title>상품 등록</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link

	href="${pageContext.servletContext.contextPath }/assets/css/shop-admin.css"
	rel="stylesheet">
<script
	src="${pageContext.servletContext.contextPath }/assets/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<link
	href="${pageContext.servletContext.contextPath }/assets/css/shop-admin-product.css"
	rel="stylesheet">
<!-- 상품 디테일 에디터 css  -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
<script>
	var deleteOption = function(e) {
		console.log('cancel-btn');
		console.log(e.path[2].dataset.option);
		var op_no = e.path[2].dataset.option;
		$('*[data-option="' + op_no + '"]').remove();
	}
	$(document).ready(function() {

		$('#summernote').summernote({
			height : 300, // set editor height
			minHeight : null, // set minimum height of editor
			maxHeight : null, // set maximum height of editor
			focus : true
		// set focus to editable area after initializing summernote
		});
	});
</script>

<!-- Menu Toggle Script -->

</head>
<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<c:import url='/WEB-INF/views/admin/includes/sidebar.jsp'>
			<c:param name="active" value="product-register" />
		</c:import>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<!-- navigation -->
			<c:import url='/WEB-INF/views/admin/includes/navigation.jsp' />
			<!-- /navigation -->

			<div class="container-fluid">
				<h1 class="mt-4">상품 등록</h1>
				<br>
				<div class="card-container">
					<form
						action="${pageContext.servletContext.contextPath}/admin/product/register"
						method="post" enctype="multipart/form-data">

						<!-- 상품정보 -->
						<div class="card" style="margin-bottom: -1px;">
							<div class="card-header">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a class="nav-link active" href="#">상품정보</a>
									</li>
								</ul>
							</div>
							<div class="card-body">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">상품명</span>
									</div>
									<input type="text" class="form-control" name="product_name"
										placeholder="ex) 여름 무지 린넨 셔츠" aria-label="Username"
										aria-describedby="basic-addon1" autofocus required />
								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text">￦</span>
									</div>
									<input type="number" class="form-control" name="product_price"
										required>
									<div class="input-group-append">
										<span class="input-group-text">원</span>
									</div>
								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">상품요약</span>
									</div>
									<input type="text" class="form-control"
										name="product_short_explain"
										placeholder="상품리스트 진열시 보이는 짧은 요약(250자)" aria-label="Username"
										aria-describedby="basic-addon1">
								</div>
								<span class="input-group-text" id="basic-addon1"
									style="border-bottom: none;">상품 상세 설명</span>
								<textarea name="product_detail" id="summernote"></textarea>
							</div>
						</div>
						<!-- 재고/옵션 -->
						<div class="card" style="margin-bottom: -1px;">
							<div class="card-header">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a class="nav-link active" href="#">재고/옵션</a></li>
								</ul>
							</div>
							<div class="card-body option-box etc-item-box">
								<div class="card text-center etc-item" style="width: 10rem;">
									<div class="card-header">재고 사용</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="radio"
											id="radio-use_stock-y" name="use_stock" value="Y"> <label
											for="radio-displayed-y">Y</label>&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" id="radio-use_stock-n" name="use_stock"
											value="N" checked> <label for="radio-displayed-n">N</label></li>
										<li class="list-group-item" id="stock" style="display: none;">
											<input type="text" name="stock" style="width: 5rem;"
											value="0" /> 개
										</li>
									</ul>
								</div>
								<div class="card text-center etc-item" style="width: 10rem;">
									<div class="card-header">옵션 사용</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="radio"
											id="radio-use_option-y" name="use_option" value="Y">
											<label for="radio-use_option-y">Y</label>&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" id="radio-use_option-n" name="use_option"
											value="N" checked> <label for="radio-use_option-n">N</label>
										</li>
										<li id="option-add-button"
											style="display: none; border: none;" class="btn btn-primary">+
											옵션추가</li>
									</ul>
								</div>
								<div id="option-list" style="display: none;">
									<%-- <div class='card text-center etc-item option' data-option=1
										data-listorder=0>
										<div class='card-header'>
											<input type='text' name='o_list[0].op_name'
												style='width: 7rem;' placeholder='옵션명' required /> <img
												src='${pageContext.servletContext.contextPath}/assets/image/icon/cancel-button.png'
												class='cancel-btn' onclick='javascript:deleteOption(event)'>
										</div>
										<ul class='list-group list-group-flush opd'>
											<li class='list-group-item'><input type='text'
												name='o_list[0].od_list[0].opd_name' style='width: 6rem;'
												placeholder='옵션 항목명' required> <input type='number'
												name='o_list[0].od_list[0].plus_price'
												style='width: 5.5rem;' placeholder='추가금액' required>원
											</li>
										</ul>
										<ul class='list-group list-group-flush'>
											<li class='btn btn-primary option-detail-add-button'
												style='border: none;' data-parent=1>+항목추가</li>
										</ul>
									</div> --%>
								</div>
							</div>
						</div>
						<!-- 이미지 -->
						<div class="card text-center" style="margin-bottom: -1px;">
							<div class="card-header">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a class="nav-link active" href="#">이미지</a>
									</li>
								</ul>
							</div>
							<div class="card-body image-list">
								<input type="button" class="btn btn-primary" id="add-image"
									value="+ 이미지 추가" style="margin-bottom: 10px;" /><br> <input
									type="file" name="image_add_list" class="btn btn-light" />
							</div>
						</div>
						<!-- 카테고리 -->
						<div class="card text-center" style="margin-bottom: -1px;">
							<div class="card-header">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a class="nav-link active" href="#">카테고리</a></li>
								</ul>
							</div>
							<div class="card-body">
								<p class="card-text">
									<c:forEach items="${categories }" var="c">
										<p>
											<input type="checkbox" id=${c.no } name="cate_list" value="${c.no }" /> 
											<label for="${c.no }">(대분류)${c.category_name }</label>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${not empty c.sub_categories }">
												<c:forEach items="${c.sub_categories }" var="mid">
													<input type="checkbox" id=${mid.no } name="cate_list" value="${mid.no }" />
													<label for="${mid.no }">(중분류)${mid.category_name }</label> &nbsp;&nbsp;&nbsp;&nbsp;
												<c:if test="${not empty mid.sub_categories }">
														<c:forEach items="${mid.sub_categories }" var="sm">
															<input type="checkbox" id=${sm.no } name="cate_list" value="${sm.no }" />
															<label for="${sm.no }">(소분류)${sm.category_name }</label> &nbsp;&nbsp;&nbsp;&nbsp;
															<c:if test="${not empty sm.sub_categories }">
																<c:forEach items="${sm.sub_categories }" var="d">
																	<input type="checkbox" id=${d.no } name="cate_list"
																		value="${d.no }" />
																	<label for="${d.no }">(상세분류)${d.category_name }</label> &nbsp;&nbsp;&nbsp;&nbsp;
																</c:forEach><br>
															</c:if>
														</c:forEach>
													</c:if>
												</c:forEach>
											</c:if>
										</p>
									</c:forEach>
								</p>
							</div>
						</div>
						<!-- 기타사항 -->
						<div class="card">
							<div class="card-header text-center">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a class="nav-link active" href="#">기타사항</a>
									</li>
								</ul>
							</div>
							<div class="card-body etc-item-box">
								<div class="card etc-item" style="width: 10rem;">
									<div class="card-header">판매여부</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="radio"
											id="radio-selling-y" name="selling" value="Y" checked>
											<label for="radio-selling-y">Y</label></li>
										<li class="list-group-item"><input type="radio"
											id="radio-selling-n" name="selling" value="N"> <label
											for="radio-selling-n">N</label></li>
									</ul>
								</div>
								<div class="card etc-item" style="width: 10rem;">
									<div class="card-header">진열여부</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="radio"
											id="radio-displayed-y" name="displayed" value="Y" checked>
											<label for="radio-displayed-y">Y</label></li>
										<li class="list-group-item"><input type="radio"
											id="radio-displayed-n" name="displayed" value="N"> <label
											for="radio-displayed-n">N</label></li>
									</ul>
								</div>
								<div class="card etc-item" style="width: 10rem;">
									<div class="card-header">품절표시</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="radio"
											id="radio-soldoutmark-y" name="soldout_mark" value="Y"
											checked> <label for="radio-soldoutmark-y">Y</label></li>
										<li class="list-group-item"><input type="radio"
											id="radio-soldoutmark-n" name="soldout_mark" value="N">
											<label for="radio-soldoutmark-n">N</label></li>
									</ul>
								</div>
								<div class="card etc-item" style="width: 10rem;">
									<div class="card-header">배송비</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="text"
											name="shipping_price" value="2500" style="width: 5rem;" /> 원</li>
									</ul>
								</div>
								<div class="card etc-item" style="width: 10rem;">
									<div class="card-header">적립율</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><input type="text"
											name="save_percentage" value="5" style="width: 5rem;" /> %</li>
									</ul>
								</div>
							</div>
						</div>
						<br> <input type="submit" class="btn btn-primary"
							style="float: left" value="등록">
					</form>
				</div>


			</div>

			<br> <br> <br>
		</div>
		<!-- /#page-content-wrapper -->
	</div>
	<!-- /#wrapper -->
	<script
		src="${pageContext.servletContext.contextPath }/assets/js/product-register.js"></script>
</body>

</html>
