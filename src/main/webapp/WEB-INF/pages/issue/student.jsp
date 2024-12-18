<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form action="addStudent" method="post"  modelAttribute="person">
    <form:label path="student.firstName">First name</form:label>
    <form:input path="student.firstName" />
    <br />

<form:label path="student.lastName">Last name</form:label>
    <form:input path="student.lastName" />
    <br />
    
    <form:label path="subject.subjectCode">Subject Code</form:label>
    <form:input path="subject.subjectCode" />
    <br />
    
    <form:label path="subject.subjectName">Subject Name</form:label>
    <form:input path="subject.subjectName" />
    <br />
    
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>