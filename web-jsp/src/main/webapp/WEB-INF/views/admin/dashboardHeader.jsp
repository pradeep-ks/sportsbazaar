<%@ include file="../common/header.jsp"%>
<main role="main" class="container">
	<div class="jumbotron text-center">
		<h2 class="display-4"><spring:message code="app.admin.dashboard.common.title" /></h2>
		<nav class="nav nav-pills nav-fill justify-content-center">
			<a 
				class="nav-link <c:if test="${dashboardActive}">active</c:if>" 
				href="<c:url value="/admin/dashboard" />">
				<spring:message code="app.admin.dashboard.common.nav.dashboard.text" />
			</a>
			<a 
				class="nav-link <c:if test="${productsActive}">active</c:if>" 
				href="<c:url value="/admin/inventory/products" />">
				<spring:message code="app.admin.dashboard.common.nav.products.text" />
			</a>
			<a 
				class="nav-link <c:if test="${categoryActive}">active</c:if>" 
				href="<c:url value="/admin/inventory/category" />">
				<spring:message code="app.admin.dashboard.common.nav.category.text" />
			</a>
		</nav>
	</div>