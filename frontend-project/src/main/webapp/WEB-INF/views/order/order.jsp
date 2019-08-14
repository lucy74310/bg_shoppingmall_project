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
	href="${pageContext.servletContext.contextPath }/assets/css/shop-order.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
			<form method="post" action="${pageContext.servletContext.contextPath }/order" id="orderForm">
			<div class="col-lg-9  p-5 bg-white rounded shadow-sm mb-5 custom">
				<!-- Shopping cart table -->
				<div class="table-responsive">
					<h4>상품 내역</h4>
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
									<div class="py-2 text-uppercase">Remove</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty order_list}">
								<c:forEach items="${order_list}" var="order" varStatus="status">
									<tr>
										<th scope="row">
											<div class="p-2">
												<img
													src="${pageContext.servletContext.contextPath }/${order.main_url}"
													alt="" width="70" class="img-fluid rounded shadow-sm">
												<div class="ml-3 d-inline-block align-middle">
													<h5 class="mb-0">
														<a href="#" class="text-dark d-inline-block align-middle">${order.product_name }</a>
													</h5>
													<span
														class="text-muted font-weight-normal font-italic d-block">${order.po_name }</span>
												</div>
											</div>
										</th>
										<td class="align-middle"><strong>${order.count }</strong></td>
										<td class="align-middle"><strong>￦ ${order.price * order.count}
										</strong></td>
										<td class="align-middle text-center"><a href="#"
											class="text-dark delete-one"
											data-apono="${order.product_option_no}"><i
												class="fa fa-trash"></i></a></td>
										<td style="display: none;">
												<input type="hidden"
													name='cart_list[${status.index}].product_name'
													value="${order.product_name}" /> <input type="hidden"
													name='cart_list[${status.index}].po_name'
													value="${order.po_name}" /> <input type="hidden"
													name='cart_list[${status.index}].product_option_no'
													value="${order.product_option_no}" /> <input type="hidden"
													name='cart_list[${status.index}].member_no'
													value="${order.member_no}" /> <input type="hidden"
													name='cart_list[${status.index}].price' value="${c.price}" />
												<input type="hidden" name='cart_list[${status.index}].count'
													value="${order.count}" />
											</form>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				<div>
					<div class="orderArea ">
						<div class="title">
							<h3>주문 정보</h3>
							<p class="required">
								<img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"> 필수입력사항
							</p>
						</div>
						<div class="">
							<table border="1" summary="">
								<caption>주문 정보 입력</caption>
								<colgroup>
									<col style="width: 139px;">
									<col style="width: auto;">
								</colgroup>
								<!-- 국내 쇼핑몰 -->
								<tbody class="address_form">
									<tr>
										<th scope="row">주문하시는 분 <img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></th>
										<td><input id="oname" name="orderer_name" placeholder="" size="15" value="${member.name}" type="text"></td>
									</tr>
									<tr class="">
										<th scope="row">주소 
											<img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수">
										</th>
										<td>
											<input id="ozipcode" name="orderer_zipcode"  placeholder="" size="6" maxlength="6" readonly="1" value="" type="text"> <a href="#none"
											id="btn_search_ozipcode" class="btnNormal">우편번호</a> 
											<br>
											<input id="oaddr1" name="orderer_addr1" class="" placeholder="" size="40" readonly="1" value="" type="text">
											<span class="txtInfo">기본주소</span> <br> 
											<input id="oaddr2" name="orderer_addr2" class="" placeholder="" size="40" value="" type="text"> 
											<span class="txtInfo">나머지주소</span>
										</td>
									</tr>
									<tr class="">
										<th scope="row">일반전화 <span class=""><img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></span>
										</th>
										<td><select id="ophone1" name="orderer_phone1">
												<option value="02">02</option>
												<option value="031">031</option>
												<option value="032">032</option>
												<option value="033">033</option>
												<option value="041">041</option>
												<option value="042">042</option>
												<option value="043">043</option>
												<option value="044">044</option>
												<option value="051">051</option>
												<option value="052">052</option>
												<option value="053">053</option>
												<option value="054">054</option>
												<option value="055">055</option>
												<option value="061">061</option>
												<option value="062">062</option>
												<option value="063">063</option>
												<option value="064">064</option>
												<option value="0502">0502</option>
												<option value="0503">0503</option>
												<option value="0504">0504</option>
												<option value="0505">0505</option>
												<option value="0506">0506</option>
												<option value="0507">0507</option>
												<option value="070">070</option>
												<option value="010">010</option>
												<option value="011">011</option>
												<option value="016">016</option>
												<option value="017">017</option>
												<option value="018">018</option>
												<option value="019">019</option>
										</select>-<input id="ophone2" name="orderer_phone2" maxlength="4" size="4" value="" type="text">-<input
											id="ophone3" name="orderer_phone3" maxlength="4" size="4" value="" type="text">
										</td>
									</tr>
									<tr class="">
										<th scope="row">휴대전화 
										<span class="displaynone"><img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></span>
										</th>
										<td><select id="otele1" name="orderer_telephone1">
												<option value="010">010</option>
												<option value="011">011</option>
												<option value="016">016</option>
												<option value="017">017</option>
												<option value="018">018</option>
												<option value="019">019</option>
										</select>-<input id="otele2" name="orderer_telephone2" maxlength="4" size="4" value="" type="text">-<input
											id="otele3" name="orderer_telephone3" maxlength="4" size="4" value="" type="text"></td>
									</tr>
									<tr>
										<th scope="row">이메일 <img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></th>
										<td><input id="oemail" name="orderer_email1" value="" type="text">@<input id="orderer_email2"
											name="orderer_email2" readonly="readonly" value="" type="text"><select
											id="oemail3">
												<option value="" selected="selected">- 이메일 선택 -</option>
												<option value="naver.com">naver.com</option>
												<option value="daum.net">daum.net</option>
												<option value="nate.com">nate.com</option>
												<option value="hotmail.com">hotmail.com</option>
												<option value="yahoo.com">yahoo.com</option>
												<option value="empas.com">empas.com</option>
												<option value="korea.com">korea.com</option>
												<option value="dreamwiz.com">dreamwiz.com</option>
												<option value="gmail.com">gmail.com</option>
												<option value="etc">직접입력</option>
										</select>
											<ul class="gBlank5 txtInfo">
												<li>- 이메일을 통해 주문처리과정을 보내드립니다.</li>
												<li>- 이메일 주소란에는 반드시 수신가능한 이메일주소를 입력해 주세요</li>
											</ul></td>
									</tr>
								</tbody>

								<!-- 비회원 결제 -->
								<!-- <tbody class="noMember displaynone">
									<tr class="ec-orderform-NoMemberPasswdRow">
										<th scope="row">주문조회 비밀번호 <img
											src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"
											alt="필수"></th>
										<td><span id="order_password_alert" class="txtWarn"></span><span
											id="order_password_msg"> (영문대소문자/숫자/특수문자 중 2가지 이상 조합,
												6자~16자)</span></td>
									</tr>
									<tr class="ec-orderform-NoMemberPasswdRow">
										<th scope="row">주문조회 비밀번호<br>확인 <img
											src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"
											alt="필수"></th>
										<td><span id="order_password_confirm_alert"
											class="txtWarn"></span></td>
									</tr>
								</tbody> -->
							</table>
						</div>
					</div>
					<div class="orderArea">
						<div class="title">
							<h3>배송 정보</h3>
							<p class="required">
								<img
									src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"
									alt="필수"> 필수입력사항
							</p>
						</div>
						<div class="ec-base-table typeWrite">
							<table border="1" summary="">
								<caption>배송 정보 입력</caption>
								<colgroup>
									<col style="width: 139px;">
									<col style="width: auto;">
								</colgroup>
								<!-- 비회원 결제 -->
								<!-- 국내 배송지 정보 -->
								<tbody class="">
									<tr class="">
										<th scope="row">배송지 선택</th>
										<td>
											<div class="address">
												<input id="sameaddr0" name="sameaddr"  value="T" type="radio">
													<label for="sameaddr0">주문자 정보와 동일</label> 
												<input id="sameaddr1" name="sameaddr"	value="F" type="radio">
													<label for="sameaddr1">새로운배송지</label>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">받으시는 분 <img
											src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"
											alt="필수"></th>
										<td><input id="rname" name="rname" 
											placeholder="" size="15" value="" type="text"></td>
									</tr>
									<tr>
										<th scope="row">주소 <img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></th>
										<td><input id="rzipcode" name="receiver_zipcode" placeholder="" size="6" maxlength="6"
											readonly="1" value="" type="text"> 
											<a href="#none" id="btn_search_rzipcode" class="btnNormal">우편번호</a><br>
											<input id="raddr1" name="receiver_addr1"  class="inputTypeText" placeholder="" size="40" readonly="1" value="" type="text">
											<span class="grid">기본주소</span><br> 
											<input id="raddr2" name="receiver_addr2" 
											class="inputTypeText" placeholder="" size="40" value="" type="text">
											<span class="grid">나머지주소</span><span class="grid displaynone">(선택입력가능)</span></td>
									</tr>
									<tr class="">
										<th scope="row">일반전화 <span class=""><img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="필수"></span>
										</th>
										<td><select id="rphone1" name="receiver_phone1">
												<option value="02">02</option>
												<option value="031">031</option>
												<option value="032">032</option>
												<option value="033">033</option>
												<option value="041">041</option>
												<option value="042">042</option>
												<option value="043">043</option>
												<option value="044">044</option>
												<option value="051">051</option>
												<option value="052">052</option>
												<option value="053">053</option>
												<option value="054">054</option>
												<option value="055">055</option>
												<option value="061">061</option>
												<option value="062">062</option>
												<option value="063">063</option>
												<option value="064">064</option>
												<option value="0502">0502</option>
												<option value="0503">0503</option>
												<option value="0504">0504</option>
												<option value="0505">0505</option>
												<option value="0506">0506</option>
												<option value="0507">0507</option>
												<option value="070">070</option>
												<option value="010">010</option>
												<option value="011">011</option>
												<option value="016">016</option>
												<option value="017">017</option>
												<option value="018">018</option>
												<option value="019">019</option>
										</select>-<input id="rphone2" name="receiver_phone2" maxlength="4"
											size="4" value="" type="text">-<input
											id="rphone3" name="receiver_phone3" maxlength="4"
											size="4" value="" type="text"></td>
									</tr>
									<tr class="">
										<th scope="row">휴대전화 <span class="displaynone"><img
												src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"
												alt="필수"></span>
										</th>
										<td><select id="rtele1" name="receiver_telephone1">
												<option value="010">010</option>
												<option value="011">011</option>
												<option value="016">016</option>
												<option value="017">017</option>
												<option value="018">018</option>
												<option value="019">019</option>
										</select>-<input id="rtele2" name="receiver_telephone2" maxlength="4"
											size="4" value="" type="text">-<input
											id="rtele3" name="receiver_telephone3" maxlength="4"
											 size="4" value="" type="text"></td>
									</tr>
								</tbody>

								<!-- 국내 배송관련 정보 -->
								<tbody class="delivery ">
									<tr class="">
										<th scope="row">배송메시지 <span class="displaynone"><img
												src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"
												alt="필수"></span>
										</th>
										<td><textarea id="omessage" name="omessage" maxlength="255" cols="70"></textarea>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<h4>결제예정금액</h4>
				</div>
				<!-- End -->

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
		$(function() {
			/* 주문자 주소 */
			$('#btn_search_ozipcode').click(function() {
				new daum.Postcode({
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수

		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }

		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    $('#oaddr2').val(extraAddr);
		                } else {
		                	$('#oaddr2').val('');
		                }

		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                $('#ozipcode').val(data.zonecode);
		                $('#oaddr1').val(addr);
		                
		                // 커서를 상세주소 필드로 이동한다.
		                $('#oaddr2').focus();
					}
				}).open();
			});
			/* 주문자 이메일 */
			$('#oemail3').change(function(){
				$('#orderer_email2').val($('#oemail3').val());
			});
			
			/*주문자 정보와 동일*/
			$('input[name=sameaddr]').change(function(){
				console.log('change');
				console.log($('input[name=sameaddr]:checked').val());
				if ($('input[name=sameaddr]:checked').val() == "T") {
					var oname = $('#oname').val();
					$('#rname').val(oname);
					
					var zipcode1 = $('#ozipcode').val();
					$('#rzipcode').val(zipcode1);
					var oaddr1 = $('#oaddr1').val();
					$('#raddr1').val(oaddr1);
					var oaddr2 = $('#oaddr2').val();
					$('#raddr2').val(oaddr2);
					var ophone1 = $('#ophone1').val();
					$('#rphone1').val(ophone1);
					var ophone2 = $('#ophone2').val();
					$('#rphone2').val(ophone2);
					var ophone3 = $('#ophone3').val();
					$('#rphone3').val(ophone3);
					var otele1 = $('#otele1').val();
					$('#rtele1').val(otele1);
					var otele2 = $('#otele2').val();
					$('#rtele2').val(otele2);
					var otele3 = $('#otele3').val();
					$('#rtele3').val(otele3);
				} else {
					console.log('gg');
					$('#rname').val('');
					$('#rzipcode').val('');
					$('#raddr1').val('');
					$('#raddr2').val('');
					$('#rphone1').val('');
					$('#rphone2').val('');
					$('#rphone3').val('');
					$('#rtele1').val('');
					$('#rtele2').val('');
					$('#rtele3').val('');
				} 
			});
			
			/*새로운배송지*/
			
		});
	</script>
</body>

</html>
