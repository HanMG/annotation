

function selectToServer(bunho, requestRoot){
	root = requestRoot;
		
	let params = "bunho="+bunho;
	let url = root + "/reply/replyUpdate.do?"+params;
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:selectFromServer,
		error:function(xhr, status, error){
			alert(xhr+","+status+","+error);
		}
	});	
}

function selectFromServer(data){	
		//alert(xhr.responseText);
		let str = data.split(",");
		let bunho = str[0].trim();
		let reply = str[1].trim();
		//alert(bunho +","+reply);		
		let div = $("#"+bunho);
		let upDiv = $("<div class='replyDiv' id=updiv"+bunho+"></div>");
		let upInput = $("<input type='text' name='write' value="+reply+" id=up"+bunho+" />");
		let upBtn = $("<input type='button' value='수정' onclick='updateToServer("+bunho+",&#39;"+root+"&#39;,&#39;up"+bunho+"&#39;)' />");
		let delBtn = $("<input type='button' value='취소' onclick='updateClose("+bunho+")'/>");
			
		upInput.appendTo(upDiv);
		upBtn.appendTo(upDiv);	
		delBtn.appendTo(upDiv);		
		
		upDiv.appendTo(div);	
}

function updateToServer(bunho,root,inputId){
	//alert(bunho+","+root+","+inputId);
	let updateReply = $("#"+inputId).val();
	//alert(bunho+","+root+","+updateReply);
	let params = "bunho="+bunho+"&updateReply="+updateReply;
	let url = root + "/reply/replyUpdateOk.do?"+params;	
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:updateFromServer,
		error:function(xhr, status, error){
			alert(xhr+","+status+","+error);
		}
	});	
}

function updateFromServer(data){	
		let str = data.split(",");
		let bunho = str[0].trim();
		let reply = str[1].trim();
		$("#"+bunho).find(".cssReply").text(reply);		
		updateClose(bunho);
}

function updateClose(divId){
	$("#updiv"+divId).remove();	
}