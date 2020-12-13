<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu</title>


</head>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<sec:authorize access="hasRole('SUPPER_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/admin/user/"> <i
						class="far fa-address-book"></i> <span data-feather="file"></span>
						<spring:message code="admin.user"></spring:message>
				</a></li>
			</sec:authorize>



			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/order/"> <i
					class="fas fa-file-alt"></i> <span data-feather="file"></span> <spring:message
						code="admin.order"></spring:message>
			</a></li>


			<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER')">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/admin/product/"> <i
						class="fab fa-product-hunt"></i> <span
						data-feather="shopping-cart"></span> <spring:message code="admin.book"></spring:message>
				</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER')">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/admin/category/"> <i
						class="fas fa-align-justify"></i> <span
						data-feather="shopping-cart"></span>
<spring:message code="admin.category"></spring:message>
				</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER')">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/admin/publisher/"> <i
						class='fas fa-building'></i> <span data-feather="shopping-cart"></span>
						<spring:message code="admin.publisher"></spring:message>
				</a></li>
			</sec:authorize>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/customer/"> <i
					class="fas fa-user-plus"></i> <span data-feather="users"></span>
					<spring:message code="admin.customer"></spring:message>
			</a></li>


		</ul>
	</div>
</nav>