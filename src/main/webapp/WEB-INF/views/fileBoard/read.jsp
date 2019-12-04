<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>게시판 읽기</title>
<link rel="stylesheet" href="${root}/resources/css/board/read.css" />
<script src="${root}/resources/javascript/fileBoard/board.js"></script>
</head>
<body>	
	<div align="center" class="wrap">
		<table>
			<tr>
				<td align="center" height="20" width="125">글번호</td>
				<td align="center" height="20" width="125">${boardDto.boardNumber}</td>

				<td align="center" height="20" width="125">조회수</td>
				<td align="center" height="20" width="125">${boardDto.readCount}</td>
			</tr>
			<tr>
				<td align="center" height="20" width="125">작성자</td>
				<td align="center" height="20" width="125">${boardDto.writer}</td>

				<td align="center" height="20" width="125">작성일</td>
				<td align="center" height="20" width="125"><fmt:formatDate value="${boardDto.writeDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>
			<tr>
				<td align="center" height="15" width="125">이메일</td>
				<td align="center" height="15" colspan="3">${boardDto.email}</td>
			</tr>
			<tr>
				<td align="center" height="350" width="125">내용</td>
				<td valign="top" height="350" colspan="3">${boardDto.content}</td>
			</tr>
			<c:if test="${boardDto.fileName != null }">
				<tr>
					<td align="center" height="20" width="125">파일명</td>
					<td align="center" height="20" width="125"><a href="${root}/fileBoard/download.do?boardNumber=${boardDto.boardNumber}">${boardDto.fileName}</a></td>

					<td align="center" height="20" width="125">크기</td>
					<td align="center" height="20" width="125">${boardDto.fileSize}B</td>
				</tr>
			</c:if>
			<tr>
				<td height="30" colspan="4" align="center"><input type="button" value="글수정" onclick="updateFun('${root}','${boardDto.boardNumber}','${boardDto.groupNumber}','${boardDto.sequenceNumber }','${boardDto.sequenceLevel }','${pageNumber}')" /> <input type="button" value="글삭제" onclick="delFun('${root}','${boardDto.boardNumber}','${pageNumber}')" /> <input type="button" value="답글" onclick="replyFun('${root}','${boardDto.boardNumber}','${boardDto.groupNumber}','${boardDto.sequenceNumber }','${boardDto.sequenceLevel }','${pageNumber}')" /> <input type="button" value="글목록으로" onclick="location.href='${root}/fileBoard/list.do?pageNumber=${pageNumber}'" /></td>
			</tr>
		</table>
	</div>
</body>
</html>