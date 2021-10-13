<%@include file="../views/common/header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col text-center">
			<h2 class="fw-bold display-4">Order Summary</h2>
			<p class="lead">Your order has been placed, following are details of your order!</p>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<div class="list-group">
				<c:forEach var="item" items="${ userOrder.cart.cartItems }">
				<div class="list-group-item">
					<div class="d-flex w-100 justify-content-between">
				      <h5 class="mb-1">${ item.product.productName } - ${ item.product.manufacturer }</h5>
				      <small>Quantity: ${ item.quantity }</small>
				    </div>
				    <p class="mb-1">Item Total: 
				    <fmt:formatNumber currencySymbol="&#8377;" type="currency" groupingUsed="true" value="${ item.itemTotal }"/></p>
				</div>
				</c:forEach>
			</div>
			<br>
			<form:form modelAttribute="userOrder">
				<div class="form-group">
					<input type="submit" class="btn btn-primary"
						name="_eventId_submitOrderSummary"> <input
						type="hidden" name="_eventId" value="submitOrderSummary">
				</div>
			</form:form>
		</div>
	</div>
</div>
<%@include file="../views/common/footer.jsp" %>