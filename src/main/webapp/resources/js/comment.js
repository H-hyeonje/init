function submitComment(){
	var commentText= document.getElementById("commentText").value;
	var userid=document.getElementById("id").value;
	var postid=document.getElementById("Punique").value;
	
	if(commentText.trim()===""){
		alert("댓글을 입력해주세요");
		return;
	}
	
	var xhr=new XMLHttpRequest();
	xhr.open("post","/localMaster/addComment",true);
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

	
	var data=JSON.stringify({
		comments : commentText,
		id : userid,
		p_unique : parseInt(postid)
		
	});
	
	
   xhr.onreadystatechange =function(){
	 if(xhr.readyState === XMLHttpRequest.DONE && xhr.status===200){
			var response= JSON.parse(xhr.responseText);
			comments(response);
		 document.getElementById("commentText").value="";
		
		
	 }
   }
   xhr.send(data);
}

function comments(response){
	var commentList =response.comments;
	var dateList =response.formattedDates;
	var str="";
	var commentListContainer=document.getElementById("commentListContainer");
	for(var i=0;i<commentList.length;i++){
		str += "<div class='comment-item'>";
		str +="<p>"+commentList[i].id+" "+dateList[i]+" "+commentList[i].commentLikes+"</p>";
		str +="<p>"+commentList[i].comments+"</p>";
		str += "</div>";
	}
	commentListContainer.innerHTML=str;
}