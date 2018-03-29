<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Home page</title>
</head>
<body>
	<nav role="navigation">
		<ul>
			<c:if test="${empty currentUser}">
				<li><a href="/login">Log in</a></li>
			</c:if>
			<c:if test="${not empty currentUser}">
				<li>
					<form action="/logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button type="submit">Log out</button>
					</form>
				</li>
				<li><a href="/user/${currentUser.id}">View myself</a></li>
			</c:if>
			
			<c:if test="${not empty currentUser && currentUser.role.name == 'ADMIN'}">
				<li><a href="/user/create">Create a new user</a></li>
				<li><a href="/users">View all users</a></li>
			</c:if>
		</ul>
	</nav>
</body>
</html>