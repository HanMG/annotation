<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link rel="stylesheet" href="${root}/resources/css/guest/write.css" />
<script src="${root}/resources/javascript/guest/guest.js"></script>
</head>
<body>
	<h3>count: ${count }, currentPage: ${currentPage}, boardSize:${boardSize}</h3>
	
	<div id="container">
		<c:if test="${count == 0 || currentPage == 1 }">
			<form action="${root }/guest/writeOk.do" method="get" class="form box" onsubmit="return FormCheck(this)">
				<div class="title">
					<span>이름</span> <input type="text" name="name" size="12" /> <span>비밀번호</span> <input type="password" name="password" size="12" />
				</div>

				<div class="content">
					<textarea name="message" cols="53" rows="5" placeholder="여기에 입력..."></textarea>
				</div>

				<div class="title" style="text-align: right; padding-right: 8px;">
					<input type="submit" value="확인" /> <input type="reset" value="취소" />
				</div>
			</form>
			<br/>
		</c:if>
		<%-- 데이터가 있을시  리스트를 가지고 forEach를 통해 꺼냄--%>
		<c:if test="${count > 0 }">						
			<c:forEach var="guestDto" items="${guestList}">
			<div class="form box">
				<div class="title board">
					<span>${guestDto.num}</span>
					<span>${guestDto.name }</span>
					<span><fmt:formatDate value="${guestDto.writeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>										
				</div>
				<div class="content txtleft">
					<pre>${guestDto.message}</pre>
				</div>
				<div class="title" style="text-align: right; padding-right: 8px;">
					<input type="button" value="수정" onclick="javascript:updateCheck('${root}','${guestDto.num}','${currentPage}')"/><input type="button" value="삭제" onclick="javascript:deleteCheck('${root}','${guestDto.num}','${currentPage}')"/>
				</div>
			</div>	
			<br />
			</c:forEach>
		</c:if>
	</div>
	<%--
		 페이지 번호 
		1. 총 페이지수 : 전체레코드 수(count)와 페이지당 게시물 수(boardSize)
		2. 페이지 블럭 : 시작 번호, 끝 번호 
		3. 
	--%>
	<div class="paging">
		<c:if test="${count > 0 }">
			<%-- 1. 총페이지 수 : count/boardSize --%>
			<fmt:parseNumber var="pageCount" integerOnly="true" value="${count/boardSize + (count%boardSize == 0 ? 0:1) }"/>
			
			<%-- 2. 페이지 블럭 --%>
			<c:set var="pageBlock" value="${3}"/>
			
			<%-- 2. 페이지 블럭 / 시작, 끝번호 
				int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;
				int endPage = startPage+pageBlock-1;
			--%>
			<fmt:parseNumber var="rs" value="${(currentPage-1)/pageBlock}" integerOnly="true"/>
			<c:set var="startPage" value="${rs*pageBlock+1}"/>			
			<c:set var="endPage" value="${startPage+pageBlock-1 }"/>	
					
			<%-- 3. 총레코드수:270/10 = 27, 요청페이지: 25 --%>
			<c:set var="endPage" value="${ endPage > pageCount ? pageCount : endPage }"/>			
			
			<%-- 이전 --%>
			<c:if test="${startPage > pageBlock }">
				<a href="${root}/guest/write.do?pageNumber=${startPage-pageBlock}">[이전]</a>
			</c:if>
			<%-- 페이지 출력 --%>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="${root}/guest/write.do?pageNumber=${i}">[${i}]</a>
			</c:forEach>			
			<%-- 다음 --%>
			<c:if test="${pageCount > endPage}">
				<a href="${root}/guest/write.do?pageNumber=${startPage+pageBlock}">[다음]</a>
			</c:if>		
			
		</c:if>
	</div>
</body>
</html>