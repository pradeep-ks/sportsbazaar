<%@include file="../views/common/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col text-center">
			<h2 class="fw-bold display-4">Billing Address</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6 offset-md-3">
			<form:form modelAttribute="userOrder" action="${flowExecutionUrl}" method="post">
				<div class="form-group">
					<form:label path="user.shippingAddress.houseNo" cssClass="form-label">House No.</form:label>
					<form:input path="user.shippingAddress.houseNo" cssClass="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="user.shippingAddress.street1" cssClass="form-label">Street Address 1</form:label>
					<form:input path="user.shippingAddress.street1" cssClass="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="user.shippingAddress.street2" cssClass="form-label">Street Address 2</form:label>
					<form:input path="user.shippingAddress.street2" cssClass="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="user.shippingAddress.city" cssClass="form-label">City</form:label>
					<form:input path="user.shippingAddress.city" cssClass="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="user.shippingAddress.state" cssClass="form-label">State</form:label>
					<form:input path="user.shippingAddress.state" cssClass="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="user.shippingAddress.zipCode" cssClass="form-label">Zip Code</form:label>
					<form:input path="user.shippingAddress.zipCode" cssClass="form-control"/>
				</div>
				<br>
				<div class="form-group">
					<input type="submit" class="btn btn-primary" name="_eventId_submitShippingAddress">
					<input type="hidden" name="_eventId" value="submitShippingAddress">
				</div>
			</form:form>
		</div>
	</div>
</div>
<%@include file="../views/common/footer.jsp" %>