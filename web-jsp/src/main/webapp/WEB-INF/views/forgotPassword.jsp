<%@ include file="common/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h2>Forgot Password?</h2>
			<p class="lead">
				Please enter your registered email address to proceed further
			</p>
		</div>
	</div>
	<c:if test="${emailSent}">
		<div class="row">
			<div class="col-6 offset-3">
				<div class="alert alert-info">
					An email has been sent to your registered email address.
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-6 offset-3">
			<form:form method="post" action="${pageContext.request.contextPath}/forgotPassword">
				<div class="form-group">
					<label for="email" class="form-label">Enter your registered email address</label>
					<input type="email" id="email" name="email" class="form-control" required>
				</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-start">
					<input type="submit" class="btn btn-primary" value="Submit">
				</div>
			</form:form>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
