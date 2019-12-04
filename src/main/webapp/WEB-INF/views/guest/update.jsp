<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
<link rel="stylesheet" href="${root}/resources/css/guest/write.css" />
<script src="${root}/resources/javascript/guest/guest.js"></script>
</head>
<body>	
	<div id="container">	
			<form action="${root }/guest/updateOk.do" method="get" class="form box">
				<input type="hidden" name="num" value="${guestDto.num}" />
				<input type="hidden" name="pageNumber" value="${pageNumber}" />
				
				<div class="title">
					<span>이름</span> <input type="text" name="irum" size="12" value="${guestDto.name}" disabled="disabled"/>
					<span>비밀번호</span> <input type="password" name="pwd" size="12" value="${guestDto.password}"/>
				</div>

				<div class="content">
					<textarea name="message" cols="53" rows="5">${guestDto.message}</textarea>
				</div>

				<div class="title" style="text-align: right; padding-right: 8px;">
					<input type="submit" value="수정" /> <input type="button" value="취소" onclick="history.back();" />
				</div>
			</form>
			<br/>
	</div>
</body>
</html>