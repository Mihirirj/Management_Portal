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



function findBankPayments() {
    $.ajax({
    	
        type : "POST",
        url : 'findBankPayments',
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
		<form:form name="cheqeForm" id="cheqeForm" method="post" action="findBankPayment" modelAttribute="pivModel" >
		<div class="tblBody" >
        	<div class="body-screen">
        	<div class = "col-sm-3 col-md-12" >
        	<table class="table2" border="0">
        	<tr>
        		<td><form:label id="" cssClass="field" path="chequeNo">Bank Ref. No.: </form:label></td>
        		<td><form:input id="txtBankRefNo" path="chequeNo" size="20" maxlength="14" /></td>
        		<td><input class="button" type="submit" value="Find"></input></td>
        	</tr>
        	</table>
        	
								
		
					 	&nbsp;&nbsp;
					 	
							
        	</div>
        	</div>
        </div>
        <div class="tblBody" >
        	<div class="body-screen">
        	<div class = "col-sm-3 col-md-12" >
        	<table class="table2" border="0">
                        <tr height="10">
                            <td>PIV No</td>
                            <td>&nbsp;:&nbsp;</td>
                            <td>${pivModel.pivDetail.id.pivNo}</td>
                        </tr>
                        <tr height="15">
                            <td>PIV Amount</td>
                            <td>&nbsp;:&nbsp;</td>
                            <td><fmt:formatNumber groupingUsed="true" maxFractionDigits="2" minFractionDigits="2" value="${pivModel.pivDetail.pivAmount}"></fmt:formatNumber>
					  			</td>
                        </tr>
                        <tr height="15">
                            <td>Status</td>
                            <td>&nbsp;:&nbsp;</td>
                            <td>${pivModel.pivDetail.status}</td>
                        </tr>
                       </table>     
							
        	</div>
        	</div>
        </div>
        <div class="tblBody" >
        	<div class="body-screen">
        		<div class = "col-sm-3 col-md-12" >
        		  <div class = "scrollable" >
        		  
        	
        		  	
					 <div class="msgError"><c:out value="${pivModel.message}"></c:out>
            
            		</div>	
            		<br>
            		   		
				  <table class="table table-bordered text-center">
				    <tbody>
				      <tr>
				       <th>
				      <p class="text-center">Trans ID</p>
				      </th>
				      <th>
				      <p class="text-center">Payment Date</p>
				      </th>
				       <th>
				      <p class="text-center">Paid Agent</p>
				      </th>
				      <th>
				      <p class="text-center">Agent Code</p>
				      </th>
				       <th>
				      <p class="text-center">Paid Amount</p>
				      </th>
				      <th>
				      <p class="text-center">Status</p>
				      </th>
				     
				     
				     
				      </tr>
				      <c:forEach items="${pivList}" var="PivBankTran">
				      		
				      			<tr>
					  			<td>
					  			${PivBankTran.transId}
					  			</td>
					  			<td>
					  			<fmt:formatDate pattern="dd/MM/yyyy" value="${PivBankTran.paymentDate}" ></fmt:formatDate>
					  			
				       			</td>
				       			<td>
				       			<c:choose>
								    <c:when test="${PivBankTran.bankCode =='7135'}">
								        PEOPLE'S BANK 
								        
								    </c:when>    
								    <c:otherwise>
								       CEB POS
								    </c:otherwise>
								</c:choose>
				       			
				       			</td>
				       			<td>
				       				${PivBankTran.bankCode} - ${PivBankTran.branchCode}
				       			</td>
					  			
					  			
					  			<td>
					  			<fmt:formatNumber groupingUsed="true" maxFractionDigits="2" minFractionDigits="2" value="${PivBankTran.amount}"></fmt:formatNumber>
					  			
				       			
					  			</td>
					  			<td>
				       				${PivBankTran.returnMsg}
				       			</td>
					  			
					  			
					  			
					  			
					  			
					  			
								
								
								 </tr>
								 		        
					  </c:forEach>
				    </tbody>
				    </table>
				    
				 </div>
				  <div class="pull-right">
				  <a href="../admin/mainmenu">
				  <input class="button" type="submit" value="Exit"></input>
				  </a>
				</div>
				</div>
				
			    </div>
			    
			    lknk
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
        </form:form>

        
	
</body>
</html>
