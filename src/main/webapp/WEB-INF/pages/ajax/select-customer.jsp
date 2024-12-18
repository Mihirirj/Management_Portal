<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <title>Customer</title>
       <script type="text/javascript" src="<c:url value="/resources/js/dojo.js"/>" ></script>
       <script type="text/javascript" src="<c:url value="/resources/js/selectcustomer.js"/>" ></script>
</head>
<body>
       <form id='custSearchFrm'>
       <table>
              <tr>
                     <td>Customer Id</td>
                     <td><input id='custId' name='custId'></td>
              </tr>
              <tr>
                     <td>Company Name</td>
                     <td><input id='compName' name='compName'></td>
              </tr>
              <tr>
                     <td>Customer Last Name</td>
                     <td><input id='contactName' name='contactName'></td>
              </tr>
       </table>
       <br/>
       <input type="button" id='custSearchBtn' value='Search Customers'>
       </form>
       <div id="searchResults">
       Results will come here
       </div>
       <script type="text/javascript">
              require(["nwdojo/selectcustomer"],function(selCustObj){
              });
       </script>
</body>
</html>