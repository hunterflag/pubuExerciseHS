<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/DevTools.js"></script>
<script type="text/javascript" src="js/showOrderList.js"></script>
<link rel="stylesheet" type="text/css" href="css/showOrderList.css" />
<title>訂購單</title>
</head>
<body>
<c:choose>
	<c:when test="${empty loginName}">
		請先登入!! (稍後自動關閉)
		<script type='text/javascript'>	setTimeout('window.close()', 2000);	</script>
	</c:when>
	<c:otherwise>
		${loginId }號會員${loginName} 已訂購過 ${ods.size()} 筆, 紀錄如下：<br />
		<table border='1'>
			<TR><TH>單號<TH>客戶<TH>時間<TH>金額<TH>狀態 
			<c:if test="${ods.size()>0}">
				<c:forEach items="${ods}" var="od" >
					<TR onclick='getOrderDetails(${od.od_id});' class='record' title='點選以顯示訂購明細' >
						<TD>${od.od_id }
						<TD>${od.ctmBean.ctm_account }
						<TD>${od.od_time }
						<TD>${od.od_total_price }
						<TD>${od.od_state }
					</TR>
				</c:forEach>
			</c:if> 	
		</table>
	</c:otherwise>
</c:choose>
	<button onclick="window.close();">關閉</button>
	<hr />
	<div id="areaShowOrderDetails"></div>	
</body>
</html>