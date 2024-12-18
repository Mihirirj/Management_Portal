<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>REFUND APPROVE</title>

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

<script>

function approveButton(){
	$("#approveButtonmod").modal();
}

function issueButton(){
	$("#issueButtonmod").modal();
}

function openPopup(pivNo)
{
	
       var url="../viewprint/printRefundInfo?pivNo="+pivNo;
       var width = 1100;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
    var windowFeatures = "width=" + width + ",height=" + height +  
        ",status,resizable,left=" + left + ",top=" + top +
        "screenX=" + left + ",screenY=" + top + ",scrollbars=yes";
       window.open(url, "subWind", windowFeatures, "POS");//window.open(url,"_blank","directories=no, status=no,width=1000, height=700,top=50,left=50", "scrollbars=1");
}
</script>

</head>

<body>
	<%@ include file="..//admin/header.jsp"%>
	<%@ include file="..//admin/menuv.jsp"%>
	<div class="tblBody">
	<div class="row">
		<form:form id="refundForm" method="post" action="refundApprove" modelAttribute="pivModel">
		
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
		
			<div>
			<%@ include file="..//view/pivPrintForm.jsp"%>
			</div>

		<form:hidden path="mode" />
        	<c:set var="myVar" value="${pivModel.mode}"/>
        		<c:if test="${myVar =='approve'}">
		
			<div>
				<label class="field" >Requested Amount</label>
				<input type="text" value="${reqAmount}" readonly/>
			</div>
		<br></br>
			<div>
				<input id="refundApproveBtn" class="button" type="button" value="Approve" onclick="javascript:approveButton()"></input>
				<input class="button" type="submit" value="Back To List" name="actionButton"></input>	
				<input class="button" type="button" value="Print" name="actionButton" onclick="openPopup('${pivModel.pivDetail.id.pivNo}')"></input>	
			</div>	
				</c:if>
		
			<div>
				<c:if test="${myVar =='FA'}">
				<input class="button" type="submit" value="Back To List" name="actionButton"></input>
				</c:if>	
			</div>
			
			<div>
				<c:if test="${myVar =='issue'}">
				<div>
					<form:label cssClass="field" path="pivRefund.docNo">Pay Slip No</form:label>
					<form:input path="pivRefund.docNo" id="paySlipNo" />
					<br></br>
					
					<%-- <c:set var="date" value="<%=new java.util.Date()%>" />
					<form:label cssClass="field" path="pivRefund.refundDate">Issued Date :</form:label>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${date}" var="formattedDate" />
					<form:input path="pivRefund.refundDate" value ="${formattedDate}" type="date"/> --%>
					
					<form:label cssClass="field" path="pivRefund.refundDate">Payslip Issued Date :</form:label>
					<form:input path="pivRefund.refundDate" cssClass="date-picker" />
					
				</div>	
					<br></br>
			<div>
				<input id="refundIssueBtn" class="button" type="button" value="Issue" onclick="javascript:issueButton()"></input>
				<input class="button" type="submit" value=" Back To List" name="actionButton"></input>		
			</div>	
					
				</c:if>
				
				<c:if test="${myVar == 'F'}">
				<div>
					<input class="button" type="submit" value=" Back To List" name="actionButton"></input>
				</div>
				</c:if>
			</div>
								
				
<!-- Modal for the Approve button -->
<div class="modal fade" id="approveButtonmod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Approve Request for PIV refund</h4>
      </div>
      <div class="modal-body">
        Confirm this refund request?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
        <button id="sendButton" type="submit" class="btn btn-primary"  value="Confirm" name="actionButton"> Confirm </button>
      </div>
    </div>
  </div>
</div>	

<!-- Modal for the Issue button -->
<div class="modal fade" id="issueButtonmod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Issue refund PIV</h4>
      </div>
      <div class="modal-body">
        Issue this fund?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
        <button id="sendButton" type="submit" class="btn btn-primary"  value="Issue" name="actionButton"> Issue </button>
      </div>
    </div>
  </div>
</div>				
			


		</form:form>
		
			
		</div>
		</div>
</body>
</html>