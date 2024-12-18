<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
	<title>PIV DETAILS$$$$</title>
</head>

<style>
table.table1 {
	
}

.innerTableHeader {
    text-align: left;
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
    text-align: right;
    
    padding: 8px;}
    
table.table2 td,th {
    text-align:left;
    
    padding: 8px;}
    
table.table3 td,th {
    text-align:left;
    
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


<!--  font-size: .80em; -->
<body>
	<div class="tblBodymy">
		<div class="container-fluid">
			<div class="row">
	    		<div class="col-sm-6" >
		    		<h4>PIV Details</h4>
		    		<table class="table2" border="0"   >
		        	 	<input type="hidden" name="pivno" value="${pivModel.pivDetail.id.pivNo}"/>
		        	 	<tr>
		          			<th> <small>Piv No :</small></th><td>${pivModel.pivDetail.id.pivNo}</td>
		          		 </tr>
		           		
		           		<tr>
		          			<th> <small>Piv Type :</small></th><td>${pivModel.pivDetail.titleCd}</td>
		           		</tr>
		          	 	<tr> 
		          			<th><small>Bank Reference No</small> </th><td>${pivModel.pivDetail.bankCheckNo}</td>
			          	</tr>
			          	<tr>
		          			<th><small>Reference No :</small></th><td>${pivModel.pivDetail.referenceNo}</td>
		          		</tr>
		          		<tr>
		          			<th><small>Reference Type :</small></th><td>${pivModel.pivDetail.referenceType}</td>
		       		 	</tr>
		       		 	<tr>
		          			<th><small>Description :</small></th><td>${pivModel.pivDetail.description}</td>
		       		 	</tr>
		       			<tr>
		          			<th><small>Payment Mode :</small></th><td>${pivModel.pivDetail.paymentMode}</td>
		       		 	</tr>   		 
		    		</table> 
		    		
		    		<table class="table1 table-bordered ">
		    		
		    			<tr class="innerTableHeader">
		    				<th>#</th>
							<th><small>Acct Code</small></th>
							<th><small>Description</small></th>
							<th><small>Amount</small>(${pivModel.pivDetail.currencyCode})</th>
		    			</tr>
		    			<c:forEach items="${pivModel.amountList}" var="amtGrid" varStatus="status">
		    			<tr>
							<td align="center">${status.count}</td>
							<td>${amtGrid.acCd}</td>
							<td>${amtGrid.acNm}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${amtGrid.amount}" /></td>
						</tr>					
						</c:forEach>
					
						<tr>
		    				<th colspan="3" ><small>Total</small></th>
		    				<td><fmt:formatNumber type="number"  maxFractionDigits="3" value="${pivModel.pivDetail.grandTotal}" /></td>
		    			</tr>
		    		</table>
		    		
		    		<h4>Customer Details</h4>
		    		<table class="table3" border="0">
		    			<tr>
		    				<th><small>Name</small></th><td>${pivModel.pivApplicant.name}</td>
		    			</tr>
		    			<tr>
		    				<th><small>Address</small></th><td>${pivModel.pivApplicant.address}</td>
		    			</tr>
		    			<tr>
		    				<th><small>ID type</small></th><td>${pivModel.pivApplicant.idType}</td>
		    			</tr>
		    			<tr>
		    				<th><small>ID number</small></th><td>${pivModel.pivApplicant.idNo}</td>
		    			</tr>
		    		</table>
		    			
		    		<h4>Representative Details</h4>
		    		<table class="table3" border="0">
		    			<tr>
		    				<th><small>Name of Representative</small></th><td>${pivModel.pivApplicant.collectPersonName}</td>
		    			</tr>
		    			<tr>
		    				<th><small>Representative ID Number</small></th><td>${pivModel.pivApplicant.collectPersonId}</td>
						</tr>
					</table>
					
	    		</div>
 		
		    	<div class="col-sm-6" >
		    		<div>
		    			<div class="row">
		    				<c:if test="${not empty pivModel.pivDetail.status}">
		    				<p id="p5">
								<strong>PIV Status:</strong> ${pivModel.pivDetail.status} PIV
							</p>
							</c:if>
						</div>
					</div>
				
					<div>
						<h4>PIV Payment Details</h4>
						<table class="table3" border="0">
				   			<tr>
				      		 	<th><small>Cheque  No :</small></th><td>${pivModel.pivDetail.chequeNo}</td>
				   			</tr>	
				   			<tr>
				   				<th><small>Bank Branch:</small></th><td>${pivModel.pivDetail.bankBranch1}</td>
				   			</tr>
				   			<tr>
				         		<th><small>Paid Branch:</small></th><td>${pivModel.pivDetail.bankBranch2}</td>
				        	</tr>
				        	<tr>
				         		<th><small>Paid Agent:</small></th><td>${pivModel.pivDetail.bankBranch2}</td>
				         	</tr>
				        </table>
		        
						<h4>PIV History</h4>
						<table class="table1 table-bordered ">
				    		
				    		<tr class="innerTableHeader">
				    				<th>#</th>
									<th><small>History code</small></th>
									<th><small>Dept ID</small></th>
									<th><small>Status</small></th>
									<th><small>User ID</small></th>
									<th><small>Updated time</small></th>
									
				    		</tr>
				    		<c:forEach items="${pivModel.historyList}" var="histList" varStatus="status">
					    		<tr>
									<td align="center">${status.count}</td>
									<td>${histList.historyCode}</td>
									<td>${histList.deptId}</td>
									<td>${histList.status}</td>
									<td>${histList.userId}</td>
									<td>${histList.updateTime}</td>
								</tr>						
							</c:forEach>
						</table>
				    </div>
    
			    	<div>
			    		<table  class="table3" border="0" ">
			    			<tr>
			          			<th><small>Issued Date</small></th><td>${pivModel.pivDetail.issuedDate}</td>
			       		 	</tr>
			       		 	<tr>
			          			<th><small>Expire Date</small></th><td>${pivModel.pivDetail.expiryDate}</td>
			       		 	</tr>
			       		 	<tr>
			          			<th><small>Paid Date</small></th><td>${pivModel.pivDetail.paidDate}</td>
			       		 	</tr>
			    		</table>
			    	</div>
			    	
			    	<div>
				    	<p>
				    	<small>Total Amount :</small>
				    	<strong>${pivModel.printCurrency}</strong>
				    	</p>
			    	</div>
    	
   				</div>   	
    		</div>
    	
		</div>
	</div>
</body>	
</html>