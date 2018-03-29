<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create a new user</title>
</head>
<body>
	<nav role="navigation">
		<ul>
			<li><a href="/">Home</a></li>
		</ul>
	</nav>

	<h1>Create a new user</h1>

	<form role="form" name="form" action="" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<div>
			<label for="email">Email address</label> <input type="email"
				name="email" id="email" value="${form.email}" required autofocus />
		</div>
		<div>
			<label for="password">Password</label> <input type="password"
				name="password" id="password" required />
		</div>
		<div>
			<label for="passwordRepeated">Repeat</label> <input type="password"
				name="passwordRepeated" id="passwordRepeated" required />
		</div>
		<div>
			<label for="role">Role</label> <select name="role" id="role" required>
				<option <c:if test="${form.role == 'USER'}">selected</c:if>>USER</option>
				<option <c:if test="${form.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
			</select>
		</div>
		<button type="submit">Save</button>
	</form>

</body>
</html>