<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>	
	<c:set var="root" value="${pageContext.request.contextPath}"/>
	<c:out value="${check }"></c:out>
	<c:if test="${check > 0 }">
		<c:remove var="id" scope="session"/>
		<c:remove var="memberLevel" scope="session"/>
		<script type="text/javascript">
			alert("탈퇴가 잘 되었습니다.");
			location.href="${root}/index.jsp";
			
		</script>
	</c:if>
	<c:if test="${check == 0 }">
		<script>
			alert("탈퇴가 되지 않았습니다.");
			location.href="${root}/index.jsp";
		</script>
	</c:if>
</body>
</html>