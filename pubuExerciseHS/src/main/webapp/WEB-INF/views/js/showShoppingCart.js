$(document).ready(function(){
	$("#btnOrder").click(function(){
		var urlString = "shoppingCartConfirmOrder";
		$.get(urlString, function(data, status){
			if (status == "success"){
				alert("訂單成立!");
				window.close();
			}
		});
	});
	
});

function removeItemFromShoppingCart(sc_id){
	var urlString = "removeItemFromShoppingCart?sc_id=" + sc_id ;
	$.get(urlString, function(data, status){
		if (status == "success"){
			todo(data);
			location.reload();
		}
	});
}

function clearShoppingCartByCustomer(ctm_id){
	var urlString = "clearShoppingCartByCustomer?ctm_id=" + ctm_id ;
	$.get(urlString, function(data, status){
		if (status == "success"){
			location.reload();
			todo(data);
		}
	});
}



function updateShoppingCartItem(sc_id, sc_number){
	var urlString = "updateShoppingCartItem";
	$.post(urlString, 
		   { "sc_id": sc_id, 
			 "sc_number": sc_number},
  	  	   function(data, status){
	  		  if (status == "success"){
	  			  location.reload();
	  		  }
	  	   });
}
