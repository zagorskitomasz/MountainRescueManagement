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
    	
    	<form:form class="form-horizontal" action="/operation/save" modelAttribute="operation" method="POST">
			<form:hidden path="operation.id"/>
			<form:hidden path="operation.destination"/>
			<form:hidden path="operation.operationDetail.description"/>
			<form:hidden path="operation.state"/>
  			
  			<div class="form-group">
  			<div class="col-sm-6">
    	    	<div class="table-responsive">
  					<table class="table table-bordered table-striped">
    					<tbody>
							<tr>
								<td><p class="text-right">Destination</p></td>
								<td><Strong>${operation.operation.destination }</Strong></td>
							</tr>
							<tr>
								<td><p class="text-right">Description</p></td>
								<td><Strong>${operation.operation.operationDetail.description }</Strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
  			</div>
  			<div class="form-group">
  				<div class="alert alert-info" role="alert">
   					Select rescuers you want attach to operation:
  				</div>
  			</div>
  			<div class="form-group">
				<c:forEach items="${operation.candidatesMap }" var="candidate" varStatus="loop">
					<form:hidden path="candidatesMap['${loop.index }'].rescuer.id"/>
					<div class="form-group">
					<div class="col-sm-offset-2 col-sm-4">
					<div class="checkbox">
					<label>
						<form:checkbox class="checkbox" path="candidatesMap['${loop.index }'].attached"/> <Strong>${candidate.rescuer}</Strong>				
					</label>
					</div>
					</div>
					</div>
				</c:forEach>
				<div class="col-sm-offset-2 col-sm-4">
					<Strong><form:errors path="candidatesMap" cssClass="error"/></Strong>
				</div>
			</div>
   			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-4">
    			<div class="btn-group" role="group" aria-label="...">
      				<input type="submit" class="btn btn-success " value="Save Operation"/>
      				<button type="button" onclick="location.href='/operation/all'" class="btn btn-default ">Cancel</button>
    			</div>
    			</div>
  			</div>
			</form:form>
			</div>
		<jsp:include page="/WEB-INF/view/templates/footer.jsp"></jsp:include>
	</body>
</html>