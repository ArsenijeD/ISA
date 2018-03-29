<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Log in</title>
</head>
<body>
	<nav role="navigation">
		<ul>
			<li><a href="/">Home</a></li>
		</ul>
	</nav>

	<h1>Log in</h1>

	<p>Username: admin@admin password: admin</p>

	<form role="form" action="/login" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<div>
			<label for="email">Email address</label> <input type="email"
				name="email" id="email" required autofocus />
		</div>
		<div>
			<label for="password">Password</label> <input type="password"
				name="password" id="password" required />
		</div>
		<div>
			<label for="remember-me">Remember me</label> <input type="checkbox"
				name="remember-me" id="remember-me" />
		</div>
		<button type="submit">Sign in</button>
	</form>
</body>
</html>