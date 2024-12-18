<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:select id="cmbArea"  path="PivDetail.paidBranch"    >
    <form:option value="-1" label="--- Select Area ---"/>
    <c:forEach var="list" items="${pivModel.bankBranchList}">
       <option id="${list.key}" value="${list.key}">${list.value}</option>   
   </c:forEach>
    
</form:select>