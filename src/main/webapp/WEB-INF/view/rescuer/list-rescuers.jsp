<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>Rescuers List</h1>
    	</div>	
    		<div class="table-responsive">
  				<table class="table table-bordered table-striped">
    				<thead>
    					<tr>
    						<th>First Name</th>
    						<th>Last Name</th>
    						<th>Phone</th>
    						<th>Actions</th>
    					</tr>
    				</thead>
    				<tbody>
    				<c:forEach items="${rescuers}" var="rescuer">
    				
    				<c:url var="detailsLink" value="/rescuer/details">
					<c:param name="rescuerId" value="${rescuer.id}" />
					</c:url>
					
    				<c:url var="updateLink" value="/rescuer/update">
					<c:param name="rescuerId" value="${rescuer.id}" />
					</c:url>
					
    				<c:url var="deleteLink" value="/rescuer/deleteConfirmation">
					<c:param name="rescuerId" value="${rescuer.id}" />
					</c:url>
					
    					<tr>
    						<td>${rescuer.firstName }</td>
    						<td>${rescuer.lastName }</td>
    						<td>${rescuer.rescuerDetail.phone }</td>
    						<td><div class="btn-group" role="group" aria-label="...">
    						<button type="button" class="btn btn-success active" onclick="location.href='${detailsLink}'">Details</button>
    						<button type="button" class="btn btn-warning active" onclick="location.href='${updateLink}'">Update</button>
    						<button type="button" class="btn btn-danger active" onclick="location.href='${deleteLink}'">Delete</button>
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