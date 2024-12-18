<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="post" action="" modelAttribute="pivModel">
	
	<p>

	<table class="innerTable" border="1">
		<tr class="innerTableHeader">
			<th width="30">Seq. No</th>
	  		
	  		<th width="60">Payment Mode</th>
	  		<th width="60">Cheque No.</th>
	  		<th width="60">Cheque Bank</th>
	  		<th width="60">Cheque Date</th>
	  		<th width="60">Paid Amount</th>
	  		<th width="30"></th>
		</tr>
		<c:forEach items="${pivModel.paymentList}" var="pivPayemnt">
			<tr>
	  			<td>
       				${pivPayemnt.id.seqNo}
	  			</td>
	  			
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
	  				${pivPayemnt.chequeBankCode} - ${pivPayemnt.chequeBankBranch}
	  			</td>
	  			<td>
	  				${pivPayemnt.chequeDate}
	  			</td>
				<td align="right">
	  				${pivPayemnt.paidAmount}
	  			</td>
	  			<td><a href="javascript:deletePayment('${pivPayemnt.id.seqNo}')" >Delete</a></td>
			</tr>
			
		</c:forEach>
		<tr>
				<td colspan="5"><b>Total</b>
				</td>
				<td align="right">
				<b><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2"  maxFractionDigits="2" value="${pivModel.pivDetail.paidAmount}" /></b>
				</td>
				</tr>
	</table>
	</p>
</form:form>