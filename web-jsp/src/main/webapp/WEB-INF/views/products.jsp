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
	<div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach var="product" items="${productsList}">
		<div class="col">
			<div class="card shadow p-3 mb-5 bg-body rounded">
				<img src="<c:url value="/uploads/products/${product.id}.jpg" />" 
					class="rounded mx-auto d-block text-center" alt=""
					width="150" height="150">
				<div class="card-body">
					<h5 class="card-title">
						<c:out value="${product.productName}" />
						<small>by</small>
						<c:out value="${product.manufacturer}" />
					</h5>
					<p class="card-text text-truncate">
						<c:out value="${product.description}" />
						<br>
						<span class="text-muted">
							<strong>Price: </strong>
							<fmt:formatNumber type="currency" currencySymbol="&#8377;" value="${product.price}" />
						</span>
						<br>
						<c:if test="${product.unitsInStock eq 0}">
							<span class="text-success" title="Unavailable">
								<i class="bi bi-x-circle-fill"></i>
							</span>
						</c:if>
						<c:if test="${product.unitsInStock gt 0}">
							<span class="text-success" title="Available">
								<i class="bi bi-check-circle-fill"></i>
							</span>
						</c:if>
					</p>
				</div>
				<div class="card-footer">
					<a href='<c:url value="/productDetails"><c:param name="id" value="${product.id}" /></c:url>' 
						class="btn btn-info" title="View Details">
						<i class="bi bi-info-circle"></i>
					</a>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
