<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">
      <c:choose>
			<c:when test="${menu == '1'}">
              <li class="nav-item">
		          <a class="nav-link text-white active bg-gradient-primary" >
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">dashboard</i>
		            </div>
		            <span class="nav-link-text ms-1">Dashboard</span>
		          </a>
		        </li>
	       	</c:when>
	       	<c:otherwise>
       			<li class="nav-item">
		          <a class="nav-link text-white " href="../welcome/">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">dashboard</i>
		            </div>
		            <span class="nav-link-text ms-1">Dashboard</span>
		          </a>
		        </li>
       		</c:otherwise>
       </c:choose>
       
       
        <c:choose>
			<c:when test="${menu == '2'}">
	        <li class="nav-item">
	          <a class="nav-link text-white active bg-gradient-primary" href="../smc/newConnection">
	            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
	              <i class="material-icons opacity-10">table_view</i>
	            </div>
	            <span class="nav-link-text ms-1">New Connection</span>
	          </a>
	        </li>
	        </c:when>
	        <c:otherwise>
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../smc/newConnection">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">table_view</i>
		            </div>
		            <span class="nav-link-text ms-1">New Connection</span>
		          </a>
		        </li>
	        	
	        </c:otherwise>
	    </c:choose>
	    
	            <c:choose>
			<c:when test="${menu == '3'}">
	        <li class="nav-item">
	          <a class="nav-link text-white active bg-gradient-primary" href="../welcome/">
	            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
	              <i class="material-icons opacity-10">receipt_long</i>
	            </div>
	            <span class="nav-link-text ms-1">Solar</span>
	          </a>
	        </li>
	        </c:when>
	        <c:otherwise>
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../welcome/">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">receipt_long</i>
		            </div>
		            <span class="nav-link-text ms-1">Solar</span>
		          </a>
		        </li>
	        	
	        </c:otherwise>
	    </c:choose>
	    
	    
	<!--      <c:choose>
			<c:when test="${menu == '3'}">
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../welcome/">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">receipt_long</i>
		            </div>
		            <span class="nav-link-text ms-1">Solar</span>
		          </a>
		        </li>
		     </c:when>
		     <c:otherwise>
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../welcome/">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">receipt_long</i>
		            </div>
		            <span class="nav-link-text ms-1">Solar</span>
		          </a>
		        </li>
	        	
	        </c:otherwise>
		</c:choose>
		-->
		
		<c:choose>
			<c:when test="${menu == '4'}">
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../welcome/">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">view_in_ar</i>
		            </div>
		            <span class="nav-link-text ms-1">Cost Recovery</span>
		          </a>
		        </li>
		     </c:when>
		     <c:otherwise>
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../welcome/">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">view_in_ar</i>
		            </div>
		            <span class="nav-link-text ms-1">Cost Recovery</span>
		          </a>
		        </li>
	        	
	        </c:otherwise>
		</c:choose>
        <c:choose>
			<c:when test="${menu == '5'}">
		        <li class="nav-item">
		          <a class="nav-link text-white active bg-gradient-primary" href="../smc/searchMenu">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">receipt_long</i>
		            </div>
		            <span class="nav-link-text ms-1">Search</span>
		          </a>
		        </li>
		     </c:when>
		     <c:otherwise>
		        <li class="nav-item">
		          <a class="nav-link text-white " href="../smc/searchMenu">
		            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
		              <i class="material-icons opacity-10">receipt_long</i>
		            </div>
		            <span class="nav-link-text ms-1">Search</span>
		          </a>
		        </li>
	        	
	        </c:otherwise>
		</c:choose>
        
        
       
        <li class="nav-item">
          <a class="nav-link text-white " href="../welcome/">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">person</i>
            </div>
            <span class="nav-link-text ms-1">Profile</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " href="../welcome/">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">login</i>
            </div>
            <span class="nav-link-text ms-1">Sign In</span>
          </a>
        </li>
        
      </ul>
    </div>