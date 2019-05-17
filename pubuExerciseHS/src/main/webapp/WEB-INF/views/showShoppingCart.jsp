<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/DevTools.js"></script>
	<script type="text/javascript" src="js/showShoppingCart.js"></script>
	<title>購物車</title>
</head>
<body>
	${loginName}的購物車(已選商品):<br/>
	<table border  ='1'>
	<TR>
	<TH><TH>客戶<TH>品名<TH>售價<TH>數量<TH>小計
	<c:set var="total_price" value="0" />
	<c:if test="${scs.size()>0}">
		<c:forEach items="${scs}" var="sc" >
			<TR><TD><button type="button" onclick="removeItemFromShoppingCart(${sc.sc_id});">移除</button>
				<TD>${sc.ctmBean.ctm_account}
				<TD>${sc.pdtBean.pd_name }
				<TD>${sc.sc_price}
				<TD><input id='sc_number_${sc.sc_id}' type='number' min=1  value='${sc.sc_number}' />
					<button type='button' onclick='updateShoppingCartItem(${sc.sc_id}, $("#sc_number_${sc.sc_id}").val());'>修改</button>
				<TD><c:set var="sub_total_price"> ${sc.sc_price * sc.sc_number}</c:set>	${sub_total_price}
					<c:set var="total_price">${total_price + sub_total_price}</c:set>
		</c:forEach>
	</c:if>
			<TR><TH colspan=4>總價 <TD colspan=2> ${total_price}
	</table>

	<button type="button" onclick="clearShoppingCartByCustomer(${loginId});">全部清除</button>
	<button id="btnOrder">確認結帳</button>
</body>
</html>