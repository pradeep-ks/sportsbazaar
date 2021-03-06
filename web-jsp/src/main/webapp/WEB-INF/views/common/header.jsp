<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><spring:message code="app.title" /></title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link href="<c:url value="/webjars/bootstrap/5.0.1/css/bootstrap.css" />" rel="stylesheet">
<!-- Favicon -->
<link rel="favicon" href="#">
<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
<script src="<c:url value="/webjars/angularjs/1.8.2/angular.js" />"></script>
<script src="<c:url value="/resources/js/cart.module.js"/>"></script>
<script src="<c:url value="/resources/js/cart.service.js"/>"></script>
<script src="<c:url value="/resources/js/cart.controller.js"/>"></script>
<script src="<c:url value="/resources/js/script.js"/>"></script>
<script type="text/javascript">
function loadCategories() {
	let http = new XMLHttpRequest();
	var apiUrl = '/web-jsp/categories';
	
	http.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			var categories = JSON.parse(this.responseText);
			var elm = document.querySelector('#category-dropdown');
			var htmlText = '<a class="dropdown-item" href="<c:url value="/products" />">Browse All Products</a>';
			
			if (categories.length > 0) {
				htmlText += '<hr class="dropdown-divider">';
				for (var i = 0; i < categories.length; i++) {
					htmlText += '<a class="dropdown-item" href="#">'+ categories[i] + '</a>';
				}
			}
			elm.innerHTML = htmlText;
		}
	};
	
	http.open('GET', apiUrl, true);
	http.send();
}
</script>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-success bg-gradient">
		<div class="container-fluid">
		<a class="navbar-brand" href="<c:url value="/" />">
			<spring:message code="app.navbar.brand.text" />
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" 
						id="dropdown01" onclick="loadCategories()"
						data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<spring:message code="app.navbar.category.text" />
					</a>
					<div class="dropdown-menu dropdown-menu-dark bg-success bg-gradient" id="category-dropdown" aria-labelledby="dropdown01">
						<!-- Will be added dynamically by JavaScript -->
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/about" />">
						<spring:message code="app.navbar.about.text" />
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/contact" />">
						<spring:message code="app.navbar.contact.text" />
					</a>
				</li>
			</ul>
			<form class="d-flex">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-dark" type="submit">Search</button>
			</form>
			<ul class="navbar-nav">
				<security:authorize access="hasRole('ADMIN')">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/admin/dashboard" />">
							<spring:message code="app.navbar.dashboard.text" />
						</a>
					</li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ADMIN', 'CUSTOMER')">
					<li class="nav-item">
						<a class="nav-link" style="cursor: pointer;">
							<i class="bi bi-person-circle"></i>
							<security:authentication property="principal.username"/>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/showCart" />" style="cursor: pointer;">
							<i class="bi bi-cart"></i>
							My Cart
						</a>
					</li>
				</security:authorize>
				<security:authorize access="isAnonymous()">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/login" />">Login</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/register" />">Register</a>
					</li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ADMIN', 'CUSTOMER')">
				<li class="nav-item">
					<form method="POST" action="<c:url value="/logout" />">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<input type="submit" value="Logout" class="nav-link btn btn-outline">
					</form>
				</li>
				</security:authorize>
			</ul>
		</div>
		</div>
	</nav>