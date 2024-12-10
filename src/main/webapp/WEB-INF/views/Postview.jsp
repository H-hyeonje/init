<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${Post.title}</title>
    <script src="${pageContext.request.contextPath}/resources/js/comment.js" defer></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .post-header {
            border-bottom: 2px solid #eee;
            padding-bottom: 15px;
            margin-bottom: 20px;
        }

        .post-title {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .post-info {
            color: #666;
            font-size: 14px;
        }

        .post-content {
            min-height: 200px;
            margin: 20px 0;
            line-height: 1.6;
        }

        .divider {
            border-top: 1px solid #eee;
            margin: 20px 0;
        }

        .comment-form {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 4px;
            margin: 20px 0;
        }

        .comment-form input[type="text"] {
            padding: 8px;
            width: 200px;
            margin-bottom: 10px;
        }

        .comment-form textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            resize: vertical;
        }

        .comment-form button {
            padding: 8px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .comment-form button:hover {
            background-color: #45a049;
        }

        .comment-list {
            margin-top: 20px;
        }

        .comment {
            border-bottom: 1px solid #eee;
            padding: 15px 0;
        }

        .comment-author {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .action-links {
            margin-top: 20px;
        }

        .action-links a {
            text-decoration: none;
            color: #333;
            margin-right: 15px;
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .action-links a:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <div class="post-header">
        <h1 class="post-title">${Post.title}</h1>
        <div class="post-info">
            작성자: ${Post.id} | 조회수: ${Post.view} | 작성일: ${publishDate}
        </div>
    </div>

    <div class="post-content">
        ${Post.contents}
    </div>

    <div class="divider"></div>

    <form id="commentform" class="comment-form">
        <input type="text" id="id" placeholder="아이디">
        <input type="hidden" value="${Post.p_unique}" id="Punique">
        <textarea id="commentText" rows="3" placeholder="댓글을 입력하세요"></textarea>
        <button type="button" onclick="submitComment()">댓글 작성</button>
    </form>

    <div id="commentListContainer" class="comment-list">
	<c:forEach items="${comments}" var="commentList" varStatus="loop">
		<div class='comment-item'>
		<p><b>${commentList.id }</b> ${formattedDates[loop.index]} <button class="like" data-num="${commentList.c_unique}">❤️${commentList.commentLikes}</button>
			<button class="del" data-del="${commentList.c_unique}">X</button>
		</p>
		<p>${commentList.comments}</p>
		</div>
	</c:forEach>
	<div id="page">
	<input type="hidden" id="pages" value="${tol}"><p>
	<c:forEach items="${totalPage}" var="total" varStatus="loop">
		<c:if test="${total>=1}">
		<button id="page-${total}">[${total}]</button>
		</c:if>
	</c:forEach>
	</div>
    </div>
    
    <div class="action-links">
        <a href="/localMaster/PostupdatePage/${Post.id}/${Post.p_unique}">수정</a>
        <a href="/localMaster/PostDelete/${Post.id}/${Post.p_unique}">삭제</a>
        <a href="/localMaster/PostAllBoard/1">게시판</a>
        <a href="/localMaster/">홈</a>
    </div>
</body>
</html>