<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>게시판 목록</title>
<link rel="stylesheet" href="${root}/resources/css/board/list.css"/>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>	
	<div id="container">
		<div class="wrap">
			<div>
				<div class="subMenu">
					<a href="${root }/board/write.do">글쓰기</a>
				</div>
			</div>
			<div class="tableWrap">
				<table>
					<tr>
						<th style="width:10%;">번호</th>
						<th>제목</th>
						<th style="width:20%;">작성자</th>
						<th style="width:21%;">작성일</th>
						<th style="width:10%;">조회수</th>
					</tr>
					<c:if test="${count==0 || boardList.size() == 0}">
						<tr>
							<td align="center" colspan="5">게시판에 저장된 글이 없습니다.</td>
						</tr>
					</c:if>

					<c:if test="${count > 0 }">
						<c:forEach var="boardDto" items="${boardList}">
							<tr>
								<td>${boardDto.boardNumber}</td>
								<td class="subject">
									<c:if test="${boardDto.sequenceLevel > 0 }">										
										<c:forEach begin="1" end="${boardDto.sequenceLevel}">
											<i class='far fa-hand-point-right'></i>
										</c:forEach>										
									</c:if>
									<a href="${root}/board/read.do?boardNumber=${boardDto.boardNumber}&pageNumber=${currentPage}">${boardDto.subject }</a>
								</td>
								<td>${boardDto.writer}</td>
								<td><fmt:formatDate value="${boardDto.writeDate}" pattern="yyyy-MM-dd hh:mm" /></td>
								<td>${boardDto.readCount}</td>
							</tr>
						</c:forEach>
					</c:if>
					
				</table>
			</div>
		</div>
	</div>
	<br />
	<div align="center">
		<c:if test="${count > 0 }">
			<c:set var="pageBlock" value="${10}" />
			<c:set var="pageCount" value="${count / boardSize +(count%boardSize == 0 ? 0 : 1) }" />

			<fmt:parseNumber var="result" value="${(currentpage-1)/pageBlock}" integerOnly="true" />
			<c:set var="startPage" value="${result*pageBlock+1}" />
			<c:set var="endPage" value="${startPage+pageBlock-1 }" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			<c:if test="${startPage > pageBlock }">
				<a href="${root}/board/list.do?pageNumber=${startPage-pageBlock}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="${root}/board/list.do?pageNumber=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a href="${root}/board/list.do?pageNumber=${startPage+pageBlock}">[다음]</a>
			</c:if>
		</c:if>
	</div>
</body>
</html>