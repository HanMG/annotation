let root = null;
function writeToServer(requestRoot) {

	// alert(writeReply);
	root = requestRoot;
	let writeReply = $("#writeReply").val();
	let params = "writeReply=" + writeReply;
	let url = root + "/reply/replyWrite.do?"+params;
	
	//alert(root+","+url+","+params);
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:writeFromServer,
		error:function(xhr, status, error){
			alert(xhr+","+status+","+error);
		}
	});	
}

function writeFromServer(data) {

	let result = data.split(",");	
	let bunho = result[0].trim();
	let reply = result[1].trim();
	//alert(bunho+","+reply);
	let div = $("<div class='replyDiv' id="+bunho+"></div>");
	$("<span class='cssBunho'>"+bunho+"</span>").appendTo(div);
	$("<span class='cssReply'>"+reply+"</span>").appendTo(div);	
	$("<span class='cssUpDel'><a href='javascript:selectToServer(&#39;"+bunho+"&#39;,&#39;"+root+"&#39;)'>수정</a>&nbsp;<a href='javascript:deleteToServer(&#39;"+bunho+"&#39;,&#39;"+root+"&#39;)'>삭제</a></span>").appendTo(div);
	div.insertBefore($("#listAllDiv"));

}
