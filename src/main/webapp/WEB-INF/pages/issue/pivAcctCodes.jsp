<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
<tr><td>
						
		<c:if test="${IsVATLiable=='true'}">	
		<div class="msgSuccess">-------------- VAT Liable PIV -------------- </div>
		</c:if>					
							
</td></tr>
<tr><td>
<table class="innerTable" border="1">
						  	<tr class="innerTableHeader">
							    
							    <th width="30">Acct Code</th>
							    <th width="100">Description</th>
							    <th width="50">Amount</th>
						  	</tr>
						  	
  							<c:set var="amtListVar" value="no" /> 
							<c:forEach items="${pivModel.amountList}" var="amtGrid" varStatus="status">
								<c:set var="amtListVar" value="yes"/> 
								<tr>
									
									<td>${amtGrid.acCd}
									<form:hidden id="sortKey${amtGrid.acCd}" path="pivModel.amountList[${status.index}].sortKey"  />
									<form:hidden path="pivModel.amountList[${status.index}].acCd"  />
									</td>
									<td >
										${amtGrid.acNm}
										<form:hidden path="pivModel.amountList[${status.index}].acNm"  />
										<form:hidden id="taxVal${amtGrid.acCd}" path="pivModel.amountList[${status.index}].taxValue" />
										<form:hidden path="pivModel.amountList[${status.index}].acNm"  />
										
										 <c:if test="${amtGrid.isTax =='Y'}">
														 (${amtGrid.taxValue})
														 
														</c:if>
										 
										
									</td>
									<td align="right">
										<c:if test="${amtGrid.isTax =='Y'}">
										<form:input size="15" id="${amtGrid.acCd}" readonly="true" cssStyle="text-align:right" onkeyup="numericValidate(this,event)" onkeypress="return restrictToTwoDecimalPossition(this,event);"   path="pivModel.amountList[${status.index}].amount" />
										</c:if>
										<c:if test="${amtGrid.isTax !='Y'}">
										<form:input size="15" id="${amtGrid.acCd}" cssStyle="text-align:right" onkeyup="numericValidate(this,event);" onkeypress="return restrictToTwoDecimalPossition(this,event);" onblur="calculateTotal('${amtGrid.acCd}','${amtGrid.sortKey}');formatValue(this)"  path="pivModel.amountList[${status.index}].amount" />
										</c:if>
									</td>
								</tr>
								
							</c:forEach>
							<c:if test="${amtListVar=='no'}"> 
							<tr>
									<td >&nbsp;</td>
									<td >&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</c:if>
 							<c:if test="${amtListVar=='yes'}"> 
 							<tr>
								<td colspan="2">Total</td>
								<td align="right" ><form:input size="15" cssStyle="text-align:right" id="pivTotalTxt"  path="pivModel.pivTotal" readonly="true"></form:input></td>
							</tr>
							</c:if>
							
						</table>
						<c:if test="${myVar =='CONFIRM'}"> 
						<script type="text/javascript">
						document.getElementById('mew').disabled=true;
						
						</script>
						
						</c:if>
				</td>
			</tr>
		</table>
