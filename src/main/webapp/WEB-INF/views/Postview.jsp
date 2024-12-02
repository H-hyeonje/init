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
	<span>${Post.title}</span> 
	<span>${Post.id}</span> 
	<p>${Post.contents}
	<a href="PostBoard">게시판</a>
</body>
</html>