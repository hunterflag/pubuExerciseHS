<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/logout.js"></script>
	<title>SH登出結果</title>
</head>
<body>
	<button type='button' onclick='window.close()'>關閉</button>
	<hr/>
	logoutResult: ${logoutResult}<br/>
</body>
</html>