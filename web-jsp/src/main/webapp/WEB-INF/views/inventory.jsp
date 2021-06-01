<%@ include file="common/header.jsp"%>
<c:if test="${productsTabSelected}">
	<c:set var="productsLinkActive" value="active" />
	<c:set var="productsTabSelected" value="show active" />
</c:if>
<c:if test="${categoryTabSelected}">
	<c:set var="categoryLinkActive" value="active" />
	<c:set var="categoryTabSelected" value="show active" />
</c:if>
<main role="main" class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h2>Welcome to Inventory Management</h2>
			<p class="lead">Here, you can save, update, view and delete
				inventory items!</p>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<ul class="nav nav-tabs justify-content-center" role="tablist">
				<li class="nav-item">
					<a class="nav-link ${productsLinkActive}"
						href='<c:url value="/admin/inventory"><c:param name="activeTab" value="products" /></c:url>' 
						id="productsTab"
						data-bs-toggle="tab" data-bs-target="#products" role="tab"
						aria-selected="${productsTabActive}"> 
						Manage Products 
					</a>
				</li>
				<li class="nav-item ${categoryLinkActive}">
					<a class="nav-link"
						href='<c:url value="/admin/inventory"><c:param name="activeTab" value="category" /></c:url>' 
						id="categoryTab"
						data-bs-toggle="tab" data-bs-target="#category" role="tab"
						aria-selected="${categoryTabActive}"> 
						Manage Categories 
					</a>
				</li>
			</ul>
			
			<div class="tab-content" id="inventoryContent">
				<!-- Products Tab contents -->
				<div class="tab-pane fade ${productsTabSelected}" id="products"
					role="tabpanel">
					<div class="container">
						<!-- product management form begins -->
						<div class="row">
							<div class="col-md-6 offset-md-3">
								<form:form action="${pageContext.request.contextPath}/admin/inventory/product" modelAttribute="productDTO" method="post">
									<div class="card">
										<div class="card-body">
											<h5 class="card-title text-center">Add New Product</h5>
											<h6 class="card-subtitle mb-2 text-muted text-center">
												Fill in the required field(s) and click submit!
											</h6>
											<p class="card-text">
												<div class="mb-3">
													<form:label path="productName" class="form-label">Product Name</form:label>
													<form:input path="productName" class="form-control"/>
												</div>
												<div class="mb-3">
													<form:label path="manufacturer" class="form-label">Manufacturer</form:label>
													<form:input path="manufacturer" class="form-control" />
												</div>
												<div class="mb-3">
													<form:label path="description" class="form-label">Description</form:label>
													<form:textarea path="description" class="form-control"/>
												</div>
												<div class="mb-3">
													<form:label path="category" class="form-label">Category</form:label>
													<form:select id="productCategory" path="category" class="form-control">
														<form:options items="${categories}" itemLabel="categoryName" itemValue="categoryName"/>
													</form:select>
												</div>
												<div class="mb-3">
													<form:label path="condition" class="form-label">Condition</form:label>
													<form:input path="condition" class="form-control" />
												</div>
												<div class="mb-3">
													<form:label path="price" class="form-label">Price</form:label>
													<form:input path="price" class="form-control" />
												</div>
												<div class="mb-3">
													<form:label path="unitsInStock" class="form-label">Units in Stock</form:label>
													<form:input path="unitsInStock" class="form-control" />
												</div>
												<br>
												<div class="mb-3">
													<button type="submit" class="btn btn-primary">Add To Inventory</button>
												</div>
											</p>
										</div>
									</div>
								</form:form>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-8 offset-md-2">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Product Id</th>
											<th>Product Name</th>
											<th>Description</th>
											<th>Manufacturer</th>
											<th>Category</th>
											<th>Price</th>
											<th>Units in Stock</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="prod" items="${ products }">
										<tr>
											<td>${ prod.id }</td>
											<td>${ prod.productName }</td>
											<td>${ prod.description }</td>
											<td>${ prod.manufacturer }</td>
											<td>${ prod.category.categoryName }</td>
											<td>${ prod.price }</td>
											<td>${ prod.unitsInStock }</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- product management form ends -->
					</div>
				</div>
				<!-- End of Products tab contents -->
				<!-- Category tab contents begin -->
				<div class="tab-pane fade ${categoryTabSelected}" id="category"
					role="tabpanel">
					<div class="container">
						<!-- category create/update form begins -->
						<div class="row">
							<div class="col-md-6 offset-md-3">
								<form:form modelAttribute="categoryDto" 
									method="post" 
									action="${pageContext.request.contextPath}/admin/inventory/category">
									<div class="card">
										<div class="card-body">
											<h5 class="card-title text-center">Create New Category</h5>
											<h6 class="card-subtitle mb-2 text-muted text-center">
												Fill in the required field(s) and click submit!
											</h6>
											<p class="card-text">
											<c:if test="${ success }">
											<div class="alert alert-success alert-dismissible fade show" role="alert">
												A new category has been created successfully!
												<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
											</div>
											</c:if>
											<div class="input-group">
												<span class="input-group-text">Category Name</span>
												<form:input path="categoryName" class="form-control" />
											</div>
											<br>
											<div class="input-group">
												<button type="submit" class="btn btn-primary">Create</button>
											</div>
											</p>
										</div>
									</div>
								</form:form>
							</div>
						</div>
						<!-- category create/update form ends -->
						<hr>
						<c:if test="${categories eq null}">
							<div class="alert alert-danger text-center" role="alert">
								<h5>Oops! There are no categories available...</h5>
								Please create a category using form above!
							</div>
						</c:if>
						<c:if test="${categories ne null}">
						<div class="alert alert-info bg-gradient text-center">
							<h4>List of Available Categories!</h4>
						</div>
						<!-- category table begins -->
						<table class="table table-responsive">
							<thead>
								<tr>
									<th>Category Id</th>
									<th>Category Name</th>
									<th colspan="2">Action(s)</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="item" items="${categories}">
								<tr>
									<td>${item.id}</td>
									<td>${item.categoryName}</td>
									<td>
										Edit action
									</td>
									<td>
										Delete action
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						</c:if>
						<!-- category table ends -->
					</div>
				</div>
				<!-- Category tab contents end -->
			</div>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp"%>
