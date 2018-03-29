<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Users</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Create a new user</a></li>
    </ul>
</nav>

<h1>List of Users</h1>

<table>
    <thead>
    <tr>
        <th>E-mail</th>
        <th>Role</th>
    </tr>
    <tbody>
    <c:forEach items="${ users }"  var="user">
    <tr>
        <td><a href="/user/${user.id}">${user.email}</a></td>
        <td>
        	<table>
    			<c:forEach items="${ user.roles }"  var="role">
   					 <tr>
        				<td>${role.name}</td>
    				</tr>
    		</c:forEach>
    		</table>	
        
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>