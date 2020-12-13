<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Chi tiết sản phẩm</title>

</head>



<body style="background-color:#EBFCF7">
	<div class="card mb-3" style="max-width: 540px;">
		<div class="row no-gutters">
			<div class="col-md-4">

				<img class="card-img"
					src="<c:url value="/uploads/${product.productImages}" />" alt="">
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h3 class="card-title">${product.productName}</h3>					
					<h3 class="card-title" style="font-size:18px">${product.publisher.publisherName}</h3>
					<h3 class="card-title" style="font-size:18px">Năm xuất bản: ${product.yearManufactured}</h3>
					<fmt:setLocale value="vi_VN" scope="session" />
					
					<c:if test="${product.productNumber>0}">
					<p class="card-text">Số lượng: ${product.productNumber}</p>
					</c:if>
					<p class="card-text">${product.description}</p>
					<c:choose>
						<c:when test="${product.productNumber <=0}">
							<div style="color:red">Đã hết hàng</div>
							
						</c:when>
						<c:otherwise>
							<a
								href="<c:url value='/shop/buyProduct?id=${product.productCode }' />"
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
	</div>
	

	
	
	
</body>
</html>