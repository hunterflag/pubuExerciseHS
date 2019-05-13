$(document).ready(function(){
	$.get("showShoppingCart.jsp", function(data, status){
		if(status == "success"){
			$("#areaShowShoppingCart").html(data);
			console.log(data);
		}
	});
});

function addToShoppingCart(ctm_id, pd_id, pd_price){
	var urlString = "AddShoppingCart.do?ctm_id" + ctm_id 
									+ "&pd_id="	+ pd_id 
									+ "&pd_price=" + pd_price;
	
//	$.post(urlString, function(data, status){
	$.get(urlString, function(data, status){
		$.get("showShoppingCart.jsp", function(data, status){
			if(status == "success"){
				location.reload();
//				$("#areaShowShoppingCart").html(data);
//				console.log(data);
			}
		});		
	});

}
