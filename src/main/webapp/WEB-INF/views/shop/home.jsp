<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Trang chủ</title>


</head>

<body style="background-color:#EBFCF7">
	<div class="container">
		<c:set var="searchUrl"
			value="${pageContext.request.contextPath}/shop/search" />
		<form:form method="POST" action="${searchUrl}">
			<div class="row">
				<div class="col-sm">
					<input class="form-control" type="text" name="search"
						placeholder="Nhập tên cần tìm" aria-label="Search">
				</div>

				<div class="col-sm">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-search" width="auto"></i> Tìm kiếm
					</button>
				</div>
			</div>
		</form:form>
	</div>


	<div id="carouselExampleIndicators" class="carousel slide my-4"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class=""></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"
				class=""></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"
				class="active"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="carousel-item">
			<a href="<c:url value='/shop/getProduct/${productCode=1}?mode=VIEW' />">
				<img class="d-block img-fluid"
					src="<c:url value="../resources/img/java1.jpg" />" alt="First slide"></a>
			</div>
			<div class="carousel-item">
			<a href="<c:url value='/shop/getProduct/${productCode=2}?mode=VIEW' />">
				<img class="d-block img-fluid"
				
					src="<c:url value="../resources/img/java.png" />"
					alt="Second slide"></a>
			</div>
			<div class="carousel-item active">
			<a href="<c:url value='/shop/getProduct/${productCode=3}?mode=VIEW' />">
				<img class="d-block img-fluid"
					src="<c:url value="../resources/img/php1.jpg" />" alt="Third slide"></a>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	
	
 

	<div class="row">

		<c:forEach items="${products.getContent()}" var="p">
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-50" >
					<a
						href="<c:url value='/shop/getProduct/${p.productCode}?mode=VIEW' />"><img
						class="card-img-top"
						src="<c:url value="/uploads/${p.productImages}" />" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a
								href="<c:url value='/shop/getProduct/${p.productCode}?mode=VIEW' />">${p.productName}</a>
						</h4>
						<c:choose>
							<c:when test="${p.productNumber <=0}">
							<div style="color:red">Sách đã được thuê</div>
							<fieldset disabled>
							
					<a href="<c:url value='/shop/buyProduct?id=${p.productCode }' />"
						class="btn btn-primary">Thêm vào giỏ hàng</a>
						
					</fieldset>
						</c:when>
						<c:otherwise>
						<div>Số lượng: ${p.productNumber}</div>
					<a href="<c:url value='/shop/buyProduct?id=${p.productCode }' />"
						class="btn btn-primary">Thêm vào giỏ hàng</a>
	
						</c:otherwise>
						
						</c:choose>
						<!-- <div class="card-footer">
						<small class="text-muted">&#9733; &#9733; &#9733; &#9733;
							&#9734;</small>
					</div> -->
						
					</div>
					
					
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="row p-3">
		<div class="col">
			<c:if test="${products.totalPages > 0}">
				<util:pagination currentPage="${currentPage}"
					showPageTitle="Số sản phẩm mỗi trang" thispage="${products}"></util:pagination>
			</c:if>
		</div>
	</div>



</body>
</html>