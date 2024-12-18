<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
	<title>PIV DETAILS</title>
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


<!--  font-size: .80em; -->
<body>
	<table width="100%" >
		<tr height="20" bgcolor="#F5E4E4">
						
			<td> <strong><small>PIV Number :&nbsp;</small>${pivModel.pivDetail.id.pivNo}
        			
        			<c:if test="${not empty pivModel.pivDetail.status}">
							&nbsp;<font color="red"> (${pivModel.statusDesc})</font>
	
			</c:if>
			 </strong>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" >
					
					<tr>
						<td>
							<table class="table2" border="0"   >
		        	 			<input type="hidden" name="pivno" value="${pivModel.pivDetail.id.pivNo}"/>
				        	 	
		           		
				           		<tr height="20" >
				          			<td> <small>PIV Type</small></td>
				          			<td width="5">:</td>
				          			<td>${pivModel.pivDetail.titleCd}</td>
				           		</tr>
				          	 	<tr height="20" > 
				          			<td><small>Bank Reference No</small> </td>
				          			<td width="5">:</td>
				          			<td>${pivModel.pivDetail.bankCheckNo}</td>
					          	</tr>
					          	<tr height="20" >
				          			<td><small>Reference No</small></td>
				          			<td width="5">:</td>
				          			<td>${pivModel.pivDetail.referenceNo}</td>
				          		</tr>
				          		<tr>
				          			<td><small>Description</small></td>
				          			<td width="5">:</td>
				          			<td>${pivModel.pivDetail.description}</td>
				       		 	</tr>
				       			 
				       		 	<tr>
				          			<td><small>Issued Date</small></td>
				          			<td width="5">:</td>
				          			<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${pivModel.pivDetail.issuedDate}"/></td>
				       		 	</tr>
				       		 	<tr>
				          			<td><small>Expiry Date</small></td>
				          			<td width="5">:</td>
				          			<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pivModel.pivDetail.expiryDate}"/></td>
				       		 	</tr>
				       		 	  		 
				    		</table> 
						</td>
						<td valign="top">
						<br>
						<table   class="paddingTable" >
						<tr><td>
							<table  class="innerTable" border="1">
		    		
				    			<tr  class="innerTableHeader">
				    				<th>#</th>
									<th><small>Acct Code</small></th>
									<th><small>Description</small></th>
									<th><small>Amount</small>(${pivModel.pivDetail.currencyCode})</th>
				    			</tr>
				    			<c:forEach items="${pivModel.amountList}" var="amtGrid" varStatus="status">
				    			<tr class="innerTableRow">
									<td align="center">${status.count}</td>
									<td align="center">${amtGrid.acCd}</td>
									<td align="left">${amtGrid.acNm}
										<c:if test="${amtGrid.taxValue >0}">
										 	(${amtGrid.taxValue})
										</c:if>
									</td>
									<td align="right">${amtGrid.amount}</td>
								</tr>					
								</c:forEach>
					
								<tr class="innerTableRow">
				    				<td colspan="3" ><small>Total</small></td>
				    				<td align="right"><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2"  maxFractionDigits="2" value="${pivModel.pivDetail.grandTotal}" /></td>
				    			</tr>
				    			<tr class="innerTableRow">
							    	<td colspan="4" >${pivModel.printCurrency}</td>
							    	
							    	
						    	</tr>
				    		</table>
				    		</td>
				    		</tr>
				    		</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="20" >
			<td>
			&nbsp;
			</td>
		</tr>
		<tr height="20" bgcolor="#F5E4E4">
			<td>
			<strong>&nbsp;Customer Details</strong>
			</td>
		</tr>
		
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td>
							
				    		<table class="paddingTable" >
				    			<tr>
				    				<td><small>Name</small></td>
				    				<td width="5">:</td>
				    				<td>${pivModel.pivApplicant.name}</td>
				    			</tr>
				    			<tr>
				    				<td><small>Address</small></td>
				    				<td width="5">:</td>
				    				<td>${pivModel.pivApplicant.address}</td>
				    			</tr>
				    			<tr>
				    				<td><small>ID Type</small></td>
				    				<td width="5">:</td>
				    				<td>
				    					<c:choose>
										    <c:when test="${pivModel.pivApplicant.idType=='N'}">
										       NIC
										    </c:when>    
										    <c:when test="${pivModel.pivApplicant.idType=='P'}">
										        Passport
										    </c:when>
										    <c:when test="${pivModel.pivApplicant.idType=='B'}">
										        Business Reg. No.
										    </c:when>
										    <c:when test="${pivModel.pivApplicant.idType=='O'}">
										        Other
										    </c:when> 
										</c:choose>  
				    				</td>
				    			</tr>
				    			<tr>
				    				<td><small>ID Number</small></td>
				    				<td width="5">:</td>
				    				<td>${pivModel.pivApplicant.idNo}</td>
				    			</tr>
				    		</table>
						</td>
						<td valign="top">
							
				    		<table class="paddingTable"  >
				    			<tr>
				    				<td><small>Name of Representative</small></td>
				    				<td width="5">:</td>
				    				<td>${pivModel.pivApplicant.collectPersonName}</td>
				    			</tr>
				    			<tr>
				    				<td><small>Representative ID Number</small></td>
				    				<td width="5">:</td>
				    				<td>${pivModel.pivApplicant.collectPersonId}</td>
								</tr>
								<tr>
				    				<td><small>VAT Reg No.</small></td>
				    				<td width="5">:</td>
				    				<td>${pivModel.pivApplicant.vatRegNo}</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="20" >
			<td>
			&nbsp;
			</td>
		</tr>
		<tr height="20" bgcolor="#F5E4E4">
			<td>
			<strong>&nbsp;Payment Details</strong>
			</td>
		</tr>
		
		<tr>
			<td>
				
				<table class="paddingTable"  >
					<tr>
	          			<td><small>Paid Date</small></td>
	          			<td width="5">:</td>
	          			<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pivModel.pivDetail.paidDate}" ></fmt:formatDate></td>
	       		 	</tr>
	       		 	<tr>
		   				<td><small>Paid Agent</small></td>
		   				<td width="5">:</td>
		   				<td>${pivModel.pivDetail.paidAgent} - ${pivModel.pivDetail.paidBranch}</td>
		   			</tr>
					<tr>
					<td colspan="3">
						<table class="innerTable" border="1">
							<tr class="innerTableHeader">
								<th width="60">Payment Mode</th>
						  		<th width="60">Cheque No.</th>
						  		<th width="60">Cheque Date.</th>
						  		<th width="100">Cheque Bank</th>
						  		<th width="60">Paid Amount</th>
						  		
							</tr>
							<c:choose>
						    	<c:when test="${fn:length(pivModel.paymentList) gt 0}">
									<c:forEach items="${pivModel.paymentList}" var="pivPayemnt">
										<tr class="innerTableRow">
								  			<td>
								  				<c:choose>
												    <c:when test="${pivPayemnt.paymentMode=='C'}">
												       Cash
												    </c:when>    
												    <c:when test="${pivPayemnt.paymentMode=='Q'}">
												        Cheque
												    </c:when>
												    <c:when test="${pivPayemnt.paymentMode=='D'}">
												        Bank Draft
												    </c:when>
												    <c:when test="${pivPayemnt.paymentMode=='R'}">
												        Credit Card
												    </c:when> 
												    <c:when test="${pivPayemnt.paymentMode=='M'}">
												        Direct Transfer
												    </c:when> 
												    <c:when test="${pivPayemnt.paymentMode=='B'}">
												        Cheque Deposited To Bank
												    </c:when>
												</c:choose>   
								  				
								  			</td>
								  			<td>
								  				${pivPayemnt.chequeNo}
								  			</td>
								  			<td>
								  				<fmt:formatDate pattern="dd/MM/yyyy" value="${pivPayemnt.chequeDate}" ></fmt:formatDate>
								  			</td>
								  			<td>
								  				${pivPayemnt.chequeBankCode} - ${pivPayemnt.chequeBankBranch}
								  			</td>
											<td align="right">
												<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" groupingUsed="true" value="${pivPayemnt.paidAmount}"></fmt:formatNumber>
								  				
								  			</td>
								  			
										</tr>
					
									</c:forEach>
									<tr class="innerTableRow">
										<td colspan="4">Total
										</td>
										<td align="right"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" groupingUsed="true" value="${pivModel.pivDetail.paidAmount}"></fmt:formatNumber></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr class="innerTableRow">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
					</td>
			</tr>
		        </table>
			</td>
		</tr>
		<tr height="20" >
			<td>
			&nbsp;
			</td>
		</tr>
		<tr height="20" bgcolor="#F5E4E4">
			<td>
			<strong>&nbsp;History</strong>
			</td>
		</tr>
		
		<tr>
			<td>
			<table class="paddingTable" >
			<tr><td>
			<table class="innerTable" border="1">
							<tr class="innerTableHeader">
								<th>#</th>
								<th>Status</th>
						  		<th>User</th>
						  		<th>Changed Date</th>
						  		<th>Remark</th>
						  		
							</tr>
			
								  			
	    				
	    		<c:forEach items="${pivModel.historyList}" var="histList" varStatus="status">
		    		<tr  class="innerTableRow" >
						<td align="center">${status.count}</td>
						<td align="left">${histList.status}</td>
						<td align="left">${histList.userId}</td>
						<td align="left"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${histList.updateTime}"/></td>
						<td align="left">${histList.description}</td>
					</tr>						
				</c:forEach>
			</table>
			</td></tr></table>
			</td>
		</tr>
		<tr height="20" >
			<td>
			&nbsp;
			</td>
		</tr>
	</table>
	
</body>	
</html>