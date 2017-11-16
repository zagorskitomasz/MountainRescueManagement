<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>${formVersion} Rescuer Form</h1>
    		</div>
    	</div>
    	
    	<form:form class="form-horizontal" action="/rescuer/save" modelAttribute="rescuer" method="POST">
			<form:hidden path="id"/>
			<div class="form-group">
   				<label class="col-sm-2 control-label">First Name</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="firstName"/>
      				<strong><form:errors path="firstName" cssClass="error"/></strong>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Last Name</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="lastName"/>
      				<strong><form:errors path="lastName" cssClass="error"/></strong>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Address</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="rescuerDetail.address"/>
      				<strong><form:errors path="rescuerDetail.address" cssClass="error"/></strong>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Phone</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="rescuerDetail.phone"/>
      				<strong><form:errors path="rescuerDetail.phone" cssClass="error"/></strong>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="rescuerDetail.email"/>
      				<strong><form:errors path="rescuerDetail.email" cssClass="error"/></strong>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">State</label>
    			<div class="col-sm-4">
      						<form:select path="state">
								<form:options items="${states}"/>
							</form:select>
    			</div>
  			</div>
   			<div class="form-group">
   			
    			<div class="col-sm-offset-2 col-sm-4">
    			<div class="btn-group" role="group" aria-label="...">
      				<input type="submit" class="btn btn-success active" value="Save"/>
      				<button type="button" onclick="location.href='/rescuer/all'" class="btn btn-default active">Cancel</button>
    			</div>
    			</div>
  			</div>

		</form:form>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>