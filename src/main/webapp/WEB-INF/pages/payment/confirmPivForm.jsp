<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" />


<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>


<script type="text/javascript">
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
        data: $("#confirmPivForm").serialize(),
        success : function(data) {
            $('#result').html(data);
        }
    });
    e.preventDefault();
}



function getAgentBranch() {
    $.ajax({
    	type: "POST",
    	
        url : 'viewGetAgentBranch',
        data: $("#confirmPivForm").serialize(),
        success : function(data) {
            $('#branchTbl').html(data);
        }
    });
    
}

function getBankBranch() {
    $.ajax({
    	type: "POST",
    	
        url : 'getBankBranch',
        data: $("#confirmPivForm").serialize(),
        success : function(data) {
            $('#bankBranchTbl').html(data);
            
        }
    	
    });
}

function viewPaymentList() {
    
    $.ajax({
    	type: "POST",
    	
        url : 'viewPaymentList',
        data: $("#confirmPivForm").serialize(),
        success : function(data) {
            $('#payList').html(data);
            console.log("hiii");
        },
    	error : function(){
    		console.log("Hello");
    	}
    	
    });
    document.getElementById("txtPaidAmount").value = "";
    document.getElementById("txtChequeNo").value = "";
    document.getElementById("txtChequeDate").value = "";
    document.getElementById("cmbChequeBank").value = "";
    document.getElementById("cmbChequeBranch").value = "";
}


function pageSubmit()
{
	
	
	//document.getElementById('pivFormBtn').value = "PivNoSel";
	console.log("1111111111111111111111111111110");
	//this.pivForm.actionButton.value = "PivNoSel";
	pivForm.submit();
	console.log("000000000000000000000000000000000");
	
	
}

function calculateTotal(acctCode,sortkey)
{
	//alert('calculate')
	var el = document.pivForm.elements;
	var amtTot = 0;
	var tot4Tax = 0;
    for(var i = 0 ; i < el.length ; ++i) 
	{
		if(el[i].type == "text" ) 
		{
			//alert('calculate@@@'+el[i].name)
			
			if(el[i].name.indexOf('amountList')==0) 
			{
				//alert(el[i].id);
				var sortK = document.getElementById("sortKey"+el[i].id).value;
				sortK = parseFloat(sortK.replace(/[^\d\.\-\ ]/g,""));
				
				//alert("sortkey "+sortK);
				var tax = -1;
				if(document.getElementById("taxVal"+el[i].id)!=null)
					tax = document.getElementById("taxVal"+el[i].id).value;

				if(tax>0)
				{
					//var taxAmt = amtTot*tax;
					//document.getElementById(el[i].id).value = taxAmt;
					//amtTot = 
					calculateTax(el[i].id,tax,sortK);
				}
				var amt = el[i].value;
				
				if(amt=='')
					amt = 0;
				else
					amt = parseFloat(amt.replace(/[^\d\.\-\ ]/g,""));
				amtTot = amtTot+amt;
				
			}
		}
	}
    document.getElementById("pivTotalTxt").value = formatCurrency(amtTot);
}

function calculateTax(taxCode,taxVal,sortKey)
{
	var el = document.pivForm.elements;
	var tot4Tax = 0;
    for(var i = 0 ; i < el.length ; ++i) 
	{
		if(el[i].type == "text" ) 
		{
			if(el[i].name.indexOf('amountList')==0) 
			{
				var sortK = document.getElementById("sortKey"+el[i].id).value;
				sortK = parseFloat(sortK.replace(/[^\d\.\-\ ]/g,""));
				
				
				if(sortKey>sortK)
				{
					var amt = el[i].value;
					if(amt=='')
						amt = 0;
					else
						amt = parseFloat(amt.replace(/[^\d\.\-\ ]/g,""));
					tot4Tax = tot4Tax+amt;
				}
				
			}
		}
	}
	var taxAmt = tot4Tax*taxVal;
	document.getElementById(taxCode).value = formatCurrency(taxAmt);
    
}

function showAddPayment()
{
	document.getElementById('showPayment').style.display = '';
}

