<%@ include file="common/header.jsp" %>
<main role="main" style="margin-top: -15px">
	<div class="container d-block bg-info bg-gradient rounded">
		<div class="text-center">
			<h2>Showing All Products</h2>
			<p class="lead">
				Following are available products on our store!
			</p>
		</div>
	</div>
	<div class="container">
	<div class="row">
		<div class="col-8 offset-2">
			<c:forEach var="product" items="${productsList}">
				<div class="d-flex align-items-center mb-3">
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
										<dt class="col-3">Available:</dt>
										<dd class="col-9">
											<c:if test="${product.unitsInStock eq 0}">
												<span class="text-success"><i class="bi bi-x-circle-fill"></i></span>
											</c:if>
											<c:if test="${product.unitsInStock gt 0}">
												<span class="text-success"><i class="bi bi-check-circle-fill"></i></span>
											</c:if>
										</dd>
									</dl>
									<small class="text-muted">
										<strong>Price: </strong>
										<fmt:formatNumber currencySymbol="&#8377;" type="currency" groupingUsed="true" value="${product.price}"/>
									</small>
								</div>
								<a href='<c:url value="/productDetails"><c:param name="id" value="${product.id}" /></c:url>' 
									class="btn btn-info" title="View Details">
									<i class="bi bi-info-circle"></i>
								</a>
								&nbsp;
								<a onclick="addToCart(${product.id});" style="cursor: pointer;"
								class="btn btn-outline-success" title="Add To Cart">
									<i class="bi bi-cart"></i>
								</a>
							</div>
						</div>
				  	</div>
				</div>
			</c:forEach>
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
	</div>
</main>
<%@ include file="common/footer.jsp" %>
