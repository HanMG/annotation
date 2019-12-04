/*
 *  게시판 관련 Javascript
 */

function boardForm(obj){
	//alert("ok");
}

function updateReset(obj) {
	obj.subject.value="";
	obj.email.value="";
	obj.content.value="";
	obj.password.value="";
}

function replyFun(root, boardNumber, groupNumber, sequenceNumber,
		sequenceLevel, pageNumber) {
	var url = root + "/board/write.do?boardNumber=" + boardNumber;
	url += "&groupNumber=" + groupNumber + "&sequenceNumber="
			+ sequenceNumber;
	url += "&sequenceLevel=" + sequenceLevel + "&pageNumber=" + pageNumber;
	//alert(url);

	location.href = url;
}

function delFun(root, boardNumber, pageNumber) {
	var cf = confirm("정말로 삭제하시겠습니까?");

	if (cf == true) {
		var url = root + "/board/delete.do?boardNumber=" + boardNumber;
		url += "&pageNumber=" + pageNumber;
		//alert(url);
		location.href = url;
	}
}

function updateFun(root, boardNumber, groupNumber, sequenceNumber,
		sequenceLevel, pageNumber) {
	var cf = confirm("수정하시겠습니까?");

	if (cf == true) {
		var url = root + "/board/update.do?boardNumber=" + boardNumber;
		url += "&groupNumber=" + groupNumber + "&sequenceNumber="
				+ sequenceNumber;
		url += "&sequenceLevel=" + sequenceLevel + "&pageNumber="
				+ pageNumber;
		//alert(url);
		location.href = url;
	}	
}