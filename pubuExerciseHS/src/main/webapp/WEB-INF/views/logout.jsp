<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SH登出結果</title>
</head>
<body>
<button type='button' onclick='window.close()'>關閉</button>
<hr/>
logoutResult: ${logoutResult}<br/>

<script type='text/javascript'>
	self.opener.location.reload();
	setTimeout('window.close()', 2000);
</script>
							
</body>
</html>