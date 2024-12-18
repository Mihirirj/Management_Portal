<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	var msg = "${pivModel.messageType}";
	if(msg == 'SUCCESS'){
		$("#msgModal").modal()
	}
	/*else if(msg == 'ERROR'){
		$("#msgModal").modal()
	}*/
</script>

<script>
	var table = $('#srchResltTbl').DataTable();
	 
	$('#srchResltTbl tbody').on( 'click', 'tr', function () {
		var currentRow=$(this).closest("tr");
		 var x = currentRow.find(".idrow").html();
		 var pivNum=x.trim();
		
		
		
	    /*	    
	    $.ajax({
			type : "GET",
			url : 'viewResult',
			data : { pivNo: pivNum },
			success : function(response) {
				$('#myModal').html(response).modal();
				console.log("Success");
			},
			error : function() {
				console.log("Error");
			},
	});*/
		$.ajax({
            type : "GET",
            //url : 'viewResult',
            url : '../admin/popupView',
            data : {
            		pivNo:pivNum
            },
           
		     success : function(response) {
		         $('#myModal').html(response).modal();
		        
		     },
		     error : function() {
		         $('#myModal').html('<p>An error has occurred</p>');
		         console.log("Error Link");
		        
		         
		     }
        });
	} );
</script>
<table id="srchResltTbl" class="innerTable">
	<thead>
		<tr class="innerTableHeader">
			<th width="150">PIV No</th>
			<th width="150">Bank Ref. No</th>
			<th>Issued Date</th>
			<th>PIV Type</th>
			<th>Customer ID No</th>
			<th>Customer's Name</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty pivList }">
			</c:when>
			<c:otherwise>
				<c:forEach items="${pivList}" var="pivlist">
					<tr class="innerTableRow" >
					
						
						<td width="150" ><a href="#" class="idrow">${pivlist.pivDetail.id.pivNo}</a></td>
						<td width="150" >${pivlist.pivDetail.bankCheckNo}</td>
						<td width="20"><fmt:formatDate pattern="dd/MM/yyyy" value="${pivlist.pivDetail.pivDate}"/></td>
						<td>${pivlist.pivDetail.titleCd}</td>
						<td>${pivlist.idNo}</td>
						<td>${pivlist.name}</td>
						<td width="20">${pivlist.pivDetail.status}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
