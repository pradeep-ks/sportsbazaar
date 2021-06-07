<%@include file="dashboardHeader.jsp"%>
<div class="row">
	<div class="col-6 offset-3">
		<form:form method="post" modelAttribute="product" action="${pageContext.request.contextPath}/admin/inventory/products/save">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center">Add New Product</h5>
					<h6 class="card-subtitle text-center">Fill in the required details and click Submit</h6>
					<p class="card-text">
						<div class="form-group">
							<form:label path="name">Name of Product</form:label>
							<form:input path="name" cssClass="form-control" />
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
								<form:options items="${categories}" itemLabel="name" itemValue="name"/>
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