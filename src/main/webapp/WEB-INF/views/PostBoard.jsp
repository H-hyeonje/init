<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${All}" var="post">
		<span>${post.p_unique}</span>
		<a href="Postview/${post.title}/${post.p_unique}">${post.title}</a>
		<span>${post.id}</span>
		<br>
	</c:forEach>
</body>
</html>