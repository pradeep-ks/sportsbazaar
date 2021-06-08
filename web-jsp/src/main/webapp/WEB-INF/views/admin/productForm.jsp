<%@include file="dashboardHeader.jsp"%>
<div class="row">
	<div class="col-6 offset-3">
		<form:form method="post" modelAttribute="product" action="${pageContext.request.contextPath}/admin/inventory/products/save">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center">
						<c:if test="${product.id eq null or product.id eq 0}">
							Add New Product
						</c:if>
						<c:if test="${product.id ne null and product.id ne 0}">
							Update Product
						</c:if>
					</h5>
					<h6 class="card-subtitle text-center">Fill in the required details and click Submit</h6>
					<p class="card-text">
						<c:if test="${message ne null and message.success}">
							<div class="alert alert-success alert-dismissible fade show"
								role="alert">
								${message.message}
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
						</c:if>
						<c:if test="${message ne null and not message.success}">
							<div class="alert alert-danger alert-dismissible fade show"
								role="alert">
								${message.message}
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
						</c:if>
						<form:hidden path="id" value="${product.id}" />
<%-- 						<div class="mb-3">
							<form:label path="id" class="form-label">Product #</form:label>
							<form:input path="id" cssClass="form-control disabled" disabled="true" />
						</div> --%>
						<div class="form-group">
							<form:label path="productName">Name of Product</form:label>
							<form:input path="productName" cssClass="form-control" />
						</div>
						<div class="form-group">
							<form:label path="description">Description</form:label>
							<form:textarea path="description" cssClass="form-control"/>
						</div>
						<div class="form-group">
							<form:label path="manufacturer">Manufacturer</form:label>
							<form:input path="manufacturer" cssClass="form-control" />
						</div>
						<div class="form-group">
							<form:label path="condition">Condition</form:label>
							<form:select path="condition" cssClass="form-control">
								<form:options items="${conditions}"/>
							</form:select>
						</div>
						<div class="form-group">
							<form:label path="category">Category</form:label>
							<form:select path="category" cssClass="form-control">
								<form:options items="${categories}"/>
							</form:select>
						</div>
						<div class="form-group">
							<form:label path="price">Price</form:label>
							<form:input path="price" cssClass="form-control" />
						</div>
						<div class="form-group">
							<form:label path="unitsInStock">Units in Stock</form:label>
							<form:input path="unitsInStock" cssClass="form-control" type="number" />
						</div>
						<br>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Save</button>
						</div>
					</p>
				</div>
			</div>
		</form:form>
	</div>
</div>
<div class="row">
	<div class="col">
		<a class="btn btn-link" href="<c:url value="/admin/inventory/products" />">Back to List</a>
	</div>
</div>
<%@include file="dashboardFooter.jsp"%>