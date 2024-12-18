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

function getbank() {
    $.ajax({
    	type: "POST",
    	
        url : 'viewGetBank',
        data: $("#pivForm").serialize(),
        success : function(data) {
            $('#qqqq').html(data);
            console.log("hiii");
        },
    	error : function(){
    		console.log("Hello");
    	}
    });

    
        
   
}

function viewPaymentList() {
    alert("hi 1");
    $.ajax({
    	type: "POST",
    	
        url : 'viewPaymentList',
        data: $("#pivForm").serialize(),
        success : function(data) {
            $('#payList').html(data);
            console.log("hiii");
        },
    	error : function(){
    		console.log("Hello");
    	}
    	
    });
    alert("hi 2");
}
    
function pageSubmit()
{
	
	
	//document.getElementById('pivFormBtn').value = "PivNoSel";
	this.pivForm.actionButton.value = "ChequeNoSel";
	pivForm.submit();
	
	
	
}

function pageSubmit1()
{
	
	
	//document.getElementById('pivFormBtn').value = "PivNoSel";
	this.pivForm.actionButton.value = "PivNoSel";
	pivForm.submit();
	
	
	
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


</script>
</head>

<!-- <body> -->
	<body>
    	<%@ include file="../admin/header.jsp" %>
    	<%@ include file="../admin/menuv.jsp" %>
        <div class="tblBody">
       
        	<table border="0" >
        		<!--<c:if test="${not empty pivModel.message}">
    				<tr>
	        			
	        		</tr>
        		</c:if>-->
        		<tr>
        			<!-- <td width="40%" valign="top"> 
        				
        				
        			</td>-->
        			
        			<!-- Add New Cheque Return Table -->
        			<td width="60%" valign="top">
        			
	        			<div id="result">
	        			
							<table class="innerTable" border="1">
							  	<tr class="innerTableHeader">
								    
								    <th>PIV NO</th>
								    <th>Cheque No</th>
								    <th>Cheque Date</th>
								    <th>Cheque Amount</th>
								    <th>Bank</th>
								    <th></th>
							  	</tr>
 								
								<c:forEach items="${pivList}" var="pivList" varStatus="status">
								<tr>
									
									<td>${pivList.id.pivNo}</td>
									<td>${pivList.chequeNo}</td>
									<td>${pivList.chequeDate}</td>
									<td>${pivList.paidAmount}</td>
									<td>${pivList.chequeBankCode} - ${pivList.chequeBankBranch}</td>
									  
									<td> 
								    	<!--<a  href="javascript:findPivForCheque('${pivList.id.pivNo}')" class="viewrow">Generate PIV</a>--> 
										<a href="returnChequePiv?pivNo=${pivList.id.pivNo}&chequeNo=${pivList.chequeNo}">Generate PIV</a>
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
   
        
	<!--<body> 
	
 <script>
$(document).ready(function() {
$('#paiddate').datepicker({
format: "dd/mm/yyyy",
//startDate: new Date(), //to disable past dates
endDate: new Date() //to disable future dates
});
});
							
</script> -->
</body>
</html>
