<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    		<h1>Rescuers List</h1>
    		
    		<div class="table-responsive">
  				<table class="table table-bordered table-striped">
    				<thead>
    					<tr>
    						<th>First Name</th>
    						<th>Last Name</th>
    						<th>State</th>
    						<th colspan="2">Actions</th>
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
					
    					<tr>
    						<td>${rescuer.firstName }</td>
    						<td>${rescuer.lastName }</td>
    						<td>${rescuer.state }</td>
    						<td><button type="button" class="btn btn-success" onclick="location.href='${detailsLink}'">Details</button></td>
    						<td><button type="button" class="btn btn-info" onclick="location.href='${updateLink}'">Update</button></td>
    					</tr>
    				</c:forEach>
    				</tbody>
 				</table>
			</div>
    	</div>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>