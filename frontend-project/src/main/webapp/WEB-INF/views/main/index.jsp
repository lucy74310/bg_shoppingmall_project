<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
<!-- 장바구니 담기 시 모달 css -->
<link
	href="${pageContext.servletContext.contextPath }/assets/css/add-cart-modal.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
	<sec:authorize access="isAuthenticated()" var="authenticated"></sec:authorize>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->

	<div class="container">
		<div class="row">
			<!-- col-lg-3 -->
			<!-- Category Navigation -->
			<c:import url='/WEB-INF/views/includes/category.jsp' />
			<!-- /.Navigation -->
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<br> <br> <br> <br>
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
											<a
												href="${pageContext.servletContext.contextPath }/product/detail/${p.no}"><img
												class="card-img-top"
												src="${pageContext.servletContext.contextPath }/assets/image/icon/present_custom.png"
												alt="${p.product_name}이미지"></a>
										</c:when>
										<c:otherwise>
											<c:forEach items="${p.image_list}" var="i">
												<c:if test="${i.is_main == 'Y'}">
													<a
														href="${pageContext.servletContext.contextPath }/product/detail/${p.no}"><img
														class="card-img-top" style="max-height: 190px;"
														src="${pageContext.servletContext.contextPath }${i.url}"
														onerror="this.src='${pageContext.servletContext.contextPath }/assets/image/icon/present_custom.png'"
														alt="${p.product_name}_이미지"></a>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<div class="card-body">
										<h4 class="card-title">
											<a
												href="${pageContext.servletContext.contextPath }/product/detail/${p.no}">${p.product_name}</a>
										</h4>
										<h5>${p.product_price}</h5>
										<p class="card-text">${p.product_short_explain}</p>
									</div>
									<div class="card-footer">
										<small class="text-muted"> <a href="#"
											class="btn btn-success buy-btn">Buy</a>
											<button type="button" class="btn btn-warning add-btn" data-pno="${p.no}">+ Cart</button>
											<!-- data-toggle="modal" data-target="#myModal" -->
										</small>
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

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">장바구니</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h4 id="p_name"></h4>
					<br>
					<div id="p_image"></div>
					<div id="po_list"></div>
					<div class="count-title">
						<h6>수량</h6>	
						<input type="number" class="form-control text-center" name="count" class="count-val" value="1">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-warning" id="cart-add">담기</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
	<script>
		$(function() {
			/* 장바구니 담기 */
			$('.add-btn').click(function(e) {
				var clonedata = $('.modal-body').clone();
				var pno = e.target.dataset.pno;
				if ('${authenticated}' == "true") {
					$.ajax({
					url : '${pageContext.servletContext.contextPath }/product/api/detail/'
							+ pno,
					type : 'get',
					dataType : 'json',
					success : function(res) {
						clonedata.children('#p_name').html( res.product_name + '<span class="price">'+res.product_price+'</span>');
						res.image_list
								.forEach(function(v) {
									if (v.is_main == "Y") {
										var img = "<img src='${pageContext.servletContext.contextPath }/"+ v.url + "' class='image'/>"
										clonedata
												.children(
														'#p_image')
												.html(
														img);
									}
								});

						var po_list = " <h6> 옵션 </h6>";
						po_list += "<select name='product_option_no' data-pno="+res.no+"> ";
						res.po_list
								.forEach(function(v) {
									po_list += " <option value=" + v.no + " data-plusprice="+v.plus_price+"> "
											+ v.po_name
											+ "       (+"
											+ v.plus_price
											+ ") </option>"
								})
						po_list += "</select> <hr>";
						
						
						clonedata.children('#po_list').html(po_list);
						$('.modal-body').replaceWith(clonedata);
						
						$('#cart-add').click(add_cart);
						$('#myModal').modal('show');
					},
					error : function(xhr, err) {
						console.error('error : ',
								err);
					}
				
				});
				} else {
					alert('로그인이 필요한 서비스입니다.');
				}
				
			});
			
			
		});
		
		var add_cart = function(e) {
			$('#cart-add').unbind('click');
			var count = $('input[name="count"]').val();
			var pono = $('select[name="product_option_no"]').val();
			var price = Number($('#p_name .price').text()) + Number($('option[value='+pono+']').data('plusprice'));
			var send_data = { 
					'product_option_no' : pono,
					'count' : count,
					'price' : price
			}
			$.ajax({
				url : '/shop/cart/add',
				type: 'POST',
				contentType : "application/json",
				data : JSON.stringify(send_data),
				success: function(res) {
					$('#myModal').modal('hide');
					alert('상품이 장바구니에 추가되었습니다.')
				},
				error: function(xhr, error) {
					
				}
				
				
			}); 
			
			
		}
	</script>
</body>

</html>
