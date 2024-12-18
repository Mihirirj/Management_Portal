<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form:form name="pivForm" id="pivForm" method="post" action="savePivDetail" modelAttribute="pivModel" >
<p>
	<form:label cssClass="field" path="pivDetail.paidBranch">Paid Agent Branch : </form:label>
	<form:select id="cmbAgentBranch"  path="pivDetail.paidBranch"    >
	<form:option value="-1" label="--- Select Branch ---"/>
	<form:options items="${pivModel.agentBranchList}" />
   
  	</form:select>
</p>
</form:form>
							
