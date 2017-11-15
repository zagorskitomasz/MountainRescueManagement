<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    		<h2>Rescuer Form</h2>
    	</div>
    	
    	<form:form class="form-horizontal" action="/rescuer/save" modelAttribute="rescuer" method="POST">
			<form:hidden path="id"/>
			<div class="form-group">
   				<label class="col-sm-2 control-label">First Name</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="firstName"/>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Last Name</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="lastName"/>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Address</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="rescuerDetail.address"/>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Phone</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="rescuerDetail.phone"/>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="rescuerDetail.email"/>
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
      				<input type="submit" class="btn btn-success" value="Save"/>
    			</div>
  			</div>

		</form:form>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>