$(document).ready(function(){
	console.log( document.visibilityState );
	
});

//
//function showOrderDetails(od_id){
//	var urlString = "showOrderDetails.jsp?od_id=" + od_id;
//	$.get(urlString, function(data, status){
//		if(status == "success"){
//			$("#areaShowOrderDetails").html(data);
//		}
//	});
//}

function getOrderDetails(od_id){
	todo("getOrderDetails(od_id)");
	var urlString = "getOrderDetailsById?od_id=" + od_id;
	$.get(urlString, function(data, status){
		if(status == "success"){
			todo("Success getOrderDetails(od_id)");
//			window.open("ShowOrderDetailsList");
			$("#areaShowOrderDetails").html(data);
		}
	});
}
	