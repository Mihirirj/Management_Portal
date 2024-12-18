<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>viewprint</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>"/>

<link rel="stylesheet"
	href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" /><script
	src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
	<link rel="stylesheet"  href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>"/>
	<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>

<script>

$(function(){
    $('#download').hide();
});

function crunchifyAjax() {
    $.ajax({
        url : 'ajaxtest.html',
        success : function(data) {
            $('#result').html(data);
        }
    });
}


function openPopupCopy(pivNo)
{
	
       //var url="../issue/generateReport?pivNo="+pivNo;
       //var url="../viewprint/printInvoice";
      // if(pivNo.length>0)
       var url="../viewprint/printInvoiceCopy?pivNo="+pivNo;
       var width = 1100;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
    var windowFeatures = "width=" + width + ",height=" + height +  
        ",status,resizable,left=" + left + ",top=" + top +
        "screenX=" + left + ",screenY=" + top + ",scrollbars=yes";
       window.open(url, "subWind", windowFeatures, "POS");//window.open(url,"_blank","directories=no, status=no,width=1000, height=700,top=50,left=50", "scrollbars=1");
}

function openPopupPaid(pivNo)
{
	
       //var url="../issue/generateReport?pivNo="+pivNo;
       //var url="../viewprint/printInvoice";
      // if(pivNo.length>0)
       var url="../viewprint/printInvoicePaidCopy?pivNo="+pivNo;
       var width = 1100;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
    var windowFeatures = "width=" + width + ",height=" + height +  
        ",status,resizable,left=" + left + ",top=" + top +
        "screenX=" + left + ",screenY=" + top + ",scrollbars=yes";
       window.open(url, "subWind", windowFeatures, "POS");//window.open(url,"_blank","directories=no, status=no,width=1000, height=700,top=50,left=50", "scrollbars=1");
}


function openPopup(pivNo)
{
	
       //var url="../issue/generateReport?pivNo="+pivNo;
       //var url="../viewprint/printInvoice";
      // if(pivNo.length>0)
       var url="../viewprint/printInvoice?pivNo="+pivNo;
       var width = 1100;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
    var windowFeatures = "width=" + width + ",height=" + height +  
        ",status,resizable,left=" + left + ",top=" + top +
        "screenX=" + left + ",screenY=" + top + ",scrollbars=yes";
       window.open(url, "subWind", windowFeatures, "POS");//window.open(url,"_blank","directories=no, status=no,width=1000, height=700,top=50,left=50", "scrollbars=1");
}

function printing()
{
	alert(document.pivApplet);
	document.pivApplet.print(true);
}
</script>
 
</head>



	<body>
		<%@ include file="../admin/header.jsp" %>
		
		<%@ include file="../admin/menuv.jsp" %>
		
        <div class="tblBody">
        	<div class="body-screen">
			
        	
        	<form:form method="GET" action="formSubmit" modelAttribute="pivModel"  >
			<body>
          		<div>
          
            <strong>  PIV No. (to be setoff):</strong> <form:input name="pivNo" path="pivNoFind" size="30"  ></form:input>
           <form:hidden path="pivDetail.id.pivNo" />
            <input id="pivFormBtn" class="button" type="submit" name="findButton" value="Find"  ></input>
            
            
             
         </div>
         <div>
         <strong>  PIV No. (Setoff From):</strong> <form:input path="pivDetail.setoffFrom" size="30"  ></form:input>
           
         </div>
         
         <c:set var="msgType" value="${pivModel.messageType}"/>

			<c:if test="${msgType =='ERROR'}">
				<div class="msgError"><c:out value="${pivModel.message}"></c:out></div>
			</c:if>
			<c:if test="${msgType =='SUCCESS'}">
				<div class="msgSuccess"><c:out value="${pivModel.message}"></c:out></div>
			</c:if>
			<c:if test="${msgType =='INFO'}">
				<div class="msgInfo"><c:out value="${pivModel.message}"></c:out></div>
			</c:if>
        <br>
            
         </body>
		
      
        <div>
        
		<%@ include file="../view/pivPrintForm.jsp" %>
        	
        </div>
       
        		
        <div>
       	<c:choose>
       		<c:when test="${pivModel.mode=='SO'}">
       	    	<input id="pivFormBtn" class="button" name="setoffButton" type="submit" value="Set-Off" ></input>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		<a href="../admin/mainmenu" class="button">Exit</a>
	

	</div>	
       </form:form> 	 	
        	
        
         	

      </div>          
</div>
</body>
</html>