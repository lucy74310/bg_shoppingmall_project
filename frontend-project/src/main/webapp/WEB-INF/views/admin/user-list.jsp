<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>사용자 목록</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin.css" rel="stylesheet">
</head>
<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<c:import url='/WEB-INF/views/admin/includes/sidebar.jsp'>
			<c:param name="active" value="user-list" />
		</c:import>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			
			<!-- navigation -->
			<c:import url='/WEB-INF/views/admin/includes/navigation.jsp' />
			<!-- /navigation -->

			<div class="container-fluid">
				<h2 class="mt-4">사용자 목록</h2>
				<br>
				<div class="table-responsive">
					<table class="table table-hover" style="overflow-y: hidden;">
						<thead class="thead-dark">
							<tr>
								<th scope="col">NO</th>
								<th scope="col">id</th>
								<th scope="col">이름</th>
								<th scope="col">전화번호</th>
								<th scope="col">휴대전화번호</th>
								<th scope="col">이메일</th>
								<th scope="col">생일</th>
								<th scope="col">포인트</th>
								<th scope="col">가입일</th>
								<th scope="col">탈퇴일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${members}" var="m" varStatus="i">
								<tr>
									<th scope="row">${i.index+1}</th>
									<td>${m.id }</td>
									<td>${m.name }</td>
									<td>${m.phone }</td>
									<td>${m.telephone }</td>
									<td>${m.email }</td>
									<td>${m.birth }</td>
									<td>${m.point }</td>
									<td>${m.join_date }</td>
									<td>${m.leave_date }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	
	<!-- Bootstrap core JavaScript -->
	<script src="${pageContext.servletContext.contextPath }/assets/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath }/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>
</body>

</html>
