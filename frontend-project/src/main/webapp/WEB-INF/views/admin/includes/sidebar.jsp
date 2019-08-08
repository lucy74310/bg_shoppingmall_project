<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">관리자 페이지 </div>
      <div class="list-group list-group-flush">
      	<c:choose>
      		<c:when test='${param.active == "product-list" }'>
		        <a href="${pageContext.servletContext.contextPath }/manage/main" 
		        	class="list-group-item list-group-item-action bg-light bg-active" >상품관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/product/list" 
		        	class="list-group-item list-group-item-action bg-light bg-active-child child-active" >&nbsp;&nbsp;&nbsp;상품목록</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/product/register" 
		        	class="list-group-item list-group-item-action bg-light bg-active-child" >&nbsp;&nbsp;&nbsp;상품등록</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/category" 
		        	class="list-group-item list-group-item-action bg-light">카테고리관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/user" 
		        	class="list-group-item list-group-item-action bg-light">사용자관리</a>
        	</c:when>
        	<c:when test='${param.active == "product-register" }'>
		        <a href="${pageContext.servletContext.contextPath }/manage/main" 
		        	class="list-group-item list-group-item-action bg-light bg-active" >상품관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/product/list" 
		        	class="list-group-item list-group-item-action bg-light bg-active-child" >&nbsp;&nbsp;&nbsp;상품목록</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/product/register" 
		        	class="list-group-item list-group-item-action bg-light bg-active-child child-active" >&nbsp;&nbsp;&nbsp;상품등록</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/category" 
		        	class="list-group-item list-group-item-action bg-light">카테고리관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/user" 
		        	class="list-group-item list-group-item-action bg-light">사용자관리</a>
        	</c:when>
        	<c:when test='${param.active == "user-list" }'>
        		<a href="${pageContext.servletContext.contextPath }/manage/main" 
        			class="list-group-item list-group-item-action bg-light" >상품관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/category" 
		        	class="list-group-item list-group-item-action bg-light">카테고리관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/user"  
		        	class="list-group-item list-group-item-action bg-light bg-active">사용자관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/user/list" 
		        	class="list-group-item list-group-item-action bg-light bg-active-child child-active">&nbsp;&nbsp;&nbsp;사용자목록</a>
        	</c:when>
        	<c:otherwise>
		        <a href="${pageContext.servletContext.contextPath }/manage/main" 
		        	class="list-group-item list-group-item-action bg-light" >상품관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/category" 
		        	class="list-group-item list-group-item-action bg-light bg-active">카테고리관리</a>
		        <a href="${pageContext.servletContext.contextPath }/manage/user" 
		        	class="list-group-item list-group-item-action bg-light">사용자관리</a>
        	</c:otherwise>
        </c:choose>
      </div>
    </div>