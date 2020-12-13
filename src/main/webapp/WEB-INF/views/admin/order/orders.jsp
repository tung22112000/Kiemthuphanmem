<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<html>
<head>
<script language="javascript" src="/resources/js/order.js"></script>
<script src="http://momentjs.com/downloads/moment-with-locales.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.0.min.js"></script>
<script src="http://momentjs.com/downloads/moment-timezone-with-data.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tất cả hóa đơnr</title>

<script>
	$(document).ready(function() {
		$("input[value='hien']").click(function() {
			$("#div1").show();
			$("#div2").hide();
		})
		$("input[value='an']").click(function() {
			$("#div1").hide();
			$("#div2").show();
		})
	})
</script>
<script>
	$(document).ready(function() {
		$('#div2').hide();
	})
</script>
</head>


<body>
	<center>
		<h2><spring:message code="admin.order.title"></spring:message></h2>
	</center>

	<p>
		<input type="radio" name="abc" value="hien" checked> <spring:message code="admin.order.search1"></spring:message>
	</p>
	<p>
		<input type="radio" name="abc" value="an"> <spring:message code="admin.order.search2"></spring:message>
	</p>
	<div class="container">
		<!-- 		<div class="col-sm-1"></div> -->
		<form method="POST" action="searchByFromDateAndToDate">
			<div class="form-group row" id="div1">
				<label for="fromDate" class="col-xs-2 col-form-label"><b><i><spring:message code="admin.order.from"></spring:message></i></b></label>
				<div class="col-sm-2">
					<input type="date" class="form-control" name="fromDate"
						id="fromDate" placeholder="fromDate" required>
				</div>

				<label for="toDate" class="col-xs-2 col-form-label"> <b><i><spring:message code="admin.order.to"></spring:message></i></b>
				</label>
				<div class="col-sm-2">
					<input type="date" class="form-control" name="toDate" id="toDate"
						placeholder="toDate" required>
				</div>

				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-search" width="auto"></i> <spring:message code="admin.order.search"></spring:message>
					</button>
				</div>
			</div>
		</form>
	</div>
	<div class="container">
		<!-- 	<div class="col-sm-1"></div> -->
		<form method="POST" action="searchOrderByDatesAndTime">

			<div class="form-group row" id="div2">
				<label for="Hour" class="col-xs-1 col-form-label"><b><i><spring:message code="admin.order.date"></spring:message></i></b></label>
				<div class="col-sm-2">
					<input type="date" class="form-control" name="Date" id="Date"
						placeholder="Date" required>
				</div>

				<label for="Hour" class="col-xs-1 col-form-label"><b><i><spring:message code="admin.order.hour"></spring:message></i></b></label>
				<div class="col-sm-1">
					<input type="number" class="form-control" name="Hour" min="0"
						max="24" id="Hour" required>
				</div>
				<label for="Minute" class="col-xs-1 col-form-label"><b><i><spring:message code="admin.order.minute"></spring:message></i></b></label>
				<div class="col-sm-1">
					<input type="number" class="form-control" name="Minute" min="0"
						max="59" id="Minute" required>
				</div>

				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-search" width="auto"></i> <spring:message code="admin.order.search"></spring:message>
					</button>
				</div>
			</div>
		</form>


	</div>


	<div class="table-responsive">
		<table class="table table-striped table-sm table-hover">
			<thead>
				<tr class="table-info">
					<th style="text-align: center"><spring:message code="admin.order.view"></spring:message></th>
					<th style="text-align: center"><spring:message code="admin.order.code"></spring:message></th>
					<th><spring:message code="admin.order.name"></spring:message></th>
					<th><spring:message code="admin.order.created"></spring:message></th>
					<th><spring:message code="admin.order.address"></spring:message></th>
					<th><spring:message code="admin.order.email"></spring:message></th>
					<th><spring:message code="admin.order.status"></spring:message></th>
					<%-- <sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER')"> --%>
					<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER','EMPLOYEE')">
						<th><spring:message code="admin.order.edit"></spring:message></th>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER')">
						<th style="text-align: center"><spring:message code="admin.order.delete"></spring:message></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders.getContent()}" var="order">
					<tr>
						<td style="text-align: center"><a
							href="<c:url value='/admin/order/getOrder/${order.id}?mode=VIEW' />"><i
								class="fas fa-eye"></i></a></td>
						<td style="text-align: center">${order.id }</td>
						<td>${order.customer.lastName}${order.customer.firstName}</td>
						<td><fmt:formatDate type="date" value="${order.orderDate}" /></td>
						<td>${order.customer.street},${order.customer.district},
							${order.customer.city}</td>
						<td>${order.customer.email}</td>
						<td><c:if test="${order.status == 0}">
								<p>
									<spring:message code="admin.order.status.0"></spring:message>
								<p>
							</c:if> <c:if test="${order.status == 1}">
								<p>
									<spring:message code="admin.order.status.1"></spring:message>
								<p>
							</c:if> <c:if test="${order.status == 2}">
								<p>
									<spring:message code="admin.order.status.2"></spring:message>
								<p>
							</c:if><c:if test="${order.status == 3}">
								<p>
									<spring:message code="admin.order.status.3"></spring:message>
								<p>
							</c:if></td>

						<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER','EMPLOYEE')">

							<td style="text-align: center"><a
								href="<c:url value='/admin/order/getOrder/${order.id}?mode=EDIT' />"><i
									class="far fa-edit"></a></td>
									</sec:authorize>
									<sec:authorize access="hasAnyRole('SUPPER_ADMIN','MANAGER')">
							<td style="text-align: center"><a
								href="<c:url value='/admin/order/deleteOrder/${order.id}' />">
									<i class="fas fa-trash-alt"></i>
							</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<c:if test="${orders.totalPages > 0}">
		<util:pagination currentPage="${currentPage}" filter="${filter}"
			showPageTitle="Hóa đơn / Trang" thispage="${orders}"></util:pagination>
	</c:if>
	<br />
</body>
</html>