<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" sizes="76x76"
	href="../assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<title>Management Portal - New Connection</title>
<!-- Fonts and icons -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<!-- Nucleo Icons -->
<link href="../assets/css/nucleo-icons.css" rel="stylesheet" />
<link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<script src="https://kit.fontawesome.com/42d5adcbca.js"
	crossorigin="anonymous"></script>
<!-- Material Icons -->
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Round"
	rel="stylesheet">
<!-- CSS Files -->
<link id="pagestyle" href="../assets/css/material-dashboard.css?v=3.1.0"
	rel="stylesheet" />
<!-- Nepcha Analytics (nepcha.com) -->
<!-- Nepcha is a easy-to-use web analytics. No cookies and fully compliant with GDPR, CCPA and PECR. -->
<script defer data-site="YOUR_DOMAIN_HERE"
	src="https://api.nepcha.com/js/nepcha-analytics.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
	
	<title>Search Form</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
  
  	<style>
.error-message {
	color: red;
	font-size: 12px;
	text-align: left;
	margin-top: 5px;
	text-align: center;	
}

.centered-table {
	font-size: 11px;
	width: 100%;
	text-align: center;
}

.centered-table td {
	white-space: normal;
	word-wrap: break-word; 
}

.centered-table th {
	background-color: #e91e63;
	color: white; 
	text-align: center; 
	padding: 10px;
}
</style>
  
    
</head>
<body class="g-sidenav-show bg-gray-200">
	<aside
		class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"
		id="sidenav-main">
	<div class="sidenav-header">
		<i
			class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
			aria-hidden="true" id="iconSidenav"></i> <a class="navbar-brand m-0"
			href="https://demos.creative-tim.com/material-dashboard/pages/dashboard"
			target="_blank"> <img src="../assets/img/logo-ct.png"
			class="navbar-brand-img h-100" alt="main_logo"> <span
			class="ms-1 font-weight-bold text-white">Material Dashboard 2</span>
		</a>
	</div>
	<hr class="horizontal light mt-0 mb-2">
	<%@ include file="..//admin/mainmenu.jsp"%> </aside>
	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
	<!-- Navbar --> <%@ include file="..//admin/header.jsp"%>
	<!-- End Navbar --> 


    <form name="smcForm" id="smcForm" method="get" action="/MgtPortal/smc/searchMenu" onsubmit="return validateForm()">
        <div class="container-search py-4 form-container">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                                <h6 class="text-white text-capitalize ps-3">${Formheading}</h6>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive p-0">
                                <div id="validationMessages"></div>
                                <!-- Error display area -->
                                <c:if test="${not empty errors}">
                                    <div class="error-message" role="alert">
                                        <ul>
                                            <c:forEach var="error" items="${errors}">
                                                <li>${error.value}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:if>
                                <!-- Form fields -->
                                <div class="form-group">
                                    <label for="applicationNo">Application No (eg: 423.10/ANC/23/0012):</label>
                                    <input type="text" id="applicationNo" name="applicationNo">
                                    <div id="applicationNoError" class="error-message"></div>
                                </div>
                                <div class="form-group">
                                    <label for="estimationNo">Estimation No (eg:423.10/ENC/23/0012):</label>
                                    <input type="text" id="estimationNo" name="estimationNo">
                                    <div id="estimationNoError" class="error-message"></div>
                                </div>
                                <div class="form-group">
                                    <label for="jobNo">Job No (eg: 423.10/SMC/23/0012):</label>
                                    <input type="text" id="jobNo" name="jobNo">
                                    <div id="jobNoError" class="error-message"></div>
                                </div>
                                <div class="form-group">
                                    <label for="idNumber">ID Number :</label>
                                    <input type="text" id="idNumber" name="idNo">
                                    <div id="idNumberError" class="error-message"></div>
                                </div>
                                <div class="form-group">
                                    <label for="accountNo">Account No:</label>
                                    <input type="text" id="accountNo" name="accountNo">
                                    <div id="accountNoError" class="error-message"></div>
                                </div>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script>
        // Function to validate form inputs
        function validateForm() {
            var applicationNo = $("#applicationNo").val().trim();
            var estimationNo = $("#estimationNo").val().trim();
            var jobNo = $("#jobNo").val().trim();
            var idNumber = $("#idNumber").val().trim();
            var accountNo = $("#accountNo").val().trim();

            resetValidationMessages();

            var filledCount = [applicationNo, estimationNo, jobNo, idNumber, accountNo].filter(Boolean).length;

            if (filledCount !== 1) {
                displayErrorMessage("Please fill one field at a time.");
                return false;
            }

            var isValid = true;

            if (applicationNo && applicationNo.length !== 19) {
                $("#applicationNoError").text("Application No must be 19 characters long.").addClass('error-message');
                isValid = false;
            }
            if (estimationNo && estimationNo.length !== 19) {
                $("#estimationNoError").text("Estimation No must be 19 characters long.").addClass('error-message');
                isValid = false;
            }
            if (jobNo && jobNo.length !== 19) {
                $("#jobNoError").text("Job No must be 19 characters long.").addClass('error-message');
                isValid = false;
            }
            if (idNumber) {
                const idNumberPattern = /^[vV0-9]{10,12}$/;
                if (!idNumberPattern.test(idNumber)) {
                    $("#idNumberError").text("ID Number must be 10 or 12 characters long and can contain 'V' or 'v'.").addClass('error-message');
                    isValid = false;
                }
            }
            if (accountNo && accountNo.length !== 10) {
                $("#accountNoError").text("Account No must be 10 characters long.").addClass('error-message');
                isValid = false;
            }

            return isValid;
        }

        // Function to reset validation messages
        function resetValidationMessages() {
            $(".error-message").text("");
            $("#validationMessages").text("");
        }

        // Function to display error message
        function displayErrorMessage(message) {
            $("#validationMessages").text(message).addClass('error-message');
        } 
    </script>



	<!-- Display search results -->
