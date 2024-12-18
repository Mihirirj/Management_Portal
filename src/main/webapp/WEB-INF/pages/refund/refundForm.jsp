<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>REFUND FORM</title>

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

function validateAmount(){
	var requestAmount=parseFloat(document.getElementById("refundRequestAmount").value);
	var refundable_amount="${refundable_amount}";
/* 	var n=refundable_amount.length;
	var m=refundAmount.length; */
	if(requestAmount>refundable_amount){
		$("#excededAmount").modal();
	}	
}	
   
    function requestButton(){
    	var requestAmount=document.getElementById("refundRequestAmount").value;
    	var refundable_amount="${refundable_amount}";
    	//var n=refundable_amount.length;
		
    	if(requestAmount=="" || requestAmount=="null" || requestAmount==0){

    		$("#emptyamnt").modal();
    	}
    	else{
    		$("#requestButtonmod").modal();
    	}
    }
    
    function emptyFeild(){
    	var pivno=document.getElementByID("pivno").value;
    	if(pivno=="" || pivno=="null"){
    		$("emptyfield").modal();
    	}
    }
	
	
</script>

</head>


<body>
	<%@ include file="..//admin/header.jsp"%>
	<%@ include file="..//admin/menuv.jsp"%>
	
	<div class="tblBody">

	<div class="row">
		<form:form id="refundForm" method="post" action="requestrefund" modelAttribute="pivModel">
		
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
				<form:label path="pivDetail.id.pivNo">PIV No   </form:label>
				<form:input path="pivDetail.id.pivNo" id="pivno" />
			
				<input id="FindBtn" class="button" type="submit" name="actionButton" value="Find" onclick="javascript:emptyFeild()" ></input>	
			</div>
			
			<div>
			<%@ include file="..//view/pivPrintForm.jsp"%>
			</div> 
			<br>
			
			<div>
			<form:hidden path="mode" />
        		<c:set var="myVar" value="${pivModel.mode}"/> 
			<c:set var="status" value="${pivModel.pivDetail.status}"/> 
			
			
				<div>
					
			
						<label class="field" >Total Refundable amount</label>
						<input type="text" name="totalRefundAmnt" value="${pivModel.pivDetail.pivAmount}" readonly/>
						
						
					<br></br>
			
						<form:label cssClass="field" path="pivRefund.refundRequestAmount">Request Amount</form:label>
						<form:input path="pivRefund.refundRequestAmount" id="refundRequestAmount" type="text" onkeyup="numericValidate(this,event)"/>
					<c:if test="${myVar =='FR'}">
						<c:if test="${status =='P'}">
							<div>
								<input id="refundFormBtn" class="button" type="button" value="Request" onclick="javascript:requestButton()"></input>
							</div>
						</c:if>	
					</c:if>		
					<c:if test="${myVar =='FC'}">
						<c:if test="${status =='FR'}">
							<div>
								<form:button  id="refundFormCancelBtn" class="button" type="button" value="Cancel" ></form:button>
								<form:button class="button" type="submit" name="actionButton" value="Cancel">Cancel</form:button>
							</div>
						</c:if>	
					</c:if>					
				</div>
			
			
			</div>
			
<!-- Modal for the Request button -->
<div class="modal fade" id="requestButtonmod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Send Request for refund PIV</h4>
      </div>
      <div class="modal-body">
        Are you sure you want to send this request?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
        <button id="sendButton" type="submit" class="btn btn-primary"  value="Send" name="actionButton"> Send </button>
      </div>
    </div>
  </div>
</div>

<!-- Modal for the Empty selection of dropdown-->
<div class="modal fade" id="emptyfield" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        Please enter a PIV number first!!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal for the Empty feild of amount -->
<div class="modal fade" id="emptyamnt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        Please enter the amount need to be refunded!!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal for the invalid input of request amount -->
<div class="modal fade" id="excededAmount" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        The entered amount cannot refunded.!!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
					
		
	</form:form>
	</div>
	</div>


	<%@ include file="..//admin/footer.jsp"%>
</body>
</html>