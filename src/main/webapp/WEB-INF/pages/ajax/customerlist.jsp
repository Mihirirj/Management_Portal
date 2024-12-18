<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <script src='<c:url value="/dojo/dojo.js"/>' data-dojo-config="async: true"></script>
       <title>Customer List</title>
</head>
<body>
       <form action="showselected" method="post" id="custForm">
       <table>
              <thead>
                     <tr>
                           <th></th>
                           <th>Customer ID</th>
                           <th>Company Name</th>
                           <th>Contact Name</th>
                     </tr>
              </thead>
              <tbody>
                     <c:forEach items="${list}" var="cust" varStatus="loop">
                           <tr  id="record_${loop.index % 2}">
                                  <td><input type="checkbox" value="${cust.custId}" name='selectedCustId' id='selectedCustId'></td>
                                  <td><a href='show/${cust.custId}'>${cust.custId}</a></td>
                                  <td style="color:red">${cust.compName}</td>
                                  <td>${cust.contactName}</td>
                           </tr>
                     </c:forEach>
                     <tr>
                           <td>
                                  <input value="Edit" id="custEditBtn" type="button">
                           </td>
                           <td>
                                  <input value="Delete" id="custDelBtn" type="button">
                           </td>
                     </tr>
              </tbody>
       </table>
       </form>
       <script type="text/javascript">
    require([
        "nwdojo/customerlist"
    ], function(counterObj){
    });
       </script>
</body>
</html>