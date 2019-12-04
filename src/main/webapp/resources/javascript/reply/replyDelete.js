/**
 * 
 */

function deleteToServer(bunho, root) {
	//alert(bunho + "," + root);	
	let url = root + "/reply/replyDelete.do";
	let params = "bunho="+bunho;
	sendRequest("GET",url,deleteFromServer,params);
}

function deleteFromServer(){
	if(xhr.readyState == 4 && xhr.status == 200){		
		let listDiv = document.getElementById("listAllDiv");
		let divId = document.getElementById(xhr.responseText);
		listDiv.removeChild(divId);
	}
}