<c:if test="${not empty rowList}">


<div id="tableContainer">
		<div class="row">
			<div class="col-12">
				<div class="card my-4">

					<div class="card-body px-0 pb-2">
						<div class="table-responsive p-0">
							<table class="table centered-table">
								<thead>
									<tr>
										<th>ID No</th>
										<th>Estimate No</th>
										<th>Account No</th>
										<th>Application No</th>
										<th>Application Type</th>
										<th>Project No</th>
										<th>Consumer Name</th>
										<th>Consumer Address</th>
										<th>Submitted Date</th>
										<th>SMC Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="row" items="${rowList}">
										<tr>
											<td>${row.idNo}</td>
											<td>${row.estimateNo}</td>
											<td>${row.accountNo}</td>
											<td>${row.applicationNo}</td>
											<td>${row.description2}-${row.description}</td>
											<td>${row.projectNo}</td>
											<td>${row.consumerName}</td>
											<td>${row.consumerAddress}</td>
											<td>${fn:substring(row.submitDate, 0, 10)}</td>
											<!-- Display only date -->
											<td>${row.smcStatus}</td>
										</tr>
									</c:forEach>

								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
    
    
</c:if>
<!-- Display error message if no results found -->
<c:if test="${not empty errorMessage}">
    <div class="alert alert-primnary" role="alert">
        ${errorMessage}
    </div>
</c:if>
	<div id="bottomList"></div>
	<%@ include file="..//admin/footer.jsp"%> </main>
	<div class="fixed-plugin">
		<a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
			<i class="material-icons py-2">settings</i>
		</a>
		<div class="card shadow-lg">
			<div class="card-header pb-0 pt-3">
				<div class="float-start">
					<h5 class="mt-3 mb-0">Material UI Configurator</h5>
					<p>See our dashboard options.</p>
				</div>
				<div class="float-end mt-4">
					<button
						class="btn btn-link text-dark p-0 fixed-plugin-close-button">
						<i class="material-icons">clear</i>
					</button>
				</div>
			</div>
			<hr class="horizontal dark my-1">
			<div class="card-body pt-sm-3 pt-0">
				<!-- Sidebar Backgrounds -->
				<div>
					<h6 class="mb-0">Sidebar Colors</h6>
				</div>
				<a href="javascript:void(0)" class="switch-trigger background-color">
					<div class="badge-colors my-2 text-start">
						<span class="badge filter bg-gradient-primary active"
							data-color="primary" onclick="sidebarColor(this)"></span> <span
							class="badge filter bg-gradient-dark" data-color="dark"
							onclick="sidebarColor(this)"></span> <span
							class="badge filter bg-gradient-info" data-color="info"
							onclick="sidebarColor(this)"></span> <span
							class="badge filter bg-gradient-success" data-color="success"
							onclick="sidebarColor(this)"></span> <span
							class="badge filter bg-gradient-warning" data-color="warning"
							onclick="sidebarColor(this)"></span> <span
							class="badge filter bg-gradient-danger" data-color="danger"
							onclick="sidebarColor(this)"></span>
					</div>
				</a>
				<!-- Sidenav Type -->
				<div class="mt-3">
					<h6 class="mb-0">Sidenav Type</h6>
					<p class="text-sm">Choose</p>
					<ul class="nav nav-sm flex-column">
						<li class="nav-item"><a href="javascript:void(0)"
							class="nav-link sidebar-style"> <span class="ms-3">Light
									Sidenav</span>
						</a></li>
						<li class="nav-item"><a href="javascript:void(0)"
							class="nav-link sidebar-style"> <span class="ms-3">Dark
									Sidenav</span>
						</a></li>
						<li class="nav-item"><a href="javascript:void(0)"
							class="nav-link sidebar-style"> <span class="ms-3">Semi
									Dark Layout</span>
						</a></li>
					</ul>
				</div>
				<!-- Navigation Positions -->
				<div class="mt-2">
					<h6 class="mb-0">Navigation Positions</h6>
					<p class="text-sm">Select where you want to place your sidenav</p>
					<div class="btn-group flex-wrap" role="group">
						<button type="button"
							class="btn btn-outline-secondary mb-3 position-me">Left</button>
						<button type="button"
							class="btn btn-outline-secondary mb-3 position-me">Right</button>
					</div>
				</div>
				<!-- End Navigation Positions -->
				<hr class="horizontal dark my-sm-4">
				<!-- Reset to Default -->
				<button type="button" class="btn btn-outline-dark btn-block">
					<i class="material-icons">settings_backup_restore</i> <span>Reset
						to Default</span>
				</button>
			</div>
		</div>
	</div>
	

</body>
</html>
