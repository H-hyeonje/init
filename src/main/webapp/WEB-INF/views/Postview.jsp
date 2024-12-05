<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="${pageContext.request.contextPath}/resources/js/comment.js"></script>
</head>
<body>
	<p>제목 :${Post.title}</p> 
	<p>작성자:${Post.id} 조회수 :${Post.view} 작성일:${publishDate}</p> 
	
	<p>${Post.contents}
	
		
	<p>-----------------------------------------------------
	<form id="commentform">
		<input type="text" id="id">
		<input type="hidden" value="${Post.p_unique}" id="Punique">
		<textarea rows="3" cols="40" id="commentText"></textarea>	
		<button type="button" onclick="submitComment()">댓글 작성</button>
	</form>
	<p><a href="/localMaster/PostupdatePage/${Post.id}/${Post.p_unique}">수정</a>
		<a href="/localMaster/PostDelete/${Post.id}/${Post.p_unique}">삭제</a>
		<a href="/localMaster/PostAllBoard/1">게시판</a>
		<a href="/localMaster/">홈</a>
</body>
</html>