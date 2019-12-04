let root = null;
function writeToServer(requestRoot){
	let writeReply = document.getElementById("writeReply").value;
	//alert(writeReply);
	root = requestRoot;
	let url = root+"/reply/replyWrite.do";
	let params = "writeReply="+writeReply;
	sendRequest("POST", url, writeFromServer, params);	
}

function writeFromServer(){
	if(xhr.readyState == 4 && xhr.status == 200){
		//alert(xhr.responseText);
		document.getElementById("writeReply").value="";
		let result = xhr.responseText.split(",");
		let bunho = result[0].trim();
		let reply = result[1].trim();
		
		let listAllDiv = document.getElementById("listAllDiv");
		let div = document.createElement("div");
		div.className="replyDiv";
		div.id=bunho;
		
		let spanBunho = document.createElement("span");
		spanBunho.className = "cssBunho";
		spanBunho.innerHTML = bunho;	
		
		let spanReply = document.createElement("span");
		spanReply.className = "cssReply";
		spanReply.innerHTML = reply;
		
		let spanUpDel = document.createElement("span");
		spanUpDel.className = "cssUpDel";		
		
		let aUpdate = document.createElement("a");
		aUpdate.href="javascript:selectToServer("+bunho+",\'"+root+"\')";
		aUpdate.innerHTML="수정&nbsp;";
		
		let aDelete = document.createElement("a");
		aDelete.href="javascript:deleteToServer("+bunho+",\'"+root+"\')";
		aDelete.innerHTML="삭제";
		
		spanUpDel.appendChild(aUpdate);
		spanUpDel.appendChild(aDelete);
		
		div.appendChild(spanBunho);
		div.appendChild(spanReply);
		div.appendChild(spanUpDel);
		
		listAllDiv.insertBefore(div, listAllDiv.firstChild);	//==listAllDiv.childNode[0]	
	}
}

