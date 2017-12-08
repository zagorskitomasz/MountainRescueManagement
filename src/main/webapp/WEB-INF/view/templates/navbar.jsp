<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	 <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/home">Home</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Show Rescuers<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/rescuer/all">All</a></li>
              </ul>
            </li>
            <li><a href="/rescuer/add">Add Rescuer</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Show Operations<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/operation/all">All</a></li>
              </ul>
            </li>
            <li><a href="/operation/create">Create Operation</a></li>
          
        </ul>
        <div class="navbar-right">
        	<sec:authorize access="isAnonymous()">
        		<button type="button" onclick="location.href='/login'" class="btn btn-primary navbar-btn">Log In</button>
        	</sec:authorize>
        	<sec:authorize access="isAuthenticated()">
        		<button type="button" onclick="location.href='/logout'" class="btn btn-default navbar-btn">Log Out</button>
      		</sec:authorize>
        </div>
          <div class="collapse navbar-collapse navbar-right">
          	<p class="navbar-text">Hello <Strong>${loggedUserName}</Strong>!</p>
          </div>
        </div>
      </div>
    </nav>