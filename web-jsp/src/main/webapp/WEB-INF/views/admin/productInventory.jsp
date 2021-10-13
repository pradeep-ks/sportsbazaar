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
<c:if test="${ error ne null}">
<div class="alert alert-danger rounded text-center">
	${error}
</div>
</c:if>
<c:if test="${ productsList eq null or empty productsList }">
<div class="alert alert-info rounded">
	Currently, there are no products in the inventory. Use the link above to add products to the inventory!
</div>
</c:if>
<c:if test="${ productsList ne null and not empty productsList }">
<div class="row">
	<div class="col-12">
		<table class="table table-striped">
			<thead>
				<tr>
					<th class="align-middle">Image</th>
					<th class="align-middle">Name</th>
					<th class="align-middle">Manufacturer</th>
					<th class="align-middle">Description</th>
					<th class="align-middle">Condition</th>
					<th class="align-middle">Category</th>
					<th class="align-middle">Price</th>
					<th class="align-middle">Stock</th>
					<th colspan="2" class="align-middle">Action(s)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${productsList}">
					<tr>
						<td class="align-middle">
							<img width="90" height="90" src="<c:url value="/uploads/products/${product.id}.jpg" />" alt="#">
						</td>
						<td class="align-middle">${product.productName}</td>
						<td class="align-middle">${product.manufacturer}</td>
						<td class="align-middle">${product.description}</td>
						<td class="align-middle">${product.condition}</td>
						<td class="align-middle">${product.category.categoryName}</td>
						<td class="align-middle">
							<fmt:formatNumber currencySymbol="&#8377;" type="currency" groupingUsed="true" value="${product.price}"/>
						</td>
						<td class="align-middle">${product.unitsInStock}</td>
						<td class="align-middle">
							<a href='<c:url value="/admin/inventory/products/modify"><c:param name="productId" value="${product.id}" /></c:url>' 
								class="btn btn-outline-warning" title="Edit">
								<i class="bi bi-pencil"></i>
							</a>
						</td>
						<td class="align-middle">
							<a href='<c:url value="/admin/inventory/products/delete"><c:param name="productId" value="${product.id}" /></c:url>' 
							class="btn btn-outline-danger" title="Delete">
								<i class="bi bi-trash"></i>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${totalPages gt 1}">
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <li class="page-item  <c:if test="${currentPage eq 1}">disabled</c:if>">
			      <a class="page-link" href='<c:url value="/admin/inventory/products"><c:param name="page" value="${currentPage - 1}" /></c:url>' tabindex="-1" aria-disabled="true">Previous</a>
			    </li>
			    <c:forEach var="index" begin="1" end="${totalPages}" step="1">
			    	<li class="page-item">
			    		<a class="page-link" href='<c:url value="/admin/inventory/products"><c:param name="page" value="${index}" /></c:url>'>
			    			${index}
			    		</a>
			    	</li>
			    </c:forEach>
			    <li class="page-item <c:if test="${currentPage eq totalPages}">disabled</c:if>">
			      <a class="page-link" href='<c:url value="/admin/inventory/products"><c:param name="page" value="${currentPage + 1}" /></c:url>'>Next</a>
			    </li>
			  </ul>
			</nav>
		</c:if>
	</div>
</div>
</c:if>
<%@include file="dashboardFooter.jsp"%>