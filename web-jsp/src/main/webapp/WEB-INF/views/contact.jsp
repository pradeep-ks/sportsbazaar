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
					<div class="mb-3">
						<form:label path="firstName" class="form-label">
							<spring:message code="app.contact.form.firstName.text" />
						</form:label>
						<form:input path="firstName" id="firstName" class="form-control"/>
					</div>
					<div class="mb-3">
						<form:label path="lastName" class="form-label">
							<spring:message code="app.contact.form.lastName.text" />
						</form:label>
						<form:input path="lastName" id="lastName" class="form-control"/>
					</div>
					<div class="mb-3">
						<form:label path="email" class="form-label">
							<spring:message code="app.contact.form.email.text" />
						</form:label>
						<form:input path="email" type="email" id="email" class="form-control"/>
					</div>
					<div class="mb-3">
						<form:label path="feedbackMessage" class="form-label">
							<spring:message code="app.contact.form.feedbackMessage.text" />
						</form:label>
						<form:textarea path="feedbackMessage" class="form-control" id="feedbackMessage"/>
					</div>
					<button class="btn btn-success bg-gradient" type="submit">
						<spring:message code="app.contact.form.submit.text" />
					</button>
				</form:form>
			</div>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
