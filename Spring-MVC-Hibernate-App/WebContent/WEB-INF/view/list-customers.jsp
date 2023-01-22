<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer List</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM-Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!-- Add Button for adding customer -->

			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<form:form class="example" action="searchCustomer" method="get">
			<label>Search Customer:</label>
				<input type="text" placeholder="Search.." name="search">
				<button type="submit" class="save">Search</button>
			</form:form>



			<table>

				<tr>

					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="customer" items="${customers}">

					<c:url var="updateLink" value="/customer/showFormForUpdate">

						<c:param name="customerId" value="${customer.id}" />
					</c:url>
					<c:url var="deleteLink" value="/customer/formForDelete">

						<c:param name="customerId" value="${customer.id}" />
					</c:url>

					<tr>
						<td>${customer.firstName }</td>
						<td>${customer.lastName }</td>
						<td>${customer.email }</td>
						<td><a href="${updateLink}">Update</a> | <a
							href="${deleteLink}"
							onclick="return confirm('Are you sure you want to delete this item?');">Delete
						</a></td>
					</tr>

				</c:forEach>

			</table>
		</div>
	
	<div style="clear; both"></div>
		
	
 	 	<p>
 	 		 <a href="${pageContext.request.contextPath}/customer/list">full List</a>
 	 		</p>
 	</div>
 
</body>
</html>