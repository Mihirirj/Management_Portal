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

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css"/>" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepicker.js"/>"></script>


<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css"/>" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepicker.js"/>"></script>

<script type="text/javascript">


</script>
</head>

<!-- <body> -->
	<body>
    	<%@ include file="../admin/header.jsp" %>
    	<%@ include file="../admin/menuv.jsp" %>
        <div class="tblBody">
       
        	<table border="0" >
        			
        		<!-- Add New Cheque Return Table -->
        		<td width="60%" valign="top">
        			
	       			<div id="result"> 			
						<table class="innerTable" border="1">
							<tr class="innerTableHeader">
								    
								<th>PIV NO</th>
							    <th>Paid Total Amount</th>
							    <th>PaidDate</th>
							    <th></th>
							    
						  	</tr>
 								
							<c:forEach items="${support.getOlnUpdt()}" var="paymentList" varStatus="status">														
							<tr>
									
								<td>${paymentList.getPivNo()}</td>
								<td>${paymentList.getPaidTotalAmount()}</td>
								<td>${paymentList.getPaidDate()}</td>
								<td>
									<a href="confirmPiv?pivNo=${paymentList.getPivNo()}">Confirm Payment</a>
								</td>
								
							</tr>
							</c:forEach>
							
						</table>
	        		</div>
	        	</td>
        	</tr> 
        </table>
         	
      </div>
      <%@ include file="../admin/footer.jsp" %>
   
        
</body>
</html>