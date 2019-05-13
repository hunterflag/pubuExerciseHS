<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, tw.com.pubu.hunter.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/showProductList.js"></script>
<title>商品</title>
</head>
<body>
<c:choose>
	<c:when test="${empty loginName}">
		請先登入!! (稍後自動關閉)
		<script type='text/javascript'>
			setTimeout('window.close()', 2000);
		</script>
	</c:when>
	<c:otherwise>
		${loginName}, 有 ${pdts.size()} 筆可購商品<br/>
		<table border='1'>
		<TR>
		<TH>編號<TH>品名<TH>定價<TH>購物車
		<c:if test="${pdts.size()>0}">
			<c:forEach items="${pdts}" var="pdt" >
				<TR>
				<TD>${pdt.pd_id}
				<TD>${pdt.pd_name}
				<TD>${pdt.pd_price}
				<TD><button onclick="addToShoppingCart(${loginId}, ${pdt.pd_id}, ${pdt.pd_price})">加入</button>
			</c:forEach>
		</c:if>
		</table>
	</c:otherwise>
</c:choose>



		<hr/>
<%-- 		<jsp:include page="./showShoppingCart.jsp" ></jsp:include> --%>
</body>
</html>