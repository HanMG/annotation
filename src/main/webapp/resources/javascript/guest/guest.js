/*
 *  방명록에 쓰이는 javascript
 *  
 */
function FormCheck(obj){
	
	if(obj.irum.value==""){
		alert("이름을 입력해주세요.");
		obj.irum.focus();
		return false;
	}
	
	if(obj.pwd.value==""){
		alert("패스워드를 입력해주세요.");
		obj.pwd.focus();
		return false;
	}
	
	if(obj.message.value==""){
		alert("내용를 입력해주세요.");
		obj.message.focus();
		return false;
	}
}


function deleteCheck(root, num, currentPage) {
	var url = root + "/guest/delete.do?num="+num+"&pageNumber="+currentPage;
	//alert(url);
	
	var value = confirm("정말로 삭제하시겠습니까?");
	
	if(value == true){
		location.href=url;
	}
}

function updateCheck(root,num,currentPage) {
	var url = root + "/guest/update.do?num="+num+"&pageNumber="+currentPage;
	//alert(url);
	
	var value = confirm("수정하시겠습니까?");
	
	if(value == true){
		location.href=url;
	}
}