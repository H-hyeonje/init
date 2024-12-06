function submitComment() {
    var commentText = document.getElementById("commentText").value.trim();
    var Punique=document.getElementById("Punique").value;
	var id=document.getElementById("id").value;
    if (commentText === "") {
        alert("댓글 내용을 입력해주세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/localMaster/addComment", true);
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	
	var data=JSON.stringify({
		id : id,
		comments: commentText,
		p_unique: Punique
	})
	
	xhr.send(data)
  
    xhr.onload = function() {
        if (xhr.status === 200) {
            alert("댓글이 성공적으로 추가되었습니다.");
            document.getElementById("commentText").value = ''; // 입력창 초기화
        } else {
            alert("댓글 추가에 실패했습니다.");
        }
    };

    xhr.onerror = function() {
        alert("요청 중 문제가 발생했습니다.");
    };
}

function updateCommentList(comments){
	var commentListContainer=document.getElementById("commentListContainer");
}

