<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>	
	<c:if test="${check > 0 }">
		<script type="text/javascript">
			alert("회원가입이 잘 되었습니다.");
			location.href="${root}/index.jsp"
		</script>
	</c:if>
	<c:if test="${check == 0 }">
		<script>
			alert("회원가입이 되지 않았습니다.");
			location.href="${root}/member/register.do"
		</script>
	</c:if>
</body>
</html>