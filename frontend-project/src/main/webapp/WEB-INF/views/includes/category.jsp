<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Category Navigation -->
<div class="col-lg-3" class="">
	<a href="${pageContext.servletContext.contextPath }" class="bshop-title"><h1 class="my-4" id="shop-title">BSHOP</h1></a>
	<div class="list-group">
		<c:forEach items="${categories}" var="c">
			<div class="list-group-item">
				<a href="${pageContext.servletContext.contextPath }/list/${c.no}"
					class="bg-category-name">${c.category_name}</a>
				<c:if test="${not empty c.sub_categories }">
					<span class="bg-drop-down" onclick="javascript:dropdown2(${c.no})"></span>
				</c:if>
			</div>
			<c:if test="${not empty c.sub_categories }">
				<div class="bg-wrap" data-parent="${c.no}" data-flag=false
					style="display: none;">
					<c:forEach items="${c.sub_categories}" var="sub1">
						<div class="list-group-item">
							<a
								href="${pageContext.servletContext.contextPath }/list/${sub1.no}"
								class="bg-category-name" style="margin-left: 20px;">${sub1.category_name}</a>
							<c:if test="${not empty sub1.sub_categories }">
								<span class="bg-drop-down"
									onclick="javascript:dropdown2(${sub1.no})"></span>
							</c:if>
						</div>
						<c:if test="${not empty sub1.sub_categories }">
							<div class="bg-wrap" data-parent="${sub1.no}" data-flag=false
								style="display: none;">
								<c:forEach items="${sub1.sub_categories}" var="sub2">
									<div class="list-group-item">
										<a
											href="${pageContext.servletContext.contextPath }/list/${sub2.no}"
											class="bg-category-name" style="margin-left: 40px;">${sub2.category_name}</a>
										<c:if test="${not empty sub2.sub_categories }">
											<span class="bg-drop-down"
												onclick="javascript:dropdown2(${sub2.no})"></span>
										</c:if>
									</div>
									<c:if test="${not empty sub2.sub_categories }">
										<div class="bg-wrap" data-parent="${sub2.no}" data-flag=false
											style="display: none;">
											<c:forEach items="${sub2.sub_categories}" var="sub3">
												<div class="list-group-item">
													<a
														href="${pageContext.servletContext.contextPath }/list/${sub3.no}"
														class="bg-category-name" style="margin-left: 60px;">${sub3.category_name}</a>
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
<!-- /.Navigation -->
