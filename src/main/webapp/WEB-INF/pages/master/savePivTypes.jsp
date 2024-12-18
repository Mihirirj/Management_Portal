<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Privilege</title>

<link rel="stylesheet" type="text/css"	href="<c:url value="/resources/css/style.css"/>" />
<link rel="stylesheet" type="text/css"	href="<c:url value="/resources/css/table_style.css"/>" />
<link rel="stylesheet" type="text/css"	href="<c:url value="/resources/css/swimbi-vertical.css"/>" />
	
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>

	<script type="text/javascript">
	function deletePivType(titleCd, deptId) {
		var cfm = confirm("Do you want to remove this PIV type from "+deptId+"?");
		if(cfm)
		{
		    $.ajax({
		    	type: "POST",
		    	
		        url : 'deletePivType?titleCd='+titleCd,
		        data: $("#pivTypeForm").serialize(),
		        success : function(data) {
		            $('#authPivTypeList').html(data);
		        }
	        });
		    document.getElementById('pivTypeList').style.display = 'none';
		}
	}

	function addPivType(titleCd) {
	    
	    $.ajax({
	    	type: "POST",
	    	
	        url : 'addPivType?titleCd='+titleCd,
	        data: $("#pivTypeForm").serialize(),
	        success : function(data) {
	            $('#authPivTypeList').html(data);
	        }
	    });
	    document.getElementById('pivTypeList').style.display = 'none';
	}

    function viewAllPivTypes() {
        
        $.ajax({
        	type: "POST",
        	
            url : 'viewAllPivTypes',
            data: $("#pivTypeForm").serialize(),
            success : function(data) {
                $('#pivTypeList').html(data);
                
            }
        	
        });
        document.getElementById('pivTypeList').style.display = '';
    }

    function pageLoad()
    {
    	document.getElementById('pivTypeList').style.display = 'none';
    }

    function pageSubmit()
    {
    	pivTypeForm.submit();
 	}

    function confirmDelete()
    {
        var el = document.frmEstimate.elements;
        var total = 0;

    	for(var i = 0 ; i < el.length ; ++i) 
    	{
    		if(el[i].type == "checkbox" &&  el[i].name == "chkSelLabCode") 
    		{
    			if(el[i].checked) 
    			{
    				total += 1;
    				break;
    			}
    			
    		}
    	}
    	if (total == 0) 
    	{
    		alert("Please select labour item(s) to be removed!");
    		return false;
    	}
    	else
    	{
    		
    	}		

    }
     	
    </script>
    

</head>

<body onload="pageLoad()">
	<%@ include file="..//admin/header.jsp"%>

	<%@ include file="..//admin/menuv.jsp"%>

	<div class="tblBody">
		<div >
			<form:form action="viewDeptIdPivTypes" method="POST" id="pivTypeForm"
				modelAttribute="masterModel">
				
				
					
						<form:label cssClass="field" path="deptId">Cost Center: </form:label>
						<form:select id="cmbPivType" path="deptId" onchange="javascript:pageSubmit()">
						    <form:option value="-1" label="- Select -"/>
						    <form:options items="${masterModel.deptIdMap}" />
						</form:select>
						
						<br><br>
					
					
					
					
					<div id="authPivTypeList"><%@ include file="authPivTypeTbl.jsp" %></div>
					
					<a href="javascript:viewAllPivTypes()" >Add PIV Types</a>
					<div id="pivTypeList"><%@ include file="pivTypesTbl.jsp" %></div>
					
				
			</form:form>
			<br><br><a href="../admin/mainmenu" class="button">Exit</a>
		</div>
	</div>

	<%@ include file="..//admin/footer.jsp"%>
</body>
</html>