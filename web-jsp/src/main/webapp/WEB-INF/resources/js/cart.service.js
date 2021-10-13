/**
 * 
 */
(function () {
	'use strict';
	
	angular.module('cart').factory('CartService', CartService);
	
	CartService.$inject = ['$http'];
	
	function CartService($http) {
		let apiUrl = 'http://localhost:8080/web-jsp/api/cart';
		let service = {
			getCart: getCart,
			clearCart: clearCart,
			removeItemFromCart: removeItemFromCart
		};
		return service;
		
		function getCart() {
			return $http.get(apiUrl);
		}
		
		function clearCart(cartId) {
			return $http.get(apiUrl + '/clear/' + cartId);
		}
		
		function removeItemFromCart(itemId) {
			return $http.get(apiUrl + '/remove/' + itemId);
		}
	}
})();