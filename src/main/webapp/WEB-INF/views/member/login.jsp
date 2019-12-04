<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>	
	<div align="center">
		<form action="${root}/member/loginOk.do" method="post">
			<label for="">아이디</label>
			<input type="text" name ="id" />
			<br /><br />
			
			<label for="">비밀번호</label>
			<input type="password" name="pwd" />
			<br /><br />		
			
			<input type="submit" value="전송"/>
			<input type="reset" value="취소" />
		</form>
	</div>
</body>
</html>