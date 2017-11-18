<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>Operation Details</h1>
    		</div>
    	
    		<div class="col-sm-6">
    	    		<div class="table-responsive">
  				<table class="table table-bordered table-striped">
    				<tbody>
						<tr>
							<td><p class="text-right">Destination</p></td>
							<td><Strong>${operation.destination}</Strong></td>
						</tr>
						<tr>
							<td><p class="text-right">Description</p></td>
							<td><Strong>${operation.operationDetail.description}</Strong></td>
						</tr>
						
    				</tbody>
 				</table>
 				    <c:url var="backLink" value="/operation/all">
					</c:url>
					
    				<c:url var="updateLink" value="/operation/update">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
					
    				<c:url var="deleteLink" value="/operation/deleteConfirmation">
					<c:param name="operationId" value="${operation.id}" />
					</c:url>
					
    					
    						<div class="btn-group" role="group" aria-label="...">
    						<button type="button" class="btn btn-primary active" onclick="location.href='${backLink}'">Back</button>
    						<button type="button" class="btn btn-warning active" onclick="location.href='${updateLink}'">Update</button>
    						<button type="button" class="btn btn-danger active" onclick="location.href='${deleteLink}'">Delete</button>
    						</div>
			</div>
			</div>
		<h3>Attached Rescuers</h3>
   		<div class="table-responsive">
  				<table class="table table-bordered table-striped">
    				<thead>
    					<tr>
    						<th>First Name</th>
    						<th>Last Name</th>
    						<th>Actions</th>
    					</tr>
    				</thead>
    				<tbody>
    				<c:forEach items="${operation.rescuers}" var="rescuer">
    				<c:url var="detailsLink" value="/rescuer/details">
					<c:param name="rescuerId" value="${rescuer.id}" />
					</c:url>
					
    					<tr>
    						<td>${rescuer.firstName }</td>
    						<td>${rescuer.lastName }</td>
    						<td><div class="btn-group" role="group" aria-label="...">
    						<button type="button" class="btn btn-success active" onclick="location.href='${detailsLink}'">Details</button>
    					</div></td>
    					</tr>
    				</c:forEach>
    				</tbody>
 				</table>
			</div>
			</div>
		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>