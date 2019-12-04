<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrap" style="text-align: center;">
		<c:if test="${check > 0 }">
			<div align="center">
				<form action="${root}/member/idCheck.do" method="post">
					<input type="text" name="id" /> <input type="submit" value="검색" />
					<br /><br />
				</form>
				<p>이미 사용중인 아이디 입니다.</p>				
			</div>
		</c:if>
		<c:if test="${check == 0 }">
			<div align="center">
				<p>사용가능한 아이디 입니다.</p>
			</div>
			<br /><br />			
		</c:if>
		<input type="button" value="닫기" onclick="self.close()"/>
	</div>
	<script>
		// opener (부모창). 부모폼태그네임. 네임. 밸류
		opener.createForm.id.value = "${id}";		
	</script>
</body>
</html>