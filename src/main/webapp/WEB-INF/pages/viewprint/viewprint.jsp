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
			<h3 style="color:green;">${success}</h3>
        	<h4 style="color:powderblue;">${PDFname}</h4>
        	
        	<c:set var="myVar1" value="${pt}"/>
        	<c:set var="myVar2" value="${dt}"/>
        	
        	<form:form method="GET" action="findPiv" modelAttribute="pivModel"  >
		<body>
          <div>
          
            <strong>  PIV No. :</strong> <form:input name="pivNo" path="pivDetail.id.pivNo" size="30"  ></form:input>
           
            <input id="pivFormBtn" class="button" type="submit" name="actionButton" value="Find"  ></input>
            
            <div class="msgError"><c:out value="${pivModel.message}"></c:out>
            
            </div>
             
         </div>
         </body>
		</form:form> 	
      
        <div>
        
		<%@ include file="../view/pivPrintForm.jsp" %>
        	
        </div>
       
        		
        <div>
       
       <c:if test="${fn:indexOf(pivModel.mode, 'DT')>=0 }">
		    	<input onclick="openPopupCopy('${pivModel.pivDetail.id.pivNo}')" id="pivFormBtn" class="button" name="actionButton" type="button" value="Office Copy" ></input>
		    </c:if>
		    <c:if test="${fn:indexOf(pivModel.mode, 'PT')>=0 }">
		    	<input onclick="openPopup('${pivModel.pivDetail.id.pivNo}')" id="pivFormBtn" class="button" name="actionButton" type="button" value="Original Print" ></input>
		    </c:if>
		<c:if test="${fn:indexOf(pivModel.mode, 'CT')>=0 }">
			<c:if test="${pivModel.pivDetail.status=='P' }">
		    	<input onclick="openPopupPaid('${pivModel.pivDetail.id.pivNo}')" id="pivFormBtn" class="button" name="actionButton" type="button" value="Paid Copy" ></input>
		    </c:if>
		 </c:if>
		 
		
		
		<a href="../admin/mainmenu" class="button">Exit</a>
	

	</div>	
        	
        	
        
         	

      </div>          

</body>
</html>