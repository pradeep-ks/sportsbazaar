<%@include file="dashboardHeader.jsp"%>
<div class="row">
	<div class="col-md-6 offset-md-3">
		<form:form modelAttribute="category" method="post"
			action="${pageContext.request.contextPath}/admin/inventory/category/save">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center">
						<c:if test="${category.id eq null or category.id eq 0}">
							Create New Category
						</c:if>
						<c:if test="${category.id ne null and category.id ne 0}">
							Update Category
						</c:if>
					</h5>
					<h6 class="card-subtitle mb-2 text-muted text-center">Fill in
						the required field(s) and click submit!</h6>
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
						<div class="mb-3">
							<form:label path="id" class="form-label">Category #</form:label>
							<form:input disabled="true" path="id" cssClass="form-control disabled"/>
						</div>
						<div class="mb-3">
							<form:label path="categoryName" class="form-label">Category Name</form:label>
							<form:input path="categoryName" class="form-control" />
						</div>
						<div class="mb-3">
							<form:label path="description" class="form-label">Description</form:label>
							<form:input path="description" class="form-control" />
						</div>
						<br>
						<div class="input-group">
							<c:if test="${category.id eq null or category.id eq 0}">
								<button type="submit" class="btn btn-primary">Create</button>
							</c:if>
							<c:if test="${category.id ne null and category.id ne 0}">
								<button type="submit" class="btn btn-primary">Update</button>
							</c:if>
						</div>
					</p>
				</div>
			</div>
		</form:form>
	</div>
</div>
<%@include file="dashboardFooter.jsp"%>