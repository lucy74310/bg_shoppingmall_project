<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	
	<title>Admin Login</title>
	
	<!-- Custom fonts for this template-->
	<link href="${pageContext.servletContext.contextPath}/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	
	<!-- Custom styles for this template-->
	<link href="${pageContext.servletContext.contextPath}/assets/css/admin.css" rel="stylesheet">
	
	<!-- Font Family Noto Sans KR -->
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<style>
</style>
<body class="bg-dark">

  <div class="container">
    <div class="card card-login mx-auto mt-5" style="font-family:'Noto Sans KR',sans-serif;">
      <div class="card-header">관리자 로그인</div>
      <div class="card-body">
        <form method="post" action="${pageContext.servletContext.contextPath}/admin_login">
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="inputId" name="id" class="form-control" placeholder="ID" required="required" autofocus="autofocus">
              <label for="inputId">ID</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required="required">
              <label for="inputPassword">Password</label>
            </div>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label>
                <input type="checkbox" value="remember-me">
                Remember Password
              </label>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">로그인</button>
        </form>
        <!-- <div class="text-center">
          <a class="d-block small mt-3" href="register.html">Register an Account</a>
          <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
        </div> -->
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="${pageContext.servletContext.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.servletContext.contextPath}/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

</body>
</html>