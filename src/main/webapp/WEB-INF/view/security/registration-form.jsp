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
    		<h1>Create Account</h1>
    		</div>
    	</div>
    	
    	<c:url var="registerUrl" value="/register" />
    	<form:form autocomplete="off" class="form-horizontal" modelAttribute="user" action="${registerUrl}" method="POST">
			<form:hidden path="id"/>
    		
    		<div class="form-group">
   				<label class="col-sm-2 control-label">Name</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="name"/>
      				<strong><form:errors path="name" cssClass="error"/></strong>
    			</div>
  			</div>
    		
			<div class="form-group">
   				<label class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="email"/>
      				<strong><form:errors path="email" cssClass="error"/></strong>
    			</div>
  			</div>
  			
			<div class="form-group">
   				<label class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" type="password" path="password"/>
      				<strong><form:errors path="password" cssClass="error"/></strong>
    			</div>
  			</div>
			<div class="form-group">
   				<div class="col-sm-offset-2 col-sm-4">
      				<button class="btn btn-success " name="Submit" type="Submit">Create Account</button>
    			</div>
  			</div>
  			
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-4">
    				<button type="button" onclick="location.href='/login'" class="btn btn-default ">Log In</button>
    			</div>
    		</div>
			
		</form:form>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>