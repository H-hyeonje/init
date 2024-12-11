

function submitComment() {
    var commentText = $("#commentText").val();
    var userid = $("#id").val();
    var postid = $("#Punique").val();
    var page = $("#pages").val();
	console.log(page)
	

	if(commentText.trim() ===""){
		alert("댓글을 입력해주세요");
		return false;
	}
	if(userid.length>5){
		alert("아이디 5글자 이하로 해주세요");
			return false;
	}
	if(userid.trim() ==="" ){
		alert("아이디를 입력해주세요");
		return false;
	}
	
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


//좋아요
$("#commentListContainer").on("click", ".like", function() {
    var num = $(this).data("num");
    alert("좋아요 버튼 클릭됨! num-" + num);
	
	$.ajax({
		url:"/localMaster/updateLike",
		type :"POST",
		contentType:"application/json;charset=UTF-8",
		data:JSON.stringify({c_unique : num}),
		success:function(response){
			console.log("좋아요 성공 !")
			var updateLike=response;
			var likeButton = $("button.like[data-num='" + num + "']");
			            likeButton.text("❤️ " + updateLike);
		
			
		},
		error:function(xhr,status,error){
			console.error("좋아요 실패 :"+error);
		}
		
		
	})
	
	
	
	
	
});

// 삭제
$("#commentListContainer").on("click", ".del", function() {
    var postid = $("#Punique").val();  // 게시물 ID
    var num = $(this).data("del");  // 삭제할 댓글의 c_unique

    alert("삭제버튼! num-" + num);
	
 
    $.ajax({
        url: "/localMaster/commentDelete",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
            c_unique: num,
            p_unique: postid
        }),
        success: function(response) {
            console.log("삭제 성공!");
            
            var remainingComments = response.remainingComments;
            var currentPage = response.currentPage;
            var pageNumbers = response.pageNumbers;

            // 현재 페이지에 댓글이 없으면 이전 페이지로 이동
            if (currentPage > 1 && remainingComments % 5 === 0) {
                currentPage--;
            }

            // 댓글 목록을 다시 불러옴
            $.ajax({
                url: "/localMaster/readComment",
                type: "GET",
                data: {
                    p_unique: postid,
                    page: currentPage
                },
                success: function(response) {
                    comments(response);
                },
                error: function(xhr, status, error) {
                    console.error("댓글 목록 조회 실패:", error);
                }
            });
        },
        error: function(xhr, status, error) {
            console.error("댓글 삭제 실패:", error);
        }
    });
});
	
	
	




function comments(response) {
    var tol = response.tol;
    var pagenum = response.pagenum;
    var commentList = response.comment;
    var dateList = response.formattedDates;

    var str = "";
    var str2 = "";

 	console.log("넘"+Object.keys(commentList).length)
 	console.log("넘2:"+tol)
	if(Object.keys(commentList).length===5){
	tol= Number(tol) + 1;
	}

    $.each(commentList, function(i, comment) {
        str += "<div class='comment-item'>";
        str += "<p><b>" + comment.id + "</b> " + dateList[i] + 
               " <button class='like' data-num='" + comment.c_unique + "'>❤️" + comment.commentLikes + "</button>" +
               " <button class='del' data-del='" + comment.c_unique + "'>X</button></p>";
        str += "<p>" + comment.comments + "</p>";
        str += "</div>";
    });

    $.each(pagenum, function(i, num) {
        str2 += " <button class='commentPaging' data-page='" + num + "'>[" + num + "]</button> ";
    });

    $("#commentListContainer").html(
        str + 
        "<input type='hidden' id='pages' value='" + tol + "'>" + 
        "<div id='page'> " + str2 + " </div>"
    );
}

document.addEventListener('DOMContentLoaded', () => {
    // 페이지 버튼 클릭 이벤트
    document.querySelectorAll('.page').forEach(button => {
        button.addEventListener('click', (event) => {
            const page = event.target.getAttribute('data-page'); // data-page 값 가져오기
            fetch(`/comments?postId=1&page=${page}`) // 서버에 Ajax 요청
                .then(response => response.text())
                .then(data => {
                    // 댓글 리스트 갱신
                    document.querySelector('.comments').innerHTML = data;
                })
                .catch(error => console.error('Error fetching comments:', error));
        });
    });
});


document.addEventListener('DOMContentLoaded', function() {
    var postid = $("#Punique").val();
    
    // 이벤트 위임을 사용하여 동적으로 생성된 버튼에도 이벤트 연결
    $(document).on('click', '.page, .commentPaging', function() {
        var pageNumber = $(this).data('page');
        console.log('페이지 ' + pageNumber + ' 버튼이 클릭되었습니다.');
        
        $.ajax({
            url: "/localMaster/readComment",
            type: "GET",
            data: {
                p_unique: postid,
                page: pageNumber
            },
            success: function(response) {
                comments(response);
            },
            error: function(xhr, status, error) {
                console.error("댓글 목록 조회 실패:", error);
            }
        });
    });
});



function comments(response) {
    var tol = response.tol;
    var pagenum = response.pagenum;
    var commentList = response.comment;
    var dateList = response.formattedDates;

    var str = "";
    var str2 = "";

 	console.log("넘"+Object.keys(commentList).length)
 	console.log("넘2:"+tol)
	if(Object.keys(commentList).length===5){
	tol= Number(tol) + 1;
	}

    $.each(commentList, function(i, comment) {
        str += "<div class='comment-item'>";
        str += "<p><b>" + comment.id + "</b> " + dateList[i] + 
               " <button class='like' data-num='" + comment.c_unique + "'>❤️" + comment.commentLikes + "</button>" +
               " <button class='del' data-del='" + comment.c_unique + "'>X</button></p>";
        str += "<p>" + comment.comments + "</p>";
        str += "</div>";
    });

    $.each(pagenum, function(i, num) {
        str2 += " <button class='commentPaging' data-page='" + num + "'>[" + num + "]</button> ";
    });

    $("#commentListContainer").html(
        str + 
        "<input type='hidden' id='pages' value='" + tol + "'>" + 
        "<div id='page'> " + str2 + " </div>"
    );
}
