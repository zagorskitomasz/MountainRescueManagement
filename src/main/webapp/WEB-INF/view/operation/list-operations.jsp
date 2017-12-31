<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>${listTitle}</h1>
    	</div>	
    		<div class="table-responsive">
  				<table class="table table-bordered table-striped">
    				<thead>
    					<tr>
    						<th>Destination</th>
    						<th>Description</th>
    						<th>State</th>
    						<th>Actions</th>
    					</tr>
    				</thead>
    				<tbody>
    				<c:forEach items="${operations}" var="operation">
    				
    				<c:url var="detailsLink" value="/operation/details">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
					
    				<c:url var="updateLink" value="/operation/update">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
					
    				<c:url var="deleteLink" value="/operation/deleteConfirmation">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
					
    				<c:url var="finishLink" value="/operation/finish">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
					
    					<tr>
    						<td>${operation.destination }</td>
    						<td>${operation.operationDetail.description }</td>
    						<td>${operation.state }</td>
    						
    						<td><div class="btn-group" role="group" aria-label="...">
    						<button type="button" class="btn btn-success " onclick="location.href='${detailsLink}'">Details</button>
    						<button type="button" class="btn btn-warning " onclick="location.href='${updateLink}'">Update</button>
    						<button type="button" class="btn btn-danger " onclick="location.href='${deleteLink}'">Delete</button>
    						</div>&nbsp;&nbsp;
    						<c:choose>
    							<c:when test="${operation.state=='Running' }">
    								<button type="button" class="btn btn-primary " onclick="location.href='${finishLink}'">Finish</button>
    							</c:when>
    						</c:choose>
    						</td>
    					</tr>
    				</c:forEach>
    				</tbody>
 				</table>
			</div>
    	</div>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>