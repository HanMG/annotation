<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>업데이트</title>
</head>
<body>
	<c:if test="${check > 0 }">
		<script>
			alert("수정되었습니다.");
			location.href="${root}/board/read.do?boardNumber=${boardNumber}&pageNumber=${pageNumber}";
		</script>
	</c:if>
	<c:if test="${check == 0 }">
		<script>
			alert("수정에 실패했습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>