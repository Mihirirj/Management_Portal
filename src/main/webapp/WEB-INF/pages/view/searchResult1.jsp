<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
	var msg = "${pivModel.messageType}";
	if(msg == 'SUCCESS'){
		$("#msgModal").modal()
	}
</script>

<script>
	/*var table = $('#srchResltTbl').DataTable();
	 
	$('#srchResltTbl tbody').on( 'click', 'tr', function () {
		var pivNum = table.row( this ).data()[0];
		
		var len = pivNum.length;
		
		var pivNumber = pivNum.substr(12, 22);
		
		 
	    $.ajax({
			type : "GET",
			url : 'viewResult',
			data : { pivNo: pivNumber },
			success : function(response) {
				$('#myModal').html(response).modal();
				console.log("Success");
			},
			error : function() {
				console.log("Error");
			},
	});
	} );
*/

$(document).ready(function(){
	  
    $(".pivlink").on('click',function(){
    	
         var currentRow=$(this).closest("tr");
         
         var pivNum = currentRow.find(".pivlink").html();
        var pivNum=pivNum.trim();
         alert(pivNum);
  
         $.ajax({
                type : "GET",
                url : 'viewResult',
                data : {
                		pivNo:pivNum
                },
               
         success : function(response) {
             $('#myModal').html(response).modal();
            
         },
         error : function() {
             $('#myModal').html('<p>An error has occurred</p>');
             console.log("Error Link");
            
             alert("error"+no);
         }
            });
    });
});
</script>
<div class="container-fluid py-4 form-container">
<table id="srchResltTbl" class="innerTable">
	<thead>
		<tr class="innerTableHeader">
			<th width="200">PIV No</th>
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
					
						
						<td width="200" ><a href="#" class="pivlink">${pivlist.pivDetail.id.pivNo}</a></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pivlist.pivDetail.pivDate}"/></td>
						<td>${pivlist.pivDetail.titleCd}</td>
						<td>${pivlist.idNo}</td>
						<td>${pivlist.name}</td>
						<td>${pivlist.pivDetail.status}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</div>


<!-- PIV Detail Modal -->

<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
        </div>
        <div class="modal-footer">
                <button type="button" class="button" data-dismiss="modal">X</button>
            </div>
        
    </div>
</div>

