$(document).ready(function(){
	$("#btnHome").click(function(){
		todo("btnHome");
	});

	$("#btnLogin").click(function(){
		window.open('loginForm');
		todo("btnLogin");
	});
	
	$("#btnLogout").click(function(){
		todo('btnLogout');
		window.open('logout');
	});
	
	$("#btnShowProductList").click(function(){
		todo("btnShowProductList");
		window.open("showProductList");
	});
	
	$("#btnShowShoppingCart").click(function(){
		todo("btnShowShoppingCart");
		window.open("showShoppingCart.jsp");
	});
	
	$("#btnShowOrder").click(function(){
		todo("btnShowOrder");
		window.open("showOrders.jsp");
	});
	
	todo("index.js Loaded...");
});