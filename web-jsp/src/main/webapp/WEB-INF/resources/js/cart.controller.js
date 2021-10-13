/**
 * 
 */
(function () {
	'use strict';
	
	class CartController {
		constructor(CartService) {
			this.CartService = CartService;
		}
		
		$onInit() {
			this.getCart();
		}
		
		getCart() {
			this.CartService.getCart().then(response => {
				console.log(response);
				this.cart = response.data;
			}, errResponse => {
				console.log(errResponse);
			});
		}
		
		clearCart() {
			this.CartService.clearCart(this.cart.id).then(response => {
				console.log(response);
				if (response.status == 200) {
					this.getCart();
				}
			}, errResponse => {
				console.log(errResponse);
			});
		}
		
		removeItem(itemId) {
			this.CartService.removeItemFromCart(itemId).then(response => {
				if (response.status == 200) {
					console.log('reloading cart....');
					this.getCart();
				}
			}, errResponse => {
				console.log(errResponse);
			});
		}
		
		getGrandTotal() {
			let grandTotal = 0;
			if (this.cart && this.cart.cartItems.length !== 0) {
				for (let i = 0; i < this.cart.cartItems.length; i++) {
					grandTotal += this.cart.cartItems[i].itemTotal;
				}
			}
			return grandTotal;
		}
	}
	
	CartController.$inject = ['CartService'];
	
	angular.module('cart').controller('CartController', CartController);
})();