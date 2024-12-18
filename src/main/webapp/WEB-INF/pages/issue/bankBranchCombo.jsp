<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form:form name="pivForm" id="pivForm" method="post" action="savePivDetail" modelAttribute="pivModel" >
<p>
	<form:label cssClass="field" path="chequeBranch">Paid Agent Branch : </form:label>
	<form:select id="cmbChequeBranch"  path="chequeBranch"    >
	<form:option value="-1" label="--- Select Bank Branch ---"/>
	<form:options items="${pivModel.bankBranchList}" />
   
  	</form:select>
</p>
</form:form>
							
