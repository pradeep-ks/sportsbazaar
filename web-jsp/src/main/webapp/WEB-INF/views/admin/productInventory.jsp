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
<c:if test="${ productsList eq null or empty productsList }">
<div class="alert alert-info rounded">
	Currently, there are no products in the inventory. Use the link above to add products to the inventory!
</div>
</c:if>
<c:if test="${ productsList ne null and not empty productsList }">
<div class="row">
	<div class="col">
		<ul class="list-unstyled">
		<c:forEach items="${ productsList }" var="product">
			<li class="media">
				<img src="#" class="mr-3" alt="To be inserted...">
				<div class="media-body">
					<h5 class="mt-0 mb-1">${product.name}&nbsp;${product.manufacturer}</h5>
					<p>
						Other details goes here...
					</p>
					<a class="btn btn-outline-warning">Edit</a>
					<a class="btn btn-outline-danger">Delete</a>
				</div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>
</c:if>
<%@include file="dashboardFooter.jsp"%>