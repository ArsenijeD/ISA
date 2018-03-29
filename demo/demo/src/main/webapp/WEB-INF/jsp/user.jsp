<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>User details</title>
</head>
<body>
	<nav role="navigation">
		<ul>
			<li><a href="/">Home</a></li>
		</ul>
	</nav>

	<h1>User details</h1>

	<p>E-mail: ${user.email}</p>
	<table>
    		<c:forEach items="${ user.roles }"  var="role">
   					 <tr>
        				<td>${role.name}</td>
    				</tr>
    		</c:forEach>
    </table>	
	
</body>
</html>