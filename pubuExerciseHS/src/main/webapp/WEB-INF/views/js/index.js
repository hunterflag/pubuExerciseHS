$(document).ready(function(){
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
	
	$("#btnShowOrder").click(function(){
		todo("btnShowOrder");
		window.open("showOrderList");
	});
});