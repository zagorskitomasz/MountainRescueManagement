<% response.setCharacterEncoding("UTF-8"); request.setCharacterEncoding("UTF-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
	<body>
		<jsp:include page="/WEB-INF/view/templates/navbar.jsp"></jsp:include>
    	<div class="container">
    	<div class="page-header">
    		<h1>${formTitle}</h1>
    		</div>
    	
    	<form:form class="form-horizontal" action="/operation/save" modelAttribute="operation" method="POST">
			<form:hidden path="id"/>
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
   				<label class="col-sm-2 control-label">Rescuer #1</label>
    			<div class="col-sm-4">
      						<form:select path="rescuers[0].id">
      						<form:option value="0">--Select--</form:option>
      						<form:options items="${candidates}" itemValue="id"/>
							</form:select>
    			</div>
  			</div>
  			<div class="form-group">
   				<label class="col-sm-2 control-label">Rescuer #2</label>
    			<div class="col-sm-4">
      						<form:select path="rescuers[1].id">
      						<form:option value="0">--Select--</form:option>
							<form:options items="${candidates}" itemValue="id"/>
							</form:select>
    			</div>
  			</div>
  						<div class="form-group">
   				<label class="col-sm-2 control-label">Rescuer #3</label>
    			<div class="col-sm-4">
      						<form:select path="rescuers[2].id">
      						<form:option value="0">--Select--</form:option>
							<form:options items="${candidates}" itemValue="id"/>
							</form:select>
							
    			</div>
  			</div>
  			<div class="form-group">
  				<div class="col-sm-offset-2 col-sm-4">
  					<strong><form:errors path="rescuers" cssClass="error"/></strong>
  				</div>
  			</div>
   			<div class="form-group">
   			
    			<div class="col-sm-offset-2 col-sm-4">
    			<div class="btn-group" role="group" aria-label="...">
      				<input type="submit" class="btn btn-success " value="Save"/>
      				<button type="button" onclick="location.href='/operation/all'" class="btn btn-default ">Cancel</button>
    			</div>
    			</div>
  			</div>
			</form:form>
			</div>
		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>