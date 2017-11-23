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
    		<h1>Login Page</h1>
    		</div>
    	</div>
    	
    	<c:url var="loginUrl" value="/login" />
    	<form:form class="form-horizontal" action="${loginUrl}" method="POST">

    		<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-4">
    				<button type="button" onclick="location.href='/registration'" class="btn btn-primary ">Create Account</button>
    			</div>
    		</div>
    		
			<div class="form-group">
   				<label class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-4">
      				<input class="form-control" id="email" type="text" name="email"/>
    			</div>
  			</div>
  			
			<div class="form-group">
   				<label class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-4">
      				<input class="form-control" id="password" type="password" name="password"/>
    			</div>
  			</div>
  			<div class="form-group">
  			<c:if test="${param.error}">
				<div class="col-sm-offset-2 col-sm-4">
					<p style="font-size: 20; color: #FF1C19;">Email or Password invalid, please try again ${param.error }</p>
				</div>
			</c:if>
			</div>
			<div class="form-group">
   				<div class="col-sm-offset-2 col-sm-4">
      				<button class="btn btn-success " name="Submit" value="Login" type="Submit">Login</button>
    			</div>
  			</div>
			
		</form:form>

		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>