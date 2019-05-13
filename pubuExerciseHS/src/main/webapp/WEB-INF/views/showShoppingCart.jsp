<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, javax.servlet.http.*, tw.com.pubu.hunter.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/showShoppingCart.js"></script>
<script type="text/javascript" src="js/DevTools.js"></script>
<title>購物車</title>
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
		購物車(已選購商品):
		<table border='1'>
		<TR>
		<TH><TH>客戶<TH>品名<TH>售價<TH>數量<TH>小計
<%-- 		<c:set var="total_price" value=0 /> --%>
		<c:if test="${scs.size()>0}">
			<c:forEach items="${scs}" var="sc" >
				<TR><TD><button type="button" onclick="removeFromShoppingCart(${sc.pdtBean.pd_id});">移除</button>
					<TD><span id='ctm_id' value='${sc.ctmBean.ctm_id}'> ${sc.ctmBean.ctm_accoun} </span>
					<TD><span id='pd_id' value='${sc.pdtBean.pd_id}'> ${sc.pdtBean.pd_name }</span>
					<TD>${sc.sc_price}
					<TD><input id='sc_number' type='number' min=1  value=${sc.sc_number}/>
						<button type='button' onclick='updateShoppingCart(${sc.ctmBean.ctm_id}, ${sc.pdtBean.pd_id}, ${sc.sc_number});'>修改</button>
					<c:set> sub_total_price = rs.getInt("sc.sc_price")*rs.getInt("sc.sc_number"); 
					</c:set> 
					<TD> ${sub_total_price}
				</TR>total_price = total_price + sub_total_price;
			</c:forEach>
		</c:if>
				<TR><TH colspan=4>總價 <TD colspan=2> ${total_price}
		</table>

		<button id="btnClear">全部清除</button>
		<button id="btnOrder">確認結帳</button>
	</c:otherwise>
</c:choose>
</body>
</html>