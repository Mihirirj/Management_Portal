<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>REFUND APPROVE FORM</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>" />

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>" />


<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>

<link rel="stylesheet"  href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>"/>

<link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" />

<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>


</head>
<body>
	<%@ include file="..//admin/header.jsp"%>
	<%@ include file="..//admin/menuv.jsp"%>
	
	<div class="tblBody" >
		<form:form id="refundApprovePivs" method="post" action="refundApprove" modelAttribute="pivModel">
		
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
		
        	<div class="body-screen">
        		<div class = "col-sm-3 col-md-12" >
				  <div class = "scrollable" >
				  <table class="table table-bordered text-center">
				    <tbody>
				      <tr>
				      <th>
				      <p class="text-center">PIV No</p>
				      </th>
				      
				      <th>
				      <p class="text-center">PIV Amount </p>
				      </th>
				      <th>
				      <p class="text-center">Requested Amount </p>
				      </th>
				      <th>
				      <p class="text-center">Requested Date </p>
				      </th>
				      </tr>
				      <c:forEach items="${finalFrList}" var="FRPiv">
				      		
				      			<tr>
				      			<td>
					  			<a href="../refund/refundApproved?id=${FRPiv.id.pivNo}"> ${FRPiv.id.pivNo}</a>
					  			</td>
					  			
					  			<td align="right">
					  			<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" groupingUsed="true" value="${FRPiv.refundableAmount}"></fmt:formatNumber>
								  				
				       			
					  			</td>
					  			<td align="right">
					  			<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" groupingUsed="true" value="${FRPiv.refundRequestAmount}"></fmt:formatNumber>
								 
				       			
					  			</td>		
					  			<td>
				       			<fmt:formatDate pattern="dd/MM/yyyy" value="${FRPiv.requestDate}"/>
					  			</td>
								 </tr>
								 		        
					  </c:forEach>
				    </tbody>
				    </table>
				 </div>
				  
				</div>
				
			    </div>
			   
		 <div>
		 <%@ include file="..//admin/footer.jsp" %>
		 </div>
		 </form:form>
        </div>
	
	
	
</body>
</html>