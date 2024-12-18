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
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>






<script>
function myFunction() {
    $.ajax({
        type : "POST",
        url : 'SubmitSearchFormAjax',
        data : $("#searchForm").serialize(),
        dataType:'text',
        success : function(response) {
            $('#srchResult').html(response);
        },
        error : function() {
            $('#srchResult').html('<p>An error has occurred</p>');
        }
    });
}

/*$(document).ready(function(){
  
    $(".idrow").on('click',function(){
    	alert('click');
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
*/

</script>
<style>
.scrollable{
	height:400px;
	overflow:scroll;
}

</style>
</head>
<style>
table.table1 {
	
}

.innerTableHeader {
    padding: 8px;
     background-color: #A10000;
    color: white;
}
.tblBodymy {		
	margin: 15px auto;

	margin-left:0px;

	width: 953px;
}
table.table1 td {
    padding: 8px;}
    
table.table2 td,th {
    padding: 8px;}
    
table.table3 td,th {
    padding: 8px;}
 
 table.paddingTable td,th {
    padding: 8px;}
       
bodyy1 {
    background: #fff;
   
    font-family: "Helvetica Neue" , "Lucida Grande" , "Segoe UI" , Arial, Helvetica, Verdana, sans-serif;
    margin: 0px;
    padding: 0px;
    color: #696969;
    vertical-align: middle;
}

#p1 {background-color:rgba(255,0,0,0.3);}
#p2 {background-color:rgba(0,255,0,0.3);padding:12px;}
#p3 {background-color:rgba(0,0,255,0.3);}
#p4 {background-color:rgba(192,192,192,0.3);}
#p5 {background-color:rgba(255,255,0,1.9);padding:12px;}
#p6 {background-color:rgba(255,0,255,0.3);}
</style>
<body>
	 
	 <%@ include file="..//admin/header.jsp"%>
		<%@ include file="..//admin/menuv.jsp"%>
		
        <div class="tblBody" >
        	<form:form action="SubmitSearchForm" method="POST" id="searchForm" modelAttribute="pivModel">
				<table>
					<tr>
						<td width="30%">
							<form:label cssClass="labelfont" path="pivDetail.id.pivNo">PIV No.</form:label>
						</td>
						<td>
							<form:input path="pivDetail.id.pivNo" />
						</td>
					</tr>
					<tr>
						<td width="30%">
							<form:label cssClass="labelfont" path="pivDetail.bankCheckNo">Bank Ref. No.</form:label>
						</td>
						<td>
							<form:input path="pivDetail.bankCheckNo" />
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivDetail.titleCd">PIV Type</form:label>
						</td>
						<td>
							<form:select path="pivDetail.titleCd">
								<form:option value="" label="- Select -" />
								<form:options items="${pivModel.pivTypeList}" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivApplicant.name">Customer Name</form:label>
						</td>
						<td>
							<form:input path="pivApplicant.name" />
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivApplicant.idNo">ID</form:label>
						</td>
						<td>
							<form:input path="pivApplicant.idNo" />
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivDetail.referenceNo" >Reference No.</form:label>
						</td>
						<td>
							<form:input path="pivDetail.referenceNo" />
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivDetail.issuedDate">Issued Date</form:label>
						</td>
						<td>
							<form:input path="issuedFrom" id="txtIssuedFrom" ></form:input>
							<label Class="labelfont"> - </label> 
							<form:input path="issuedTo" id="txtIssuedTo" ></form:input>
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivDetail.paidDate">Paid Date</form:label>
						</td>
						<td>
							<form:input path="paidFrom" id="txtPaidFrom" ></form:input>
							<label Class="labelfont"> - 
							</label> 
							<form:input path="paidTo" id="txtPaidTo" ></form:input>
						</td>
					</tr>
					<tr>
						<td colspan="2"	>
							<button class="button" type="button" onclick="javascript:myFunction()">Search</button>
							<input class="button" type="submit"  name="actionButton" value="Clear"></input> 
							<a href="../admin/mainmenu" class="button">Exit</a>
							
						</td>
					</tr>
				</table>
			</form:form>
        	<div class="body-screen">
        	
        		<div class = "col-sm-3 col-md-12" >
				  <div class = "scrollable" id="srchResult">
				  
				 
				<%@ include file="searchResult.jsp"%>
			
				  
				 </div>
				  <div class="pull-right">
				  <a href="../admin/mainmenu">
				  <input class="button" type="submit" value="Back"></input>
				  </a>
				</div>
				</div>
				
			    </div>
			    
			     <!-- Modal -->
				<div id="myModal" class="modal fade" role="dialog">
    				<div class="modal-dialog modal-sm">
        				<div class="modal-content">
        				</div>
        				<div class="modal-footer">
                			<button type="button" class="button" data-dismiss="modal">X</button>
            			</div>
        
    				</div>
				</div>
			     
			     
			     
		
		 <div>   
		
		 <%@ include file="..//admin/footer.jsp" %>
		 </div>
        </div>
        

 <script>
 	$(document).ready(function() {
		$('#txtIssuedFrom').datepicker({
		format: "dd/mm/yyyy",
			//startDate: new Date(), //to disable past dates
		endDate: new Date() //to disable future dates
		});
	});	

 	$(document).ready(function() {
		$('#txtIssuedTo').datepicker({
		format: "dd/mm/yyyy",
			//startDate: new Date(), //to disable past dates
		endDate: new Date() //to disable future dates
		});
	});	

 	$(document).ready(function() {
		$('#txtPaidFrom').datepicker({
		format: "dd/mm/yyyy",
			//startDate: new Date(), //to disable past dates
		endDate: new Date() //to disable future dates
		});
	});	

 	$(document).ready(function() {
		$('#txtPaidTo').datepicker({
		format: "dd/mm/yyyy",
			//startDate: new Date(), //to disable past dates
		endDate: new Date() //to disable future dates
		});
	});	
 </script>     
	
</body>
</html>
