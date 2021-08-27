<%@ include file="common/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h2>Update Password</h2>
			<p class="lead">
				Type in your new password twice, and click &quot;Update&quot;
			</p>
		</div>
	</div>
	<c:if test="${not passwordReset}">
		<div class="row">
			<div class="col-6 offset-3">
				<div class="alert alert-danger">
					<c:out value="${message}" />
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-6 offset-3">
			<form:form method="post" action="${pageContext.request.contextPath}/updatePassword">
				<input type="hidden" name="principalUserId" value="${principalUserId}">
				<div class="form-group">
					<label for="password" class="form-label">Enter New Password</label>
					<input type="password" id="password" name="password" class="form-control" required>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="form-label">Re-Enter New Password</label>
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
				</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-start">
					<input type="submit" class="btn btn-primary" value="Update">
				</div>
			</form:form>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
