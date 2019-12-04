<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>주소 검색</title>
<script src="${root}/resources/javascript/member/register.js"></script>
</head>
<body>
	<div id="wrap" style="text-align: center">
		<div>
			<h3>주소 검색</h3>
		</div>
		<div align="center">
			<form action="${root}/member/zipcode.do" method="get">
				<table>
					<tr>
						<td><input type="text" name="dong" placeholder="동을 입력해주세요."/> <input type="submit" value="검색" /></td>
					</tr>
				</table>
			</form>
			<table>
				<c:if test="${zipcodeList.size() == 0 }">
					<tr>
						<td>검색된 결과가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${zipcodeList.size() > 0 }">
					<tr>
						<td style="border-bottom: 3px solid black">아래의 우편번호를 클릭하세요.</td>
					</tr>

					<c:forEach var="zipcodeDto" items="${zipcodeList}">
						<tr>
							<td><a href="javascript:sendAddress('${zipcodeDto.zipcode}', '${zipcodeDto.sido}', '${zipcodeDto.gugun}', '${zipcodeDto.dong}', '${zipcodeDto.ri}', '${zipcodeDto.bunji}')"> ${zipcodeDto.zipcode} ${zipcodeDto.sido} ${zipcodeDto.gugun} ${zipcodeDto.dong} ${zipcodeDto.ri} ${zipcodeDto.bunji} </a></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>		
	</div>	
</body>
</html>