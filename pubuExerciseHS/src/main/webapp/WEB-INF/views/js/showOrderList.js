function getOrderDetails(od_id){
	var urlString = "getOrderDetailsById?od_id=" + od_id;
	$.get(urlString, function(data, status){
		if(status == "success"){
			$("#areaShowOrderDetails").html(data);
		}
	});
}
	