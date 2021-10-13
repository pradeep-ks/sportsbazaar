<%@ include file="common/header.jsp" %>
<main role="main" class="container rounded bg-light bg-gradient" style="margin-top: -15px">
	<c:if test="${error ne null}">
		<div class="alert alert-danger text-center">${error}</div>
	</c:if>
	<c:if test="${selectedProduct ne null}">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card shadow p-3 mb-5 bg-body rounded">
				<div class="row g-0">
					<div class="col-md-4">
						<img width="150" src="<c:url value="/uploads/products/${selectedProduct.id}.jpg" />">
					</div>
					<div class="col-md-8">
				      <div class="card-body">
				        <h5 class="card-title">${selectedProduct.productName} - ${selectedProduct.manufacturer}</h5>
				        <p class="card-text ">
				        	<dl class="row">
				        		<dt class="col-sm-4">Description:</dt>
				        		<dd class="col-sm-8">${selectedProduct.description}</dd>
				        		<dt class="col-sm-4">Category:</dt>
				        		<dd class="col-sm-8">${selectedProduct.category.categoryName}</dd>
				        		<dt class="col-sm-4">Condition:</dt>
				        		<dd class="col-sm-8">${selectedProduct.condition}</dd>
				        		<dt class="col-sm-4">Price:</dt>
				        		<dd class="col-sm-8">
				        			<fmt:formatNumber type="currency" currencySymbol="&#8377;" value="${selectedProduct.price}" />
				        		</dd>
				        		<dt class="col-sm-4">Availability:</dt>
				        		<dd class="col-sm-8">
				        			<c:if test="${selectedProduct.unitsInStock eq 0}">
										<span class="text-success"><i class="bi bi-x-circle-fill"></i></span>
									</c:if>
									<c:if test="${selectedProduct.unitsInStock gt 0}">
										<span class="text-success"><i class="bi bi-check-circle-fill"></i></span>
									</c:if>
				        		</dd>
				        	</dl>
				        </p>
				        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
				      </div>
				    </div>
				</div>
				</div>
			</div>
		</div>
	</c:if>
</main>
<%@ include file="common/footer.jsp" %>
