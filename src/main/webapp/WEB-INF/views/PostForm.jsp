<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Postadd" method="post" >
		<p>아이디<input name="id" type="text"/>
		<p>제목<input name="title" type="text"/>
		<p>내용<input name="contents" type="text"/>
		<p>지역<input name="region" type="text"/>
		<p>공개<input name="isPrivate" type="radio" value="true"/>
		<p>비공개<input name="isPrivate" type="radio" value="false"/>
		<p>만족도<input name="satisfaction" type="text"/>
		<p><input type="submit" value="작성"/>
	</form>
</body>
</html>