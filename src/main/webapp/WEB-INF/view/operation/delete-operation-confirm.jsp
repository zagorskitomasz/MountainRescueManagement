<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>Delete Operation Confirmation</h1>
    		</div>
    	</div>
    	<div class="container theme-showcase" role="main">
    <div class="alert alert-danger" role="alert">
         Do you want to delete operation <strong>${operation.destination}</strong>?
      </div>
      
          			<c:url var="listLink" value="/operation/all">
					</c:url>
					
    				<c:url var="deleteLink" value="/operation/delete">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
      
    <div class="btn-group" role="group" aria-label="...">
	<button type="button" onclick="location.href='${listLink}'" class="btn btn-default ">Cancel</button>
	<button type="button" onclick="location.href='${deleteLink}'" class="btn btn-danger ">Delete</button>
	</div>
	
</div>
		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>