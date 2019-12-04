<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${memberLevel != null }">
		<c:set var="id" value="${id}" scope="session"/>		
		<c:set var="memberLevel" value="${memberLevel}" scope="session"></c:set>
		<script>
			alert("로그인 되었습니다.");			
			location.href="${root}/index.jsp";
		</script>
	</c:if>
	<c:if test="${memberLevel == null }">
		<script>
			alert("로그인이 되지 않았습니다.");
			location.href="${root}/member/register.do";
		</script>
	</c:if>
</body>
</html>