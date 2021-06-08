<%@include file="dashboardHeader.jsp" %>
<div class="row">
	<div class="col text-center">
		<div class="alert alert-dark rounded bg-gradient">
			<h4>Inventory Dashboard</h4>
		</div>
	</div>
</div>
<div class="row card-group">
	<div class="col">
		<div class="card h-100">
			<div class="card-body">
				<h4 class="card-title">
					<spring:message code="app.admin.dashboard.card.product-summary.title" />
				</h4>
				<h6 class="card-subtitle mb-2 text-muted">
					<spring:message code="app.admin.dashboard.card.product-summary.subtitle" />
				</h6>
				<p class="card-text">
					<h5>
						Total Products in Store: 
						<small class="badge bg-warning">${productSummary.totalProducts}</small>
					</h5>
					<h5>
						Total products in stock:  
						<small class="badge bg-warning">${productSummary.productsInStock}</small>
					</h5>
				</p>
				<a href="<c:url value="/admin/inventory/products"/>" class="card-link">
					<spring:message code="app.admin.dashboard.card.product-summary.manage.text" />
				</a>
			</div>
		</div>
	</div>
	<div class="col">
		<div class="card h-100">
			<div class="card-body">
				<h4 class="card-title">
					<spring:message code="app.admin.dashboard.card.category-summary.title" />
				</h4>
				<h6 class="card-subtitle mb-2 text-muted">
					<spring:message code="app.admin.dashboard.card.category-summary.subtitle" />
				</h6>
				<p class="card-text">
					<h5>
						Total category of products in store: 
						<small class="badge bg-warning">${categorySummary.totalCategories}</small>
					</h5>
				</p>
				<a href="<c:url value="/admin/inventory/category"/>" class="card-link">
					<spring:message code="app.admin.dashboard.card.category-summary.manage.text" />
				</a>
			</div>
		</div>
	</div>
</div>
<%@include file="dashboardFooter.jsp" %>
