<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Main Menu</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>"/>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>


<script>
function display_acct_codes() 
{
	dojo.event.topic.publish("display_acct_codes");
}


function crunchifyAjax() {
    $.ajax({
        url : 'ajaxtest.html',
        success : function(data) {
            $('#result').html(data);
        }
    });
}

function getPivTypes() {
    $.ajax({
    	type: "POST",
        url : 'viewPivType',
        data: $("#pivForm").serialize(),
        success : function(data) {
            $('#result').html(data);
        }
    });
    e.preventDefault();
}



</script>
</head>

<body>
	<body>
    	<%@ include file="../admin/header.jsp" %>
    	<%@ include file="../admin/menuv.jsp" %>
        <div class="tblBody">
        	<form:form id="pivForm" method="post" action="savePivDetail" modelAttribute="pivModel">
        	<table border="0" >
        		<c:if test="${not empty pivModel.message}">
    				<tr>
	        			<td colspan="2" class="msgSuccess" align="center">
	        				<c:set var="msgType" value="${pivModel.messageType}"/>  
        					<c:if test="${msgType =='ERROR'}">
	        					<div class="msgError"><c:out value="${pivModel.message}"></c:out></div>
	        				</c:if>
	        				<c:if test="${msgType =='SUCCESS'}">
	        					<div class="msgSuccess"><c:out value="${pivModel.message}"></c:out></div>
	        				</c:if>
	        				<c:if test="${msgType =='INFO'}">
	        					<div class="msgInfo"><c:out value="${pivModel.message}"></c:out></div>
	        				</c:if>
	        			</td>
	        		</tr>
        		</c:if>
        		<tr>
        			<td width="40%">
        				<div class="formLayout">
        					<form:hidden path="mode" />
        					<c:set var="myVar" value="${pivModel.mode}"/>  
        					<c:if test="${myVar !='NEW'}">
        					
							<form:label path="pivDetail.id.pivNo">PIV No.@@@</form:label>
        					<form:select path="pivDetail.id.pivNo" onchange="javascript:getPivTypes()">
							    <form:option value="" label="- Select -"/>
							    <form:options items="${pivModel.pivNoList}" />
							</form:select><br>
							
							</c:if>
							
        					<form:label path="pivDetail.titleCd">PIV Type</form:label>
        					<form:select disabled="true" path="pivDetail.titleCd" onchange="javascript:getPivTypes()">
							    <form:option value="" label="- Select -"/>
							    <form:options items="${pivModel.pivTypeList}" />
							</form:select><br>
							
						
							<form:label path="pivDetail.referenceNo">Reference No</form:label>
							<form:input  path="pivDetail.referenceNo" readonly="true" /><br>
						
						 	

							<form:label path="pivApplicant.idNo">ID</form:label>
							<form:input path="pivApplicant.idNo" readonly="true" /><br>
						
							<form:label path="pivApplicant.name">Customer Name</form:label>
							<form:input path="pivApplicant.name" readonly="true" /><br>
						
							<form:label path="pivApplicant.address">Address</form:label>
							<form:input path="pivApplicant.address" readonly="true" /><br>
						
							
							
						
							
							

							
						</div>
        			</td>
        			<td width="60%" valign="top">
	        			<div class="formLayout">
	        				<form:label path="pivDetail.pivDate">Issued Date</form:label>
							<form:input path="pivDetail.pivDate" cssClass="date-picker"  readonly="true"></form:input>
							<br>
							<form:label path="pivDetail.expiryDate">Expiry Date</form:label>
							<form:input path="pivDetail.expiryDate" cssClass="date-picker"  readonly="true"></form:input>
							<br>
	        				<form:label path="pivDetail.description" >Description</form:label>
							
							<form:textarea path="pivDetail.description" rows="3" cols="30" readonly="true" />
							</br>
						</div>
						<div id="container">
</div>


<div id="result">
						<table class="innerTable">
						  	<tr class="innerTableHeader">
							    <th>#</th>
							    <th>Acct Code</th>
							    <th>Description</th>
							    <th>Amount</th>
						  	</tr>
  
							<c:forEach items="${pivModel.amountList}" var="amtGrid" varStatus="status">
								<tr>
									<td align="center">${status.count}</td>
									<td>${amtGrid.acCd}<form:hidden path="amountList[${status.index}].acCd"  /></td>
									<td>${amtGrid.acNm}<form:hidden path="amountList[${status.index}].acNm"  /></td>
									<td>
										<form:input  readonly="true" cssStyle="text-align:right" onkeyup="numericValidate(this,event)" onkeypress="return restrictToTwoDecimalPossition(this,event);"  path="amountList[${status.index}].amount" />
									</td>
								</tr>
								
							</c:forEach>
 							<tr>
								<td colspan="3">Total</td>
								<td ><form:input cssStyle="text-align:right"  readonly="true" path="pivDetail.grandTotal"></form:input></td>
							</tr>
						</table>
	        			</div>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="2">
        				
        				<c:if test="${myVar =='NEW'}">
        				
        				</c:if> 
        				
        				
        				
        				<c:choose>
						    <c:when test="${pivModel.mode=='NEW'}">
						       <input class="button" type="submit" name="actionButton" value="Add"></input>
						        
						    </c:when>    
						    <c:when test="${pivModel.mode=='MODIFY'}">
						        <input class="button" type="submit" name="actionButton" value="Modify"></input>
						        <input class="button" type="submit" name="actionButton" value="Send for Validate"></input>
						    </c:when>    
						    <c:otherwise>
						        
						    </c:otherwise>
						</c:choose> 
						 <input class="button" type="submit" name="actionButton" value="Exit"></input>
						 
        			</td>
        		</tr>
        	</table>
		</form:form>
         	
        </div>
        <%@ include file="../admin/footer.jsp" %>
        
	<body>
</body>
</html>
