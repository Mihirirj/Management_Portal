<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="post" action="" modelAttribute="pivModel">
	
	
<table width="50%">
<tr><td >
	<table class="innerTable" border="1" >
		<tr class="innerTableHeader">
			<th width="12%">PIV Type</th>
			<th width="68%">Description</th>
			<th width="20%">PIV Type Code</th>
			<th width="10%">Add</th>
		</tr>
		<c:choose>
			<c:when test="${empty masterModel.pivTypeList }">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			</c:when>
			<c:otherwise>
			<c:forEach items="${masterModel.pivTypeList}" var="pivType">
				<tr class="innerTableRow">
		  			<td>
	      				${pivType.titleCd}
	  				</td>
	  				<td nowrap="nowrap">
	      				${pivType.titleNm}
	  				</td>
		  			<td>
	      				${pivType.pivCd}
	  				</td>
		  			
		  			<td align="center">
		  			<a  href="javascript:addPivType('${pivType.titleCd}')" class="viewrow"><img alt="Add" height="20" width="20" src="<c:url value="../resources/image/addPivType.png"/>"/></a> 
		  			
		  			</td>
				</tr>
				
			</c:forEach>
			</c:otherwise>
		</c:choose>
		
	</table>
	</td>
	</tr>
</table>
</form:form>