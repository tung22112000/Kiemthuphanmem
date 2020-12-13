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

<body style="background-color: #EBFCF7">
	<c:choose>
		<c:when test="${ketqua}">
			<div class="p-3">
				<div>Kết quả tìm kiếm cho '${search}': ${size} kết quả tìm
					kiếm được</div>
			</div>
			<div class="row">
				<c:forEach items="${products}" var="p">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a
								href="<c:url value='/shop/getProduct/${p.productCode}?mode=VIEW' />"><img
								class="card-img-top"
								src="<c:url value="/uploads/${p.productImages}" />" alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a
										href="<c:url value='/shop/getProduct/${p.productCode}?mode=VIEW' />">${p.productName}</a>
								</h4>
								<fmt:setLocale value="vi_VN" scope="session" />
								<div class="price">
									<fmt:formatNumber type="currency" value="${p.newPrice}"></fmt:formatNumber>
									<c:if test="${p.discount !=0 }">
										<span class="discount"> <fmt:formatNumber
												value="${- p.discount}" />%
										</span>
									</c:if>

									<span class="oldprice"> <fmt:formatNumber
											type="currency" value="${p.oldPrice}" />
									</span>
								</div>
								<c:choose>
									<c:when test="${p.productNumber <=0}">
										<div style="color: red">Đã hết hàng</div>
										<fieldset disabled>

											<a
												href="<c:url value='/shop/buyProduct?id=${p.productCode }' />"
												class="btn btn-primary">Thêm vào giỏ hàng</a>

										</fieldset>
									</c:when>
									<c:otherwise>
										<div>Số lượng: ${p.productNumber}</div>
										<a
											href="<c:url value='/shop/buyProduct?id=${p.productCode }' />"
											class="btn btn-primary">Thêm vào giỏ hàng</a>

									</c:otherwise>

								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<h4>Không có kết quả tìm kiếm</h4>
		</c:otherwise>
	</c:choose>





</body>
</html>