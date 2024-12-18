<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
 
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/swimbi-vertical.css"/>"/>

<link rel="stylesheet"  href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>


<style>
table.table1 {
    
}
table.table1 th {
    background-color: #800000;
    color: white;
}
table.table1 th, td {
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
 </head>
<body>
 <div class="tblBody" >
   <div class="body-screen">
            
      <h3>PIV DETAILS****</h3>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6">
                    <table class="table2" border="0">
                        <tr>
                            <th><small>Piv No#### :</small></th>
                            <td>${pivModel.pivDetail.id.pivNo}</td>
                        </tr>
                        <tr>
                            <th><small>Piv Type :</small></th>
                            <td>${pivModel.pivDetail.titleCd}</td>
                        </tr>
                        <tr>
                            <th><small>Bank Reference No</small></th>
                            <td>${pivModel.pivDetail.bankCheckNo}</td>
                        </tr>
                        <tr>
                            <th><small>Reference No :</small></th>
                            <td>${pivModel.pivDetail.referenceNo}</td>
                        </tr>
                        <tr>
                            <th><small>Reference Type :</small></th>
                            <td>${pivModel.pivDetail.referenceType}</td>
                        </tr>
                        <tr>
                            <th><small>Description :</small></th>
                            <td>${pivModel.pivDetail.description}</td>
                        </tr>
                        <tr>
                            <th><small>Payment Mode :</small></th>
                            <td>${pivModel.pivDetail.paymentMode}</td>
                        </tr>
                    </table>
                    <table class="table3" border="0">
                        <tr>
                            <th><small>Cheque No :</small></th>
                            <td>${pivModel.pivDetail.chequeNo}</td>
                        </tr>
                        <tr>
                            <th><small>Bank Branch:</small></th>
                            <td>${pivModel.pivDetail.bankBranch1}</td>
                        </tr>
                        <tr>
                            <th><small>Paid Branch:</small></th>
                            <td>${pivModel.pivDetail.bankBranch2}</td>
                        </tr>
                        <tr>
                            <th><small>Paid Agent:</small></th>
                            <td>${pivModel.pivDetail.bankBranch2}</td>
                        </tr>
                    </table>
                    <h3>COLLECT PERSON INFO</h3>
                    <table class="table3" border="0">
                        <tr>
                            <th><small>Collect person Name</small></th>
                            <td>${pivModel.pivApplicant.collectPersonName}</td>
                        </tr>
                        <tr>
                            <th><small>ID number</small></th>
                            <td>${pivModel.pivApplicant.collectPersonId}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                    	<p id="p5"><strong>PIV Status:</strong>${pivModel.pivDetail.status}</p>
                        
    
                    </div>
                    <table class="table1 table-bordered ">
                        <tr>
                            <th><small>Issued Date</small></th>
                            <td>${pivModel.pivDetail.issuedDate}</td>
                        </tr>
                        <tr>
                            <th><small>Expire Date</small></th>
                            <td>${pivModel.pivDetail.expiryDate}</td>
                        </tr>
                        <tr>
                            <th><small>Paid Date</small></th>
                            <td>${pivModel.pivDetail.paidDate}</td>
                        </tr>
                    </table>
                    <table class="table1 table-bordered ">
                        <h4>TOTAL AMOUNT</h4>
                        <tr class="innerTableHeader">
                            <th>#</th>
                            <th><small>Acct Code</small></th>
                            <th><small>Description</small></th>
                            <th><small>Amount</small>(${pivModel.pivDetail.currencyCode})</th>
                        </tr>
                        <c:forEach items="${pivModel.amountList}" var="amtGrid" varStatus="status">
                            <tr>
                                <td align="center">${status.count}</td>
                                <td>${amtGrid.acCd}</td>
                                <td>${amtGrid.acNm}</td>
                                <td>${amtGrid.amount}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th colspan="3"><small>Total</small></th>
                            <td>${pivModel.pivDetail.grandTotal}</td>
                        </tr>
                    </table>
                    <h3>CUSTOMER INFO</h3>
                    <table class="table3" border="0">
                        <tr>
                            <th><small>Name</small></th>
                            <td>${pivModel.pivApplicant.name}</td>
                        </tr>
                        <tr>
                            <th><small>Address</small></th>
                            <td>${pivModel.pivApplicant.address}</td>
                        </tr>
                        <tr>
                            <th><small>ID type</small></th>
                            <td>${pivModel.pivApplicant.idType}</td>
                        </tr>
                        <tr>
                            <th><small>ID number</small></th>
                            <td>${pivModel.pivApplicant.idNo}</td>
                        </tr>
                    </table>
                </div>
            </div>
            
        </div>
        </div>
        </div>
        </body>
        </html>
        
     