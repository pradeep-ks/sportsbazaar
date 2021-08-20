<%@ include file="common/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h2>New User Registration Form!</h2>
			<p class="lead">
				Fill in the required details and click &quot;Sign Up&quot; to register.
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col-6 offset-3">
			<form:form modelAttribute="signUpDTO" method="post" action="${pageContext.request.contextPath}/register">
				<div class="form-group">
					<form:label path="username" cssClass="form-label">Username</form:label>
					<form:input path="username" cssClass="form-control"/>
					<form:errors path="username" cssClass="text-danger"/>
				</div>
				<div class="form-group">
					<form:label path="email" cssClass="form-label">Email</form:label>
					<form:input path="email" type="email" cssClass="form-control"/>
					<form:errors path="email" cssClass="text-danger"/>
				</div>
				<div class="form-group">
					<form:label path="password" cssClass="form-label">Password</form:label>
					<form:password path="password" cssClass="form-control"/>
					<form:errors path="password" cssClass="text-danger"/>
				</div>
				<div class="form-group">
					<form:label path="confirmPassword" cssClass="form-label">Confirm Password</form:label>
					<form:password path="confirmPassword" cssClass="form-control"/>
					<form:errors path="confirmPassword" cssClass="text-danger"/>
				</div>
				<br>
				<input type="submit" class="btn btn-primary">
			</form:form>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
