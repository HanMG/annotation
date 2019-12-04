<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<div align="center">
		<form action="${root}/member/dropoutOk.do" method="post">
			<input type="hidden" value="${memberDto.num }" name="num" />
			<label for="">아이디</label> <input type="text" name="did" value="${memberDto.id}" disabled="disabled" /> 
			<input type="hidden" name="id" value="${memberDto.id}"/>			
			<br />
			<br /> 
			<label for="">비밀번호</label> <input type="password" name="pwd" required="required"/> <br />
			<br /> 
			<input type="submit" value="전송" /> <input type="reset" value="취소" />
		</form>
	</div>
</body>
</html>