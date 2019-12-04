// form 넘어 갈때 걸리는 함수
function registerForm(obj) {
	
/*	if(obj.id.value == ""){
		alert("아이디를 입력해주세요.");
		obj.id.focus();
		return false;
	}	
	
	if(obj.pwd.value == ""){
		alert("비밀번호를 입력해주세요.");
		obj.pwd.focus();
		return false;
	}
	
	else if(obj.pwd.value != obj.pwdcheck.value){
		alert("비밀번호와 비밀번호확인의 값이 다릅니다.");
		obj.pwd.focus();
		return false;
	}
	
	
	if(obj.name.value == ""){
		alert("이름을 입력해주세요.");
		obj.name.focus();
		return false;
	}
	
	
	if(obj.jumin1.value == ""){
		alert("주민번호 앞자리를 입력해주세요.");
		obj.jumin1.focus();
		return false;		
	}
	else if(obj.jumin1.value.length != 6){
		alert("주민번호 앞자리를 확인해주세요. (6자리)");
		obj.jumin1.focus();
		return false;
	}
	
	
	if(obj.jumin2.value == ""){
		alert("주민번호 뒷자리를 입력해주세요.");
		obj.jumin2.focus();
		return false;
	}
	else if(obj.jumin2.value.length != 7){
		alert("주민번호 뒷자리를 확인해주세요. (7자리)");
		obj.jumin2.focus();
		return false;
	}
	
	
	if(obj.email.value == ""){
		alert("이메일을 입력해주세요.");
		obj.email.focus();
		return false;
	}
	
	if(obj.zipcode.value == ""){
		alert("우편번호를 검색해주세요.");
		return false;
	}
	
	if(obj.address.value == ""){
		alert("상세주소를 입력해주세요.");
		obj.address.focous();
		return false;
	}
	
	if(obj.job.value == ""){
		alert("직업을 선택해주세요.");
		obj.job.focus();
		return false;
	}*/
	
	// 이메일수신 체크 
	let check = false;
	for (let i = 0; i < obj.mailing.length; i++) {
		if (obj.mailing[i].checked == true){
			check = true;
		}

	}

	if (check == false) {
		alert("반드시 이메일수신 선택하세요.");
		return false;
	}
	
	// 관심사 체크 , interestValue들로 부터 값을 받아 하나의 문자열로 만든뒤 interest로 넘겨줌	
	let str = "";
	let cnt = 0; // 널 체크
	for (let i = 0; i < obj.interestValue.length; i++) {
		if (obj.interestValue[i].checked == true) {
			str += obj.interestValue[i].value + ",";
			++cnt;
		}		
	}
	
	obj.interest.value=str;	
	
	if (cnt == 0) {
		alert("반드시 하나는 체크하세요.");
		return false;
	}

}

// 아이디 중복체크 함수
function idCheck(obj, root) {
	// alert(obj.id.value);

	if (obj.id.value == "") {
		alert("아이디를 반드시 입력하세요.");
		obj.id.focus();
		return false;
	}

	let url = root + "/member/idCheck.do?id=" + obj.id.value;
	// alert(url);

	open(url, "", "width=300, height=200, top=300, left=300, scrollbars=yes");
}


// 주소 검색 함수
function zipcodeRead(root) {
	let url = root + "/member/zipcode.do";
	// alert(url);

	open(url, "", "width=400, height=400, top=300, left=300, scrollbars=yes");
}

// 주소 검색 후 선택하면 등록페이지에 있는 우편번호와 주소로 입력되게하는 함수 
function sendAddress(zipcode, sido, gugun, dong, ri, bunji) {
	let address = sido + " " + gugun + " " + dong + " " + ri + " " + bunji;
	// alert(zipcode+"-"+address);
	opener.createForm.zipcode.value = zipcode;
	opener.createForm.address.value = address;
	self.close();
}