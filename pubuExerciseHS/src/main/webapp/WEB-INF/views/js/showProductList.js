$(document).ready(function(){
	var urlStr= "getDatasForShowShoppingCart"; 
	$.get(urlStr, function(data, status){
		if(status == "success"){
			$("#areaShowShoppingCart").html(data);
			console.log(data);
		}
	});
});

function addToShoppingCart(ctm_id, pd_id){
	todo("addToShoppingCart(ctm_id, pd_id)");
	var urlString = "addToShoppingCart?ctm_id=" + ctm_id + "&pd_id=" + pd_id; 
	
	$.get(urlString, function(data, status){
		if(status == "success"){
			todo("success addToShoppingCart(ctm_id, pd_id)");
			$("#areaShowShoppingCart").html(data);
		}
	});
}
