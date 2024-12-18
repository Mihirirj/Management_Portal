<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
 

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>"/>

<link rel="stylesheet"  href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script>

function conformChequeRealise()
{
	var pivNo = 'qq';'
	var chequeNo = '123';
	alert(pivNo);
	alert(chequeNo);
	var answer = confirm ("Are you sure you want to realise this cheque?");
	if (answer)
	{
			 window.location = "realisedCheque?pivNo="+pivNo+"&chequeNo="+chequeNo;
	}
		//return false;	
	
	
	//return true;
}
</script>
<script>



$(document).ready(function(){
  
    $(".viewrow").on('click',function(){
    	
         var currentRow=$(this).closest("tr");
         var x = currentRow.find(".idrow").html();
         var no=x.trim();
  
         $.ajax({
                type : "GET",
                url : 'popupView',
                data : {
                		pivNo:no
                },
               
         success : function(response) {
             $('#myModal').html(response).modal();
            
         },
         error : function() {
             $('#myModal').html('<p>An error has occurred</p>');
             console.log("Error Link");
            
             alert("error"+no);
         }
            });
    });
});


/////////////////////////edit by sandun

//myFunction()
function myFunctionAjx() {
    $.ajax({
    	
        type : "POST",
        url : 'findChequePiv',
        console.log("hell world");
        data : $("#cheqeForm").serialize(),
        dataType:'text',
        success : function(response) {
            $('#cheqeForm').html(response);
        },
        error : function() {
            $('#cheqeForm').html('<p>An error has occurred</p>');
        }
    });
}


function returnCheck(){
	$.ajax({
		type : "POST",
		url: 'returnedCheque',
		data : $("#chequePayment").serialize(),
		dataT8ype: 'text',
		success : function(response){
			$('#txtChequeNo').html(response);
		},
		 error : function() {
	            $('#txtChequeNo').html('<p>An error has occurred</p>');
	        }
		
			
		}
	
	});
}
function realisedCheck(){
	$.ajax({
		type : "POST",
		url: 'realisedCheque',
		data : $("#chequePayment").serialize(),
		dataType: 'text',
		success : function(response){
			$('#txtChequeNo').html(response);
		},
		 error : function() {
	            $('#txtChequeNo').html('<p>An error has occurred</p>');
	        }
		
			
		}
	
	});
}

</script>
<style>
.scrollable{
	height:400px;
	overflow:scroll;
}

</style>
</head>

<body>
	 
		<%@ include file="../admin/header.jsp" %>
		
		<%@ include file="../admin/menuv.jsp" %>
		
        <div class="tblBody" >
        	<div class="body-screen">
        		<div class = "col-sm-3 col-md-12" >
        		  <div class = "scrollable" >
        		  <form:form name="cheqeForm" id="cheqeForm" method="post" action="findChequePiv" modelAttribute="pivModel" >
        	
        		  <form:label id="" cssClass="field" path="chequeNo">Cheque No: </form:label><br>
								<form:input id="txtChequeNo" path="chequeNo" size="20" maxlength="10" />
		
					 	&nbsp;&nbsp;
					 	<a href="javascript:myFunction()">
				  			<input class="button" type="submit" value="Search"></input>
				 		 </a>
								&nbsp;&nbsp;&nbsp;${pivModel.pivTotal}
					 <div class="msgError"><c:out value="${pivModel.message}"></c:out>
            
            		</div>	
            		<br>
            		        		
				  <table class="table table-bordered text-center">
				    <tbody>
				      <tr>
				       <th>
				      <p class="text-center">Cheque No.</p>
				      </th>
				      <th>
				      <p class="text-center">PIV No.</p>
				      </th>
				       <th>
				      <p class="text-center">Bank Ref. No.</p>
				      </th>
				       <th>
				      <p class="text-center">Paid Date</p>
				      </th>
				      <th>
				      <p class="text-center">Cheque Amount</p>
				      </th>
				      <th>
				      <p class="text-center">Cheque Bank/Branch</p>
				      </th>
				      
				      
				      <th>
				      <p class="text-center">Realized or Returned</p>
				      </th>
				     
				     
				      </tr>
				      <c:forEach items="${pivList}" var="pivPayment">
				      		
				      			<tr>
					  			<td>
					  			${pivPayment.chequeNo}
					  			</td>
					  			<td>
				       				${pivPayment.id.pivNo}
				       			</td>
				       			<td>
				       				${pivPayment.pivDetail.bankCheckNo}
				       			</td>
					  			<td>
				       				${pivPayment.pivDetail.paidDate}
				       			</td>
					  			
					  			<td>
					  			<fmt:formatNumber groupingUsed="true" maxFractionDigits="2" minFractionDigits="2" value="${pivPayment.paidAmount}"></fmt:formatNumber>
					  			
				       			
					  			</td>
					  			
					  			<td>
					  			${pivPayment.chequeBankCode}-${pivPayment.chequeBankBranch}
					  			</td>
					  			
					  			
					  			
					  			
					  			
								<td>
							    
							    
							   <a href="realisedCheque?pivNo=${pivPayment.id.pivNo}&chequeNo=${pivPayment.chequeNo}" onclick="returnCheck()">Realise</a>
							  &nbsp; &nbsp; |  &nbsp; &nbsp;<a href="returnedCheque?pivNo=${pivPayment.id.pivNo}&chequeNo=${pivPayment.chequeNo}" onclick="returnCheck()">Return</a>
								</td>
								
								 </tr>
								 		        
					  </c:forEach>
				    </tbody>
				    </table>
				    </form:form>
				 </div>
				  <div class="pull-right">
				  <a href="../admin/mainmenu">
				  <input class="button" type="submit" value="Exit"></input>
				  </a>
				</div>
				</div>
				
			    </div>
			     <!-- Modal -->
				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-dialog">
							<div class="modal-content"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">X</button>
						</div>
					</div>	
				</div>
		 <div>
		
		 <%@ include file="../admin/footer.jsp" %>
		 </div>
        </div>
        

        
	
</body>
</html>
