
let http = null;

function addToCart(productId) {
	http = new XMLHttpRequest();
	if (!http) {
		alert('Could not send Ajax request!');
		return false;
	}
	
	http.onreadystatechange = () => {
		if (http.readyState === XMLHttpRequest.DONE) {
			if (http.status === 200) {
				console.log(http.responseText);
				alert('Product added to cart');
			} else {
				alert('Oops! Something went wrong!!');
			}
		}
	};
	
	http.open('GET', 'http://localhost:8080/web-jsp/api/cart/add/' + productId, true);
	http.send();
}
