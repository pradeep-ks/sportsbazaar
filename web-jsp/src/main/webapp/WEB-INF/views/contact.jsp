<%@ include file="common/header.jsp" %>
<c:set var="submitAction" value="${pageContext.request.contextPath}/sendFeedback" />
<main role="main" class="container">
	<div class="starter-template">
		<h1><spring:message code="app.contact.title.text" /></h1>
		<p class="lead">
			<spring:message code="app.contact.description.text" />
		</p>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<form:form action="${submitAction}" modelAttribute="feedback" method="post">
					<div class="form-group">
						<form:label path="firstName">
							<spring:message code="app.contact.form.firstName.text" />
						</form:label>
						<form:input path="firstName" id="firstName" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="lastName">
							<spring:message code="app.contact.form.lastName.text" />
						</form:label>
						<form:input path="lastName" id="lastName" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="email">
							<spring:message code="app.contact.form.email.text" />
						</form:label>
						<form:input path="email" type="email" id="email" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="feedbackMessage">
							<spring:message code="app.contact.form.feedbackMessage.text" />
						</form:label>
						<form:textarea path="feedbackMessage" class="form-control" id="feedbackMessage"/>
					</div>
					<button class="btn btn-primary" type="submit">
						<spring:message code="app.contact.form.submit.text" />
					</button>
				</form:form>
			</div>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
