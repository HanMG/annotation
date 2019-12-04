

function selectToServer(bunho, requestRoot){
	root = requestRoot;
	let url = root + "/reply/replyUpdate.do";	
	let params = "bunho="+bunho;
	sendRequest("GET",url,selectFromServer,params);
}

function selectFromServer(){
	if(xhr.readyState == 4 && xhr.status == 200){
		alert(xhr.responseText);
		let str = xhr.responseText.split(",");
		let bunho = str[0].trim();
		let reply = str[1].trim();
		//alert(bunho +","+reply);
		let listAllDiv = document.getElementById("listAllDiv");
		let div = document.getElementById(bunho);
		let upDiv = document.createElement("div");
		upDiv.className="replyDiv";		
		upDiv.id="updiv"+bunho;
		
		let input = document.createElement("input");
		input.type ="text";
		input.name= "write"
		input.value = reply;
		input.id="up"+bunho;
		
		let inputButton1 = document.createElement("input");
		inputButton1.type = "button";
		inputButton1.value= "수정";
		inputButton1.onclick=function(){
			updateToServer(div.id,root,input.id);			
		}
		
		let inputButton2 = document.createElement("input");
		inputButton2.type = "button";
		inputButton2.value= "취소";
		inputButton2.onclick=function(){
			updateClose(bunho);
		}
		
		upDiv.appendChild(input);
		upDiv.appendChild(inputButton1);
		upDiv.appendChild(inputButton2);
		
		div.appendChild(upDiv);		
	}
}

function updateToServer(bunho,root,inputId){
	//alert(bunho+","+root+","+inputId);
	let updateReply = document.getElementById(inputId).value;
	//alert(bunho+","+root+","+updateReply);
	let url = root + "/reply/replyUpdateOk.do";	
	let params = "bunho="+bunho+"&updateReply="+updateReply;
	sendRequest("GET",url,updateFromServer,params);
}

function updateFromServer(){
	if(xhr.readyState == 4 && xhr.status == 200){
		//alert(xhr.responseText);
		let str = xhr.responseText.split(",");
		let bunho = str[0].trim();
		let reply = str[1].trim();
		let span = document.getElementById(bunho).childNodes;
		for(let i = 0 ; i<span.length; i++){
			if(span[i].nodeType == 1){				
				if(span[i].className == "cssReply"){
					span[i].innerHTML = reply;
				}
			}
		}		
		updateClose(bunho);
	}
}

function updateClose(divId){
	let div = document.getElementById(divId);
	let closeDiv = document.getElementById("updiv"+divId);
	div.removeChild(closeDiv);
}