<%@include file="../views/common/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col text-center">
			<div class="alert alert-success">
				<h2 class="fw-bold display-4">Order Success!</h2>
				<p class="lead">Your order has been placed successfully</p>
				<form:form modelAttribute="userOrder">
					<div class="form-group">
						<input type="submit" class="alert-link"
							name="_eventId_continueShopping"> <input
							type="hidden" name="_eventId" value="continueShopping">
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<%@include file="../views/common/footer.jsp" %>