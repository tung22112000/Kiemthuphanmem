<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<center>
		<p>Kết quả tìm kiếm cho '${search}' - có ${size} kết quả tìm kiếm
			được</p>
	</center>
	<div class="container">
		<c:set var="searchUrl"
			value="${pageContext.request.contextPath}/admin/publisher/search" />
		<form:form method="POST" action="${searchUrl}">
			<div class="row">
				<div class="col-sm-5">
					<input class="form-control" type="text" name="search"
						placeholder="Nhập tên cần tìm" aria-label="Search">
				</div>
				<div class="btn-group">
					<button type="submit" class="btn btn-primary"><i class="fas fa-search" width="auto"></i> Tìm kiếm</button>
					<button type="reset"  class="btn btn-light" value="Reset"><a href="/admin/publisher/"><i class="fas fa-undo" ></i> Trở lại</a></button>
				</div>

			</div>
		</form:form>
	</div>

	<c:choose>
		<c:when test="${ketqua}">
			<div class="table-responsive">
				<table class="table table-striped table-sm table-hover">
					<thead>
						<tr class="table-info">
							<th style="text-align: center">ID</th>
							<th>Tên</th>
							<td>Logo</td>
							<th>SĐT</th>
							<th>Địa chỉ</th>
							<th>Email</th>
							<th style="text-align: center">Sửa</th>
							<th style="text-align: center">Xóa</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${publishers}" var="publisher">
							<tr>
								<td style="text-align: center"><a
									href="<c:url value='/admin/publisher/getPublisher/${publisher.id}?mode=VIEW' />"><i
										class="fas fa-eye"></i></a></td>
								<td>${publisher.publisherName}</td>
								<td>${publisher.logo}</td>
								<td>${publisher.phoneNumber}</td>
								<td>${publisher.street},${publisher.district},
									${publisher.city}</td>
								<td>${publisher.email}</td>
								<td style="text-align: center"><a
									href="<c:url value='/admin/publisher/getPublisher/${publisher.id}?mode=EDIT' />"><i
										class="far fa-edit"></i></a></td>
								<td style="text-align: center"><a
									href="<c:url value='/admin/publisher/deletePublisher/${publisher.id}' />"><i
										class="fas fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>
		<c:otherwise>
			<h4>Không có kết quả tìm kiếm</h4>
		</c:otherwise>
	</c:choose>

</body>
</html>