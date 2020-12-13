<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<div class="col-lg-3">

  <h1 class="my-4"><span class="category-icon-small"><i class="fas fa-align-justify"></i> Danh mục</span></h1>
  <div class="list-group">
  <c:forEach items="${categories}" var="category">
  
    <a href="<c:url value='/shop/getProductsByCategory?filter=${category.categoryCode}' />" class="list-group-item ">
    <span class="category-icon"> ${category.categoryImages} </span>
   
    
    ${category.categoryName}</a>
    

    </c:forEach>
  </div>
   <h2 class="my-4"><span class="category-icon-small"><i class="class='fas fa-building"></i> Nhà xuất bản</span></h2>
  <div class="list-group">
  <c:forEach items="${publishers}" var="publisher">
  
    <a href="<c:url value='/shop/getProductsByPublisher?filter=${publisher.id}' />" class="list-group-item ">
       
    ${publisher.publisherName}</a>
    

    </c:forEach>
  </div>

</div>