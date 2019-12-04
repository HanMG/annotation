<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
<link rel="stylesheet" href="${root}/css/member/register.css" />
<script src="${root}/javascript/member/register.js"></script>
</head>
<body>
	<jsp:include page="../../../index.jsp"></jsp:include>
	<div class="container">
		<div>
			<p>
				<strong>회원수정</strong>
			</p>
		</div>
		<div>
			<form action="${root}/member/updateOk.do" method="post" onsubmit="return registerForm(this)" name="createForm">
			<input type="hidden" value="${memberDto.num }" name="num" />
				<div class="item">
					<div>아이디</div>
					<div>
						<input type="hidden" value="${memberDto.id }" name="id" />
						*<input type="text" name="dId" value="${memberDto.id}" disabled="disabled" />
					</div>
				</div>
				<div class="item">
					<div>비밀번호</div>
					<div>
						*<input type="password" name="pwd" value="${memberDto.password}" />
					</div>
				</div>
				<div class="item">
					<div>비밀번호 확인</div>
					<div>
						*<input type="password" name="pwdcheck" value="${memberDto.password}" />
					</div>
				</div>
				<div class="item">
					<div>이름</div>
					<div>
						*<input type="text" name="name" disabled="disabled" value="${memberDto.name }" />
					</div>
				</div>
				<div class="item">
					<div>주민번호</div>
					<div>
						*<input type="text" name="jumin1" maxlength="6" value="${memberDto.jumin1 }" disabled="disabled" />-<input type="text" name="jumin2" maxlength="7" value="${memberDto.jumin2 }" disabled="disabled" />
					</div>
				</div>
				<div class="item">
					<div>이메일</div>
					<div>
						<input type="email" name="email" value="${memberDto.email }" />
					</div>
				</div>
				<div class="item">
					<div>우편번호</div>
					<div>
						<input type="text" name="zipcode" value="${memberDto.zipcode }" /> <input type="button" value="우편번호검색" onclick="zipcodeRead('${root}')" />
					</div>
				</div>
				<div class="item">
					<div>주소</div>
					<div>
						<input type="text" name="address" value="${memberDto.address }" style="width: 80%;" />
					</div>
				</div>
				<div class="item">
					<div>직업</div>
					<div>
						${memberDto.job} <select name="job">
							<option value=""></option>
							<option value="백수">백수</option>
							<option value="학생">학생</option>
							<option value="직장인">직장인</option>
							<option value="건물주">건물주</option>
						</select>
						<script>
							createForm.job.value = "${memberDto.job}";
						</script>
					</div>
				</div>
				<div class="item">
					<div>메일수신</div>
					<div>
						${memberDto.mailing}<input type="radio" name="mailing" value="yes" /> yes <input type="radio" name="mailing" value="no" /> no
					</div>
					<script>
						for (let i = 0; i < createForm.mailing.length; i++) {
							if (createForm.mailing[i].value == "${memberDto.mailing}") {
								createForm.mailing[i].checked = true;
							}
						}
					</script>
				</div>
				<div class="item" style="border-bottom: 2px solid black;">
					<div>관심분야</div>
					<div>
						${memberDto.interest} <input type="checkbox" name="interestValue" value="경제" id="ch1" /> <label for="ch1">경제</label> <input type="checkbox" name="interestValue" value="IT" id="ch2" /> <label for="ch2">IT</label> <input type="checkbox" name="interestValue" value="음악" id="ch3" /> <label for="ch3">음악</label> <input type="checkbox" name="interestValue" value="미술" id="ch4" /> <label for="ch4">미술</label> <input type="hidden" name="interest" />
					</div>					
					<script>
						let str = "${memberDto.interest}";
						let splitStr = str.split(",");								
						for (let j in splitStr){
							for(let i in createForm.interestValue){
								if(createForm.interestValue[i].value == splitStr[j]){
									createForm.interestValue[i].checked = true;
								}
							}
						}
					</script>
				</div>
				<div class="item-button">
					<input type="submit" value="가입" /> <input type="reset" value="취소" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>