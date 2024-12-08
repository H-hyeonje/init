document.addEventListener('DOMContentLoaded', function() {
    // AJAX 요청으로 댓글 데이터 가져오기
    $.ajax({
        url: '댓글을_가져오는_URL',
        type: 'GET',
        success: comments,  // 성공 시 comments 함수 실행
        error: function(xhr, status, error) {
            console.error('댓글 로딩 실패:', error);
        }
    });
});

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
		str +="<p><b>"+commentList[i].id+"</b> "+dateList[i]+"<button id='like-"+commentList[i].c_unique+"'>❤️"+commentList[i].commentLikes+"</button></p>";
		str +="<p>"+commentList[i].comments+"</p>";
		str += "</div>";
	}
	commentListContainer.innerHTML=str;
}