function pageLoad()
{
	document.getElementById('showPayment').style.display = 'none';
}

function deletePayment(serialNo) {
    
    $.ajax({
    	type: "POST",
    	
        url : 'deletePayment?SerialNo='+serialNo,
        data: $("#confirmPivForm").serialize(),
        success : function(data) {
            $('#payList').html(data);
            console.log("hiii");
        },
    	error : function(){
    		console.log("Hello");
    	}
    	
    });
    
}

function openPopup(pivNo)
{
	
       var url="../viewprint/printInvoice?pivNo="+pivNo;
       var width = 1100;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
    var windowFeatures = "width=" + width + ",height=" + height +  
        ",status,resizable,left=" + left + ",top=" + top +
        "screenX=" + left + ",screenY=" + top + ",scrollbars=yes";
       window.open(url, "subWind", windowFeatures, "POS");//window.open(url,"_blank","directories=no, status=no,width=1000, height=700,top=50,left=50", "scrollbars=1");
}

function copyCustomerInfo()
{
	document.getElementById("txtReperesentId").value = document.getElementById("txtIdNo").value
	document.getElementById("txtRepresentName").value = document.getElementById("txtName").value
}
////////////edit by sandun////////
function confirmReject() {
	
	//var cfm = confirm("Do you want to reject this PIV?");
	//if(cfm)
	//{
		//alert(cfm)
		var rrt = document.getElementById("txtRejectReason").value;
		
		if(rrt.trim().length==0){
			
			alert("Enter the Reject reason");
			
			document.getElementById("txtRejectReason").focus();
			return false;
			
		}
		
		else
			return true;
		
	//}
	
}
/////////////////
</script>
</head>


	<body onload="pageLoad()">
    	<%@ include file="../admin/header.jsp" %>
    	<%@ include file="../admin/menuv.jsp" %>
        <div class="tblBody">
        	<form:form name="confirmPivForm" id="confirmPivForm" method="post" action="savePaymentDetail" modelAttribute="pivModel" >
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
        			<td width="40%" valign="top">
        				
        				<fieldset>
        					<form:hidden path="mode" />
        					<form:hidden path="returnCheque" />
        					<form:hidden path="returnChequeNo" />
        					<form:hidden path="pivDetail.id.deptId" />
        					<c:set var="myVar" value="${pivModel.mode}"/>  
        					<c:if test="${myVar !='NEW'}">
	        					<p>
		        					<form:label cssClass="field" path="pivDetail.id.pivNo">PIV No. : </form:label>
		        					<form:select id="cmbPivNo"  path="pivDetail.id.pivNo" onchange="javascript:pageSubmit()">
									    <form:option value="-1" label="- Select -"/>
									    <form:options items="${pivModel.pivNoList}" />

									</form:select>
								</p>
								<p>
									<form:label cssClass="field" path="pivDetail.bankCheckNo">Bank Ref. No. : </form:label>
									<form:input id="Iintsim1" path="pivDetail.bankCheckNo" readonly="" />
								</p>
	        				</c:if>
        					<p>
	        					<form:label cssClass="field" path="pivDetail.titleCd">PIV Type : </form:label>
	        					<form:select id="cmbPivType" path="pivDetail.titleCd" onchange="javascript:getPivTypes()">
								    <form:option value="-1" label="- Select -"/>
								    <form:options items="${pivModel.pivTypeList}" />
								</form:select>
							</p>
							<p>
								<form:label cssClass="field" path="pivDetail.referenceNo">Reference No : </form:label>
								<form:input  id="txtRefNo" path="pivDetail.referenceNo" size="30" maxlength="24" />
							</p>
							<p>
							 	<form:label cssClass="field"  path="pivApplicant.idType">Customer's ID : </form:label>
							 	<form:radiobutton id="radio1" path="pivApplicant.idType" value="N"/>&nbsp;NIC&nbsp;&nbsp;
								<form:radiobutton id="radio2" path="pivApplicant.idType" value="P"/>&nbsp;Passport&nbsp;&nbsp;
								<form:radiobutton id="radio3" path="pivApplicant.idType" value="B"/>&nbsp;Business Reg. No.&nbsp;&nbsp;
								<form:radiobutton id="radio4" path="pivApplicant.idType" value="O"/>&nbsp;Other
						 	</p>
							<p>
								<form:label cssClass="field" path="pivApplicant.idNo"></form:label>
								<form:input id="txtIdNo" path="pivApplicant.idNo" size="15" maxlength="12"/>
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.name">Customer's Name : </form:label>
								<form:input id="txtName" path="pivApplicant.name" size="50" maxlength="100"/>
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.address">Address : </form:label>
								<form:textarea id="txtAddress" path="pivApplicant.address"   rows="2" cols="30" />
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.telephoneNo">Telephone : </form:label>
								<form:input id="txtTelephone" path="pivApplicant.telephoneNo" size="50" maxlength="10"/>
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.email">Email : </form:label>
								<form:input id="txtEmail" path="pivApplicant.email" size="50" maxlength="30"/>
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.vatRegNo">VAT Reg. No : </form:label>
								<form:input id="txtEmail" path="pivApplicant.vatRegNo" size="50" maxlength="25"/>
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.collectPersonId">Representative ID : </form:label>
								<form:input id="txtReperesentId" path="pivApplicant.collectPersonId" size="15" maxlength="12" />
								<c:if test="${pivModel.mode=='NEW' || pivModel.mode=='MODIFY'}">
								 <a href="javascript:copyCustomerInfo()">Same as Customer</a>
								</c:if>
							</p>
							<p>
								<form:label id="" cssClass="field" path="pivApplicant.collectPersonName">Name of Representative: </form:label>
								<form:input id="txtRepresentName" path="pivApplicant.collectPersonName" size="50" maxlength="100" />
							</p>
							<p>
	        					<form:label cssClass="field" path="pivDetail.description">Description : </form:label>
								<form:textarea id="Iintsim9" path="pivDetail.description" rows="3" cols="30"  />
							</p>
							
						</fieldset>
        			</td>
        			<td width="60%" valign="top">
        			
	        			<div id="result">
	        			
							<table class="innerTable" border="1">
							  	<tr class="innerTableHeader">
								    
								    <th width="30">Acct Code</th>
								    <th width="100">Description</th>
								    <th width="50">Amount</th>
							  	</tr>
 									<c:choose>
						    			<c:when test="${fn:length(pivModel.amountList) gt 0}">
						    				
											<c:set var="amtListVar" value="${pivModel.amountList}"/> 
											<c:forEach items="${pivModel.amountList}" var="amtGrid" varStatus="status">
												<c:set var="amtListVar" value="yes"/> 
												<tr>
													<td>${amtGrid.acCd}
													<form:hidden id="sortKey${amtGrid.acCd}" path="amountList[${status.index}].sortKey"  />
													<form:hidden path="amountList[${status.index}].acCd"  />
													</td>
													<td >
														${amtGrid.acNm}<form:hidden path="amountList[${status.index}].acNm"  />
														               
														<form:hidden id="taxVal${amtGrid.acCd}" path="amountList[${status.index}].taxValue" />
														
														<c:if test="${amtGrid.taxValue >0}">
														 (${amtGrid.taxValue})
														 
														</c:if>
													</td>
													<td align="right">
														<c:if test="${amtGrid.isTax =='Y'}">
														<form:input size="15" id="${amtGrid.acCd}" readonly="true" cssStyle="text-align:right" onkeyup="numericValidate(this,event)" onkeypress="return restrictToTwoDecimalPossition(this,event);"   path="amountList[${status.index}].amount" />
														</c:if>
														<c:if test="${amtGrid.isTax !='Y'}">
														<form:input size="15" id="${amtGrid.acCd}" cssStyle="text-align:right" onkeyup="numericValidate(this,event);" onkeypress="return restrictToTwoDecimalPossition(this,event);" onblur="calculateTotal('${amtGrid.acCd}','${amtGrid.sortKey}');formatValue(this)"  path="amountList[${status.index}].amount" />
														</c:if>
													</td>
												</tr>
											</c:forEach>
											<tr>
												<td colspan="2">Total</td>
												<td align="right" ><form:input size="15" cssStyle="text-align:right" id="pivTotalTxt" path="pivTotal" readonly="true"></form:input></td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
						    					<td>&nbsp;</td>
						    					<td>&nbsp;</td>
						    					<td>&nbsp;</td>
						    				</tr>
						    			</c:otherwise>
						    			
								</c:choose>
							<c:if test="${amtListVar=='no'}"> 
							<tr>
									<td >&nbsp;</td>
									<td >&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</c:if>
 							<c:if test="${amtListVar=='yes'}"> 
 							
							</c:if>
							</table>
	        			</div>
	        			<fieldset>
	        				<p></p>
	        				<fieldset>
	        				<p>
							 	<form:label cssClass="field" path="pivDetail.currencyCode">Currency : </form:label>
							 	<form:radiobutton id="cur1" path="pivDetail.currencyCode" value="LKR"/>&nbsp;Rupee&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur2" path="pivDetail.currencyCode" value="USD"/>&nbsp;US Dollar&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur3" path="pivDetail.currencyCode" value="GBP"/>&nbsp;Pound Sterling&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur4" path="pivDetail.currencyCode" value="EUR"/>&nbsp;Euro
						 	</p>
						 	</fieldset>
	        				<p>
		        				<form:label cssClass="field" path="pivDetail.pivDate">Issued Date : </form:label>
								<form:input id="Iintsim10" path="pivDetail.pivDate" cssClass="date-picker" maxlength="10" readonly="true" ></form:input>
							</p>
							<p>
								<form:label cssClass="field" path="pivDetail.expiryDate">Expiry Date : </form:label>
								<form:input id="Iintsim11" path="pivDetail.expiryDate" cssClass="date-picker" maxlength="10" readonly="true" ></form:input>
							</p>
							<!-- edit by sandun -->
							<c:if test="${pivModel.mode=='CANCEL'}">
							<p>
								<form:label cssClass="field" path="pivDetail.rejectedReason">Reject Reason : </form:label>
								<form:textarea id="txtRejectReason" onmousemove="confirmReject()" path="pivDetail.rejectedReason" rows="3" cols="30"  />
							</p>
							</c:if>
							
							
							<c:if test="${pivModel.mode=='APPROVE'}">
							<p>
								<form:label cssClass="field" path="pivDetail.rejectedReason">Reject Reason : </form:label>
								<form:textarea id="txtRejectReason" path="pivDetail.rejectedReason" rows="3" cols="30"  />
							</p>
							</c:if>
							 <c:if test="${myVar =='CONFIRM'}"> 
							 <table border="1" width="100%" bgcolor="#FFB6B5">
							 <tr>
							 	<td>Payments
							 	</td>
							 	</tr>
							 	<tr>
							 	<td>
							<p>
								<form:label cssClass="field" path="pivDetail.paidDate">Date Paid : </form:label>
								<form:input id="paidDate" path="pivDetail.paidDate" cssClass="date-picker" maxlength="10" readonly="true" ></form:input>
							</p>
							
							<p>
								<form:label cssClass="field" path="pivDetail.paidAgent">Paid Agent : </form:label>
								<form:input  id="txtPaidAgent" path="pivDetail.paidAgent" readOnly="true"></form:input>
							</p>
							
							<p>
								<form:label cssClass="field" path="pivDetail.paidBranch">Paid Agent Branch : </form:label>
								<form:input  id="txtPaidAgent" path="pivDetail.paidBranch" readOnly="true"></form:input>
							</p>
														 
							<div id="payList"><%@ include file="newPayments.jsp" %></div>
							<a href="javascript:showAddPayment()" >Add Payment</a>
							<div id="showPayment">
							<p>
							 	<form:label cssClass="field" path="paymentMode">Payment Mode : </form:label>
							 	<form:radiobutton id="cur1" path="paymentMode" value="C"/>&nbsp;Cash&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur2" path="paymentMode" value="Q"/>&nbsp;Cheque&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur3" path="paymentMode" value="D"/>&nbsp;Bank Draft&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur4" path="paymentMode" value="R"/>&nbsp;Credit Card&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur5" path="paymentMode" value="M"/>&nbsp;Direct Transfer&nbsp;&nbsp;&nbsp;
								<form:radiobutton id="cur6" path="paymentMode" value="B"/>&nbsp;Cheque Deposit to Bank
						 	</p>
						 	<p>
								<form:label cssClass="field" path="paidAmount">Paid Amount : </form:label>
								<form:input id="txtPaidAmount" path="paidAmount" size="50" maxlength="30"/>
							</p>
						 	<p>
								<form:label cssClass="field" path="chequeNo">Cheque No. : </form:label>
								<form:input id="txtChequeNo" path="chequeNo" size="50" maxlength="30"/>
							</p>
							<p>
								<form:label cssClass="field" path="chequeDate">Cheque Date : </form:label>
								<form:input id="txtChequeDate" path="chequeDate" ></form:input>
							</p>
							<p>
		        					<form:label cssClass="field" path="chequeBank">Cheque Bank Code : </form:label>
		        					<form:select id="cmbChequeBank"  path="chequeBank" onchange="javascript:getBankBranch()">
									<form:option value="" label="--- Select Bank ---"/>
									<form:options items="${pivModel.bankList}" />
									<!--<c:forEach var="list" items="${pivModel.bankList}">
								       <option id="${list.key}" value="${list.key}">${list.value}</option>   
								   	</c:forEach>
								   	-->

									</form:select>
								</p>
								<div id="bankBranchTbl"	>
									<%@ include file="../issue/bankBranchCombo.jsp" %>
								</div>
								<!-- 
								<p id="qqqq">
		        					<form:label cssClass="field" path="chequeBranch">Cheque Bank Branch Code : </form:label>
		        					<form:select  path="chequeBranch">
									<form:option value="" label="- Select -"/>
									<form:options items="${pivModel.bankBranchList}" />

									</form:select>
								</p>
								 -->
								<p><a href="javascript:viewPaymentList()" >Save Payment</a></p>
								</div>
								</td>
								</tr>
								<tr><td></td></tr>
								<tr><td></td></tr>
								</table>
								
							</c:if> 
						</fieldset>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="2">
        				<c:if test="${myVar =='NEW'}">
        				</c:if> 
        				<input type="hidden" name="actionButton" value=""/>
        				<c:choose>
						    <c:when test="${pivModel.mode=='NEW'}">
						       <input id="pivFormBtn" class="button" type="submit" onclick="return pivFormValidate()" name="actionButton" value="Add" ></input>
						        
						    </c:when>    
						    <c:when test="${pivModel.mode=='MODIFY'}">
						        <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Modify"></input>
						        <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Send for Approval" onclick="openPopup('${pivModel.pivDetail.id.pivNo}')"></input>

						    </c:when>    
						    <c:when test="${pivModel.mode=='VALIDATE'}">
						       <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Validate"></input>
						        
						    </c:when> 
						     <c:when test="${pivModel.mode=='APPROVE'}">
						       <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Approve"></input>
						        <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Reject" onclick="return confirmReject()"></input>
						    </c:when> 
						     <c:when test="${pivModel.mode=='CONFIRM'}">
						       <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Confirm"></input>
						        
						    </c:when> 
						    
						    <c:when test="${pivModel.mode=='CANCEL'}">
						       <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Modify"></input>
						        
						    </c:when>
						     
						    <c:otherwise>
						        
						    </c:otherwise>
						</c:choose> 
						 <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Exit"></input>
        			</td>
        		</tr>
        	</table>
		</form:form>
         	
        </div>
        <%@ include file="../admin/footer.jsp" %>
   
        
	
	
<script>
$(document).ready(function() {
$('#paiddate').datepicker({
format: "dd/mm/yyyy",
//startDate: new Date(), //to disable past dates
endDate: new Date() //to disable future dates
});
});

$(document).ready(function() {
	$('#txtChequeDate').datepicker({
	format: "dd/mm/yyyy",
	//startDate: new Date(), //to disable past dates
	endDate: new Date() //to disable future dates
	});
	});						
</script>
</body>
</html>