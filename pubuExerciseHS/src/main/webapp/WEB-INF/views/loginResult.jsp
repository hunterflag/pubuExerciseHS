<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/DevTools.js"></script>
	<script type="text/javascript" src="js/loginResult.js"></script>
	<title>SH登入結果</title>
</head>
<body>
	帳號： ${loginName}<br/>
	<c:choose>
		<c:when test="${loginResult=='OK'}">
			<span id="dd"> 登入成功! </span><br/>
		</c:when>
		<c:otherwise>
			登入失敗!  <br/>
		</c:otherwise>
	</c:choose>
	<button type='button' onclick='window.close()'>關閉</button>
	<hr/>
	loginResult: ${loginResult}<br/>
</body>
</html>