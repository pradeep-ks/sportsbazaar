<%@include file="dashboardHeader.jsp"%>
<div class="row">
	<div class="col text-center">
		<div class="alert alert-dark rounded bg-gradient">
			<h4>Products in Inventory</h4>
		</div>
	</div>
</div>
<div class="row">
	<div class="col">
		<a class="btn btn-link" href="<c:url value="/admin/inventory/products/addProduct" />">Add New Product</a>
	</div>
</div>
<hr>
<c:if test="${ message ne null and message.success }">
<div class="alert alert-success rounded text-center">
	${message.message}
</div>
</c:if>
<c:if test="${ message ne null and not message.success }">
<div class="alert alert-danger rounded text-center">
	${message.message}
</div>
</c:if>
<c:if test="${ productsList eq null or empty productsList }">
<div class="alert alert-info rounded">
	Currently, there are no products in the inventory. Use the link above to add products to the inventory!
</div>
</c:if>
<c:if test="${ productsList ne null and not empty productsList }">
<div class="row">
	<div class="col-8 offset-2">
		<c:forEach var="product" items="${productsList}">
			<div class="d-flex align-items-center">
			  	<div class="flex-shrink-0">
			    	<img width="150" height="150" src="<c:url value="/uploads/products/${product.id}.jpg" />" alt="#">
			  	</div>
			  	<div class="flex-grow-1 ms-3">
			    	<div class="list-group">
						<div class="list-group-item d-flex justify-content-between align-items-start">
							<div class="ms-2 me-auto">
								<div class="d-flex w-100 justify-content-between">
									<h5 class="mb-1">${product.manufacturer}&nbsp;${product.productName}</h5>
								</div>
								<dl class="row mb-1">
									<dt class="col-3">Description:</dt>
									<dd class="col-9">${product.description}</dd>
									<dt class="col-3">Category:</dt>
									<dd class="col-9">${product.category.categoryName}</dd>
									<dt class="col-3">Condition:</dt>
									<dd class="col-9">${product.condition}</dd>
									<dt class="col-3">Units In Stock:</dt>
									<dd class="col-9">${product.unitsInStock}</dd>
								</dl>
								<small class="text-muted">
									<strong>Price: </strong>
									<fmt:formatNumber currencySymbol="&#8377;" type="currency" groupingUsed="true" value="${product.price}"/>
								</small>
							</div>
							<a href='<c:url value="/admin/inventory/products/modify"><c:param name="productId" value="${product.id}" /></c:url>' 
								class="btn btn-outline-warning" title="Edit">
								<i class="bi bi-pencil"></i>
							</a>
							&nbsp;
							<a href='<c:url value="/admin/inventory/products/delete"><c:param name="productId" value="${product.id}" /></c:url>' 
							class="btn btn-outline-danger" title="Delete">
								<i class="bi bi-trash"></i>
							</a>
						</div>
					</div>
			  	</div>
			</div>
		</c:forEach>
	</div>
</div>
</c:if>
<%@include file="dashboardFooter.jsp"%>