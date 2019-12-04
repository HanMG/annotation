<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 작성</title>
<link rel="stylesheet" href="${root}/resources/css/board/board.css"/>
<script src="${root}/resources/javascript/fileBoard/board.js"></script>
</head>
<body>	
	<h3>
		boardNumber : ${boardNumber}, &nbsp;
		groupNumber : ${groupNumber }, &nbsp;
		sequenceNumber : ${sequenceNumber}, &nbsp;
		sequenceLevel : ${sequenceLevel}		
	</h3>
	<div>
		<div class="container">
			<div>
				<p><strong>게시판 작성</strong></p>
			</div>
			<div style="text-align: right;">
				<a href="${root}/fileBoard/list.do">글목록</a>
			</div>
			<!-- 
				enctype="application/x-www-form-urlencoded" : 텍스트 전송 (기본값, 생략가능)
				enctype="multipart/form-data" : 데이터 업로드시 
			 -->
			<form action="${root}/fileBoard/writeOk.do" method="post" onsubmit="return boardForm(this)" enctype="multipart/form-data">
				<input type="hidden" name="boardNumber" value="${boardNumber}"/>
				<input type="hidden" name="groupNumber" value="${groupNumber}"/>
				<input type="hidden" name="sequenceNumber" value="${sequenceNumber}"/>
				<input type="hidden" name="sequenceLevel" value="${sequenceLevel}"/>
			
				<div class="item">
					<div>작성자</div>
					<div>
						<input type="text" name="writer" required/>
					</div>
				</div>
				<div class="item">
					<div>제목</div>
					<div>
						<input type="text" name="subject" required />
					</div>
				</div>
				<div class="item">
					<div>이메일</div>
					<div>
						<input type="email" name="email" style="width:65%;" required />
					</div>
				</div>
				<div class="item">
					<div style="height: 120px;">내용</div>
					<div>
						<textarea name="content" cols="30" rows="10" style="width: 100%; height: 100px; resize:none;" required></textarea>
					</div>
				</div>
				<div class="item">
					<div>비밀번호</div>
					<div>
						<input type="password" name="password"/>
					</div>
				</div>
				<div class="item" style="border-bottom:2px solid">
					<div>파일명</div>
					<div>
						<input type="file" name="file" size="40"/>
					</div>
				</div>
				<div class="item-button">
					<div>
						<input type="submit" value="글작성" /> <input type="reset" value="취소" /> <input type="button" value="글 목록으로" onclick="location.href='${root}/fileBoard/list.do'"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>