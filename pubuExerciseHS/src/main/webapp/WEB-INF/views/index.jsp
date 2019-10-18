<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/DevTools.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<title>Hibernate + Spring 測試首頁</title>
</head>
<body>
	<header>
	Hibernate + Spring MVC版
	<nav>
		<button id="btnLogin">登入</button>
		<button id="btnLogout">登出</button>
		<button id="btnShowProductList">商品選購</button>
		<button id="btnShowOrder">訂購檢視</button>		
	</nav>		
	</header>
	
	<div>
		ID: ${loginId} 號<br/>
		 登入者: ${loginName}<br/> 
	</div>
	<hr/>
	&lt;&nbsp;${pageContext.request.contextPath}: ${pageContext.request.contextPath}&nbsp;&gt;<br/>
	&lt;&nbsp;${pageContext.request}: ${pageContext.request}&nbsp;&gt;<br/> 
	&lt;&nbsp;${pageContext}: ${pageContext}&nbsp;&gt;<br/> 
	
</body>
</html>