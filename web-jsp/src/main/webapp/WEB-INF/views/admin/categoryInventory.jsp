<%@include file="dashboardHeader.jsp"%>
<div class="row">
	<div class="col text-center">
		<div class="alert alert-dark rounded bg-gradient">
			<h4>Categories of Inventory Products</h4>
		</div>
	</div>
</div>
<div class="row">
	<div class="col">
		<a class="btn btn-link" href="<c:url value="/admin/inventory/category/addCategory" />">Create New Category</a>
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
<c:if test="${ categoryList eq null or empty categoryList }">
<div class="alert alert-info rounded">
	Currently, there are no categories in the database. Use the link above to create categories!
</div>
</c:if>
<c:if test="${ categoryList ne null and not empty categoryList }">
<div class="row">
	<div class="col">
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Category Name</th>
					<th scope="col">Description</th>
					<th scope="col">Action(s)</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${ categoryList }" var="category">
			<tr>
				<td>${category.id}</td>
				<td>${category.categoryName}</td>
				<td>${category.description}</td>
				<td>
					<a href="<c:url value="/admin/inventory/category/modify/${category.id}" />" class="btn btn-outline-warning">Edit</a>
					<a href="<c:url value="/admin/inventory/category/delete/${category.id}" />" class="btn btn-outline-danger">Delete</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</c:if>
<%@include file="dashboardFooter.jsp"%>