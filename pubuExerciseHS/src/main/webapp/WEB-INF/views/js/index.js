$(document).ready(function(){
	$("#btnHome").click(function(){
		todo("btnHome");
		window.open("index");
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
	
	$("#btnShowOrder").click(function(){
		todo("btnShowOrder");
		window.open("showOrderList");
	});
	
	todo("index.js Loaded...");
});