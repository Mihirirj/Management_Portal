<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NO ACCESS</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table_style.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>" />

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>" />


<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/piv-main.js"/>"></script>

<link rel="stylesheet"  href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>"/>

<link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" />

<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>

</head>
<style>
.msgErrorr{

    color:red;
    font-family: "Verdana", Times, serif;
    font-size: 30px;
    font-weight: bold;
    text-align: center;
}
</style>
<body>

	<%@ include file="..//admin/header.jsp"%>
	<%@ include file="..//admin/menuv.jsp"%>

    		
			<p class="msgErrorr">You do not have access to this page.</p>
			
<br></br>
<%@ include file="..//admin/footer.jsp"%>
</body>
</html>