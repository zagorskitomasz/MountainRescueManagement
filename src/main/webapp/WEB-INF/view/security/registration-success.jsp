<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>Account Created</h1>
    		</div>
    	</div>
    	<div class="container theme-showcase" role="main">
    <div class="alert alert-success" role="alert">
         Your account has been successfully created.
      </div>
      
          			<c:url var="loginLink" value="/login">
					</c:url>
      
	<button type="button" onclick="location.href='${loginLink}'" class="btn btn-primary ">Go to Login Page</button>
	

</div>
		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>