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
		
		<c:set var="seqNo" value="0"/> 
		<c:set var="total" value="0"/>
		<c:forEach items="${response.payment}" var="pivPayemnt">
		  <c:set var="seqNo" value="${seqNo+1}"/>
		
			<tr>
	  			<td>
       				${seqNo}
	  			</td>
	  			
	  			<td>
	  				<c:choose>
					    <c:when test="${pivPayemnt.payMode=='C'}">
					       Cash
					    </c:when>    
					    <c:when test="${pivPayemnt.payMode=='Q'}">
					        Cheque
					    </c:when>
					    <c:when test="${pivPayemnt.payMode=='D'}">
					        Bank Draft
					    </c:when>
					    <c:when test="${pivPayemnt.payMode=='R'}">
					        Credit Card
					    </c:when> 
					     <c:when test="${pivPayemnt.payMode=='M'}">
					        Direct Transfer
					    </c:when> 
					    <c:when test="${pivPayemnt.payMode=='B'}">
					        Cheque Deposited To Bank
					    </c:when>
					</c:choose>   
	  				
	  			</td>
	  			<td>
	  				${pivPayemnt.chequenum}
	  			</td>
	  			<td>
	  				${pivPayemnt.chequeBank} - ${pivPayemnt.chequeBankBranch}
	  			</td>
	  			<td>
	  				${pivPayemnt.saved_date}
	  			</td>
				<td align="right">
	  				${pivPayemnt.tranAmt}
	  			</td>
	  			<td><a href="javascript:deletePayment('${pivPayemnt.count_no}')" >Delete</a></td>
	  			
	  			<c:set var="total" value="${total+pivPayemnt.tranAmt}"/>
			</tr>
						
		</c:forEach>
		<tr>
				<td colspan="5"><b>Total</b>
				</td>
				<td align="right">
				<b><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2"  maxFractionDigits="2" value="${total}" /></b>
				</td>
				</tr>
	</table>
	</p>
</form:form>