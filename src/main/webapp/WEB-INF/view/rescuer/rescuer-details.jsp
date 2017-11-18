<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>Rescuer Details</h1>
    		</div>
    		<div class="col-sm-6">
    	    		<div class="table-responsive">
  				<table class="table table-bordered table-striped">
    				<tbody>
						<tr>
							<td><p class="text-right">First Name</p></td>
							<td><Strong>${rescuer.firstName}</Strong></td>
						</tr>
						<tr>
							<td><p class="text-right">Last Name</p></td>
							<td><Strong>${rescuer.lastName}</Strong></td>
						</tr>
						<tr>
							<td><p class="text-right">Operations</p></td>
							<td><Strong><span class="badge">${fn:length(rescuer.operations)}</span> </Strong>
							
							<c:url var="operationsLink" value="/rescuer/operations">
							<c:param name="rescuerId" value="${rescuer.id}" />
							</c:url>
							<button type="button" class="btn btn-sm btn-success active" onclick="location.href='${operationsLink}'">Show</button>
    						</td>
						</tr>
						<tr>
							<td><p class="text-right">Address</p></td>
							<td><Strong>${rescuer.rescuerDetail.address}</Strong></td>
						</tr>
						<tr>
							<td><p class="text-right">Phone</p></td>
							<td><Strong>${rescuer.rescuerDetail.phone}</Strong></td>
						</tr>
						<tr>
							<td><p class="text-right">Email</p></td>
							<td><Strong>${rescuer.rescuerDetail.email}</Strong></td>
						</tr>
						
    				</tbody>
 				</table>
 				    <c:url var="backLink" value="/rescuer/all">
					</c:url>
					
    				<c:url var="updateLink" value="/rescuer/update">
					<c:param name="rescuerId" value="${rescuer.id}" />
					</c:url>
					
    				<c:url var="deleteLink" value="/rescuer/deleteConfirmation">
					<c:param name="rescuerId" value="${rescuer.id}" />
					</c:url>
					
    					
    						<div class="btn-group" role="group" aria-label="...">
    						<button type="button" class="btn btn-primary active" onclick="location.href='${backLink}'">Back</button>
    						<button type="button" class="btn btn-warning active" onclick="location.href='${updateLink}'">Update</button>
    						<button type="button" class="btn btn-danger active" onclick="location.href='${deleteLink}'">Delete</button>
    						</div>
			</div>
			</div>
    	</div>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>