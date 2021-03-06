<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Mountain Rescue Management</title>

<spring:url value="/resources/static/css/bootstrap.min.css"
	var="bootstrapMinUrl"></spring:url>
<spring:url value="/resources/static/css/mytemplate.css"
	var="mytemplateUrl"></spring:url>

<!-- Bootstrap -->
<link href="${bootstrapMinUrl }" rel="stylesheet">
<link href="${mytemplateUrl }" rel="stylesheet">

<style>
.error {
	color: red
}
</style>
</head>