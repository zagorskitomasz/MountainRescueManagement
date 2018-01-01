<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>${formTitle}</h1>
    		</div>
    	
    	<form:form class="form-horizontal" action="/operation/addRescuers" modelAttribute="operation" method="POST">
			<form:hidden path="id"/>
			<form:hidden path="state"/>
			<div class="form-group">
   				<label class="col-sm-2 control-label">Destination</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="destination"/>
      				<strong><form:errors path="destination" cssClass="error"/></strong>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Description</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="operationDetail.description"/>
      				<strong><form:errors path="operationDetail.description" cssClass="error"/></strong>
    			</div>
  			</div>
   			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-4">
    			<div class="btn-group" role="group" aria-label="...">
      				<input type="submit" class="btn btn-primary " value="Attach Rescuers"/>
      				<button type="button" onclick="location.href='/operation/all'" class="btn btn-default ">Cancel</button>
    			</div>
    			</div>
  			</div>
			</form:form>
			</div>
		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>