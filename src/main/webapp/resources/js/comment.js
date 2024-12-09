function submitComment() {
    var commentText = $("#commentText").val();
    var userid = $("#id").val();
    var postid = $("#Punique").val();
    var page = $("#pages").val();
	console.log(page)
	
    $.ajax({
        url: "/localMaster/addComment",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
            comments: commentText,
            id: userid,
            p_unique: parseInt(postid)
        }),
        success: function() {
            console.log("댓글 추가 성공");

            // 두 번째 AJAX 호출을 success 콜백 안에 넣어야 합니다.
            $.ajax({
                url: "/localMaster/readComment", // URL
                type: "GET", // GET 요청
                data: {
                    p_unique:postid,
                    page: page
                },
                success: function(response) {
                    comments(response); // 댓글 처리 함수 호출
                    $("#commentText").val(""); // 댓글 입력란 비우기
                },
                error: function(xhr, status, error) {
                    console.error("댓글 추가 실패:", error);
                }
            });
        },
        error: function(xhr, status, error) {
            console.error("댓글 추가 실패:", error);
        }
    });
}

function comments(response) {
	var tol=response.tol;
    var pagenum = response.pagenum;
    var commentList = response.comment;
    var dateList = response.formattedDates;
    var str = "";
    var str2 = "";

    $.each(commentList, function(i, comment) {
        str += "<div class='comment-item'>";
        str += "<p><b>" + comment.id + "</b> " + dateList[i] + " <button id='like-" + comment.c_unique + "'>❤️" + comment.commentLikes + "</button></p>";
        str += "<p>" + comment.comments + "</p>";
        str += "</div>";
    });
 	
    $.each(pagenum, function(i, num) {
        str2 += " <button class='commentPaging' data-page='" + num + "'>[" + num + "]</button> ";
    });

    $("#commentListContainer").html("<input type=hidden id=pages value='"+tol +"'><div id='page'> " + str2 + " </div>");
}
