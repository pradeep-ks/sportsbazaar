<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><spring:message code="app.common.title" /></title>
<!-- Bootstrap core CSS -->
<link href="<c:url value="/webjars/bootstrap/4.6.0-1/css/bootstrap.css" />" rel="stylesheet">
<!-- Favicon -->
<link rel="favicon" href="#">
<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="<c:url value="/" />">
			<spring:message code="app.navbar.brand.text" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value="/" />">
						<spring:message code="app.navbar.home.text" />
					</a>
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
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown01"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
					<div class="dropdown-menu" aria-labelledby="dropdown01">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a> <a
							class="dropdown-item" href="#">Something else here</a>
					</div></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>