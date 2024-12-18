<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
table.table1 {
	
}

.innerTableHeader {
	text-align: left;
	padding: 8px;
	background-color: #A10000;
	color: white;
}

table.table1 td {
	text-align: right;
	padding: 8px;
}

table.table2 td, th {
	text-align: left;
	padding: 8px;
}

table.table3 td, th {
	text-align: left;
	padding: 8px;
}

#p1 {
	background-color: rgba(255, 0, 0, 0.3);
	padding: 12px;
}

#p2 {
	background-color: rgba(0, 255, 0, 0.3);
	padding: 12px;
}

#p3 {
	background-color: rgba(0, 0, 255, 0.3);
	padding: 12px;
}

#p4 {
	background-color: rgba(192, 192, 192, 0.3);
	padding: 12px;
}

#p5 {
	background-color: rgba(255, 255, 0, 1.9);
	padding: 12px;
}

#p6 {
	background-color: rgba(255, 0, 255, 0.3);
}
</style>

<div class="tblBody">
	<div class="body-screen">
		<div class="modal-header" align="center">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4>PIV Details</h4>
		</div>
		<div class="modal-body"  >
			<%@ include file="../view/pivPrintForm.jsp"%>
		</div>
		<div class="modal-footer">
			<button type="button" class="button" data-dismiss="modal">Close</button>
		</div>
	</div>
</div>
