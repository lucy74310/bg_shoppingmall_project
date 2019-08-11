<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">&nbsp;</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<sec:authorize access="isAnonymous()">
					<c:choose>
						<c:when test='${param.active == "login" }'>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }">홈</a></li>
							<li class="nav-item active"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }/loginUser">로그인<span
									class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }/joinUser">회원가입</a>
							</li>
						</c:when>
						<c:when test='${param.active == "join" }'>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }">홈</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }/loginUser">로그인</a>
							</li>
							<li class="nav-item active"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }/joinUser">회원가입<span
									class="sr-only">(current)</span></a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item active"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }">홈<span
									class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }/loginUser">로그인</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.servletContext.contextPath }/joinUser">회원가입</a>
							</li>
						</c:otherwise>
					</c:choose>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item active"><a class="nav-link"
						href="${pageContext.servletContext.contextPath }">홈<span
									class="sr-only">(current)</span></a></li>
					<li class="nav-item "><a class="nav-link"
						href="${pageContext.servletContext.contextPath }/user_logout">로그아웃</span></a></li>					
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>