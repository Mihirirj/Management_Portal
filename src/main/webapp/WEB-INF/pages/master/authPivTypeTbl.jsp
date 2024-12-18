<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form method="post" action="" modelAttribute="pivModel">
	
<table width="50%">
<tr><td >	

	<table class="innerTable" border="1" width="100%">
		<tr class="innerTableHeader">
			<th width="12%">PIV Type</th>
			<th width="68%">Description</th>
			<th width="20%">PIV Type Code</th>
			<th width="10%">Delete</th>
		</tr>
		<c:choose>
			<c:when test="${empty masterModel.pivTypeAuthList }">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${masterModel.pivTypeAuthList}" var="pivType">
					<tr>
			  			<td>
		      				${pivType.id.titleCd}
		  				</td>
		  				<td nowrap="nowrap">
		      				${pivType.gltitlm.titleNm}
		  				</td>
			  			<td>
		      				${pivType.gltitlm.pivCd}
		  				</td>
			  			
			  			<td align="center">
			  			<a  href="javascript:deletePivType('${pivType.id.titleCd}','${pivType.id.deptId}')" class="viewrow"><img alt="Delete" height="20" width="20" src="<c:url value="../resources/image/delete.png"/>"/></a> 
			  			</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
	</table>
</td></tr></table>
</form:form>