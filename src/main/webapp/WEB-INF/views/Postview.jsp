<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>제목 :${Post.title}</p> 
	<p>작성자:${Post.id} 조회수 :${Post.view} 작성일:${days}</p> 
	
	<p>${Post.contents}
	<p><a href="/localMaster/Postupdate/${Post.p_unique}">수정</a>
		<a href="/localMaster/PostAllBoard">게시판</a>
		<a href="/localMaster/">홈</a>
</body>
</html>