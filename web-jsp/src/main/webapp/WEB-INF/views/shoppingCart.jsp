<%@ include file="common/header.jsp" %>
<main role="main" class="container" ng-app="cart" ng-controller="CartController as $ctrl" ng-cloak>
	<div class="starter-template">
		<h1>Your Shopping Cart</h1>
	</div>
	<div class="row">
		<div class="col">
			<button class="btn btn-warning" ng-click="$ctrl.clearCart()" ng-disabled="$ctrl.cart.cartItems.length === 0">Clear Cart</button>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<div class="alert alert-warning text-center" ng-show="$ctrl.cart.cartItems.length === 0">
				Your cart is empty! <a href="<c:url value="/products"/>" class="alert-link">Continue Shopping</a>
			</div>
			<table class="table table-striped" ng-hide="$ctrl.cart.cartItems.length === 0">
				<thead>
					<tr>
						<th>#</th>
						<th>Item Name</th>
						<th>Item Category</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Item Total</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in $ctrl.cart.cartItems">
						<td>{{$index + 1}}</td>
						<td>{{item.product.productName}} ({{item.product.manufacturer}})</td>
						<td>{{item.product.category.categoryName}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.product.price | currency:'&#8377;'}}</td>
						<td>{{item.itemTotal | currency:'&#8377;'}}</td>
						<td>
							<button class="btn btn-warning" ng-click="$ctrl.removeItem(item.id)"><i class="bi bi-trash"></i></button>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="5" style="text-align: right">Grand Total:</th>
						<td>{{$ctrl.getGrandTotal() | currency:'&#8377;'}}</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<a href="<c:url value="/products"/>" class="btn btn-link">Back to Shopping</a>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
