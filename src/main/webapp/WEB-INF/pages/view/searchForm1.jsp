<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css"/>" />


<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepicker.js"/>"></script>

<script type="text/javascript">
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

    
</script>
</head>
<body>

	<%@ include file="..//admin/header.jsp"%>

	<%@ include file="..//admin/menuv.jsp"%>

	<div class="tblBody">
		
			<form:form action="SubmitSearchForm" method="POST" id="searchForm" modelAttribute="pivModel">
				<table>
					<tr>
						<td width="30%">
							<form:label cssClass="labelfont" path="pivDetail.id.pivNo">PIV Number</form:label>
						</td>
						<td>
							<form:input path="pivDetail.id.pivNo" />
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
							<form:label cssClass="labelfont" path="pivDetail.issuedDate">Issued Date</form:label>
						</td>
						<td>
							<form:input path="pivDetail.pivDate" id="issuedfrom" ></form:input>
							<label Class="labelfont"> - </label> 
							<input name="issuedTo" id="issuedto">
						</td>
					</tr>
					<tr>
						<td width="10%">
							<form:label cssClass="labelfont" path="pivDetail.paidDate">Paid Date</form:label>
						</td>
						<td>
							<form:input path="pivDetail.paidDate" id="paidfrom" ></form:input>
							<label Class="labelfont"> - 
							</label> 
							<input name="paidTo" id="paidto">
						</td>
					</tr>
					<tr>
						<td colspan="2"	>
							<button class="button" type="button" onclick="javascript:myFunction()">Search</button>
							<input class="button" type="submit"  name="actionButton" value="Clear"></input> 
							
						</td>
					</tr>
				</table>
			</form:form>

			<div id="srchResult">
				<%@ include file="searchResult.jsp"%>
			</div>
		
	</div>
	<%@ include file="..//admin/footer.jsp"%>

<script>
    $(document).ready(function() {
        $('#srchResltTbl').dataTable();
    });
    
    $(document).ready(function() {
        $('#issuedfrom').datepicker({
            format: "dd/mm/yyyy",
            //startDate: new Date(), //to disable past dates
            endDate: new Date() //to disable future dates
        });
    });
    
    $(document).ready(function() {
        $('#paidfrom').datepicker({
            format: "dd/mm/yyyy",
            //startDate: new Date(), //to disable past dates
            endDate: new Date() //to disable future dates
        });
    });
    
    $(document).ready(function() {
        $('#issuedto').datepicker({
            format: "dd/mm/yyyy",
            //startDate: new Date(), //to disable past dates
            endDate: new Date() //to disable future dates
        });
    });
    
    $(document).ready(function() {
        $('#paidto').datepicker({
            format: "dd/mm/yyyy",
            //startDate: new Date(), //to disable past dates
            endDate: new Date() //to disable future dates
        });
    });
</script>
</body>
</html>