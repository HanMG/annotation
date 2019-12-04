/**
 * 
 */

function deleteToServer(bunho, root) {
	//alert(bunho + "," + root);	
	
	let params = "bunho="+bunho;
	let url = root + "/reply/replyDelete.do?"+params;
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:deleteFromServer,
		error:function(xhr, status, error){
			alert(xhr+","+status+","+error);
		}
	});	
}

function deleteFromServer(data){	
	//alert(data);
	$("#"+data).remove();		
}