<%@ include file="common/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h2>User Login Form</h2>
			<p class="lead">
				Fill in your credentials and click &quot;Login&quot;
			</p>
		</div>
	</div>
	<c:if test="${param.logout ne null}">
		<div class="row">
			<div class="col-6 offset-3">
				<div class="alert alert-success">
					You logged out successfully!
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-6 offset-3">
			<form:form method="post" action="${pageContext.request.contextPath}/login">
				<div class="form-group">
					<label for="username" class="form-label">Username</label>
					<input type="text" id="username" name="username" class="form-control" required>
				</div>
				<div class="form-group">
					<label for="password" class="form-label">Password</label>
					<input type="password" name="password" id="password" class="form-control" required>
				</div>
				<br>
				<input type="submit" class="btn btn-primary" value="Login">
			</form:form>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
