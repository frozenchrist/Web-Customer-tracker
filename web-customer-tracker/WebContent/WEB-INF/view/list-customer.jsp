<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer List</title>

<link type="text/css" 
rel="stylesheet" 
href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h2><a href="list">CRM - Customer Realationship Manager</a></h2>
		</div>
	</div>
	
	
	
	<div id="container">
		<div id="content">
		
		<input type="button" value="Add Customer"
		 onclick="window.location.href='addCustomerForm'; return false;" 
		 class="add-button" />
		
			<form:form action="searchCustomer" method="GET">
			
				Search customer: <input type="text" name="searchName"/>
				<input type="submit" value="Search" class="add-button" />
			
			</form:form>
		
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
				
				<c:forEach var="tempCustomer" items="${customerList}">
				
					<!-- display the update link and forward a id parameter-->
					<c:url var="updateLink" value="/customer/updateCustomerForm">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
				
					<tr>
						<td> ${tempCustomer.firstName } </td>
						<td> ${tempCustomer.lastName } </td>
						<td> ${tempCustomer.email } </td>
						<td> 
							<a href="${updateLink }">Update</a> | 
						
						<!-- Common way to add a JavaScript pop up box -->
							<a href="${deleteLink }" 
							onclick="if (!(confirm('Are you sure you want to delete this customer?')))
								return false">Delete</a> 
						</td>
					</tr>
				
				</c:forEach>
				
				
			</table>
		</div>
	</div>


</body>
</html>