<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Spring 3 MVC Multipe Row Submit</title>
<style>
body {
	font-family: Calibri;
}
table {
	border: 1px solid;
	border-collapse: collapse;
}
td {
	border: 1px solid;
}
th {
	background: #ffbb00 solid;
}
</style>
</head>
<body>

<h2>Spring MVC Multiple Row Form Submit example</h2>
<form:form method="post" action="addContact" modelAttribute="contactForm">
	<table>
	<tr>
		<td colspan="5">
		
    <form:label path="student.firstName">First name</form:label>
    <form:input path="student.firstName" />
    <br />

<form:label path="student.lastName">Last name</form:label>
    <form:input path="student.lastName" />
    <br />
		</td>
	</tr>
	<tr>
		<th>No.</th>
		<th>Name</th>
		<th>Lastname</th>
		<th>Email</th>
		<th>Phone</th>
	</tr>
	<c:forEach items="${contactForm.contacts}" var="contact" varStatus="status">
		<tr>
			<td align="center">${status.count}</td>
			<td>${contact.firstname}</td>
			<td>${contact.lastname}</td>
			<td>${contact.email}</td>
			<td><input name="contacts[${status.index}].phone" value="${contact.phone}"/></td>
		</tr>
	</c:forEach>
</table>	
<br/>
<input type="submit" value="Save" />
	
</form:form>
</body>
</html>
