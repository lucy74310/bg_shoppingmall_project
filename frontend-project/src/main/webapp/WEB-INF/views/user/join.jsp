<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!-- spring 태그 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>회원가입</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.servletContext.contextPath }/assets/css/shop-join.css"
	rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

	<div id="container" class="container">
		<header>
			<h1>회원가입</h1>
		</header>
		<div class="card card-container">

			<form:form modelAttribute="joinVo" name="joinForm"
				method="post"
				action="${pageContext.servletContext.contextPath }/joinUser">



				<!-- 이름 -->
				<div class="form-group input-group" style="margin-top:0">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa fa-user"></i>
						</span>
					</div>
					<form:input path="name" class="form-control" placeholder="Name"
						type="text" />
				</div>
				 <p class="errormsg"><form:errors path="name" /></p>
				
				<!-- Id -->
				<div class="form-group input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa fa-user"></i>
						</span>
					</div>
					<form:input path="id" id="id" class="form-control" placeholder="ID"/>
					<div class="input-group-append">
						<span  class="input-group-text" >
						<i id="question-mark" class="fa fa-question-circle" aria-hidden="true"></i>
						<i id="check-mark" class="fa fa-check-circle-o" aria-hidden="true"></i>
						</span>
					</div>
					<form:input type="hidden" id="idcheck" path="idcheck" value="false"/>
				</div>
				 <p class="errormsg"><form:errors path="idcheck" /><form:errors path="id" /></p>


				<%-- <img style="display: none" id="check-image"
					src="${pageContext.servletContext.contextPath }/assets/images/check.png" />
				<p
					style="font-weight: bold; color: #f00; text-align: left; padding: 0; margin: 0">
					<form:errors path="id" />
				</p> --%>

				


				<!-- 비밀번호 -->
				<div class="form-group input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa fa-lock"></i>
						</span>
					</div>
					<form:password path="password" class="form-control"	placeholder="Enter Password" />
				</div>
				<p class="errormsg"><form:errors path="password" /></p>
				
				<!-- 이메일 -->
				<div class="form-group input-group">
			    	<div class="input-group-prepend">
					    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
					 </div>
			        <form:input path="email" class="form-control" placeholder="Email address" type="email"/>
			    </div>
			   <p class="errormsg"><form:errors path="email" /></p>
				
				<!-- 휴대전화번호 -->
				<div class="form-group input-group">
			    	<div class="input-group-prepend">
					    <span class="input-group-text"> <i class="fa fa-phone" aria-hidden="true" ></i> </span>
					</div>
			    	<form:input path="telephone" class="form-control" placeholder="Telephone (123-456-7890)" type="tel"
			    		pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" />
			    </div>
			     <p class="errormsg"><form:errors path="telephone" /></p>
			    
			    <!-- 생일 -->
			    <div class="form-group input-group">
					<div class="input-group-prepend">
					    <span class="input-group-text"> <i class="fa fa-birthday-cake"></i> </span>
					 </div>
			        <form:input type="date" path="birth" class="form-control" placeholder="Birthday" />
			    </div>
			      <p class="errormsg"><form:errors path="birth" /></p>
				<%-- <spring:hasBindErrors name="memberVo">
					<c:if test="${errors.hasFieldErrors('name') }">
						<p
							style="font-weight: bold; color: red; text-align: left; padding: 0">
							<spring:message
								code="${errors.getFieldError( 'name' ).codes[0] }"
								text="${errors.getFieldError( 'name' ).defaultMessage }" />
						</p>
					</c:if>
				</spring:hasBindErrors> --%>
				
				<fieldset>
					<!-- form:checkbox path="agreeProv" value="y"/-->
					<form:checkbox path="agree" name="agree" value="true"/>
					<label>약관 동의</label>
					 <p class="errormsg" style="text-align:center;"><form:errors path="agree" /></p>
				</fieldset>

				<input type="submit" value="가입하기"
					class="btn btn-lg btn-primary btn-block btn-join">

			</form:form>
		</div>
	</div>
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
	<script type="text/javascript">
		$(function() {
			$('#question-mark').click(function(){
				var id = $("#id").val();
				if(id == "") {
					alert("id를 입력해주세요"); return;
				}
				$.ajax({
					
					url: "${pageContext.servletContext.contextPath }/checkid/" + $("#id").val() ,
					success: function(response){
						console.log(response);
						if(response.result == "fail"){
							alert(response.message);
							$('#idcheck').val(false);
							$('#id').val("");
						} else {
							alert("사용 가능한 id 입니다.");
							$('#idcheck').val(true);
							$('#check-mark').css('display', 'block');
							$('#question-mark').css('display', 'none');
						}
					},
					error: function(xhr, e) {
						console.error("error : " ,e , xhr);
					}
					
				});
			});
			$('#id').keydown(function(){
				console.log('chang');
				$('#idcheck').val(false);
				$('#check-mark').css('display', 'none');
				$('#question-mark').css('display', 'block');
			})
		}) 
	</script>
</body>
</html>