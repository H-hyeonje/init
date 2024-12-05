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
	<form action="/localMaster/PostUpdate" method="post" >
		
		<p>아이디 : ${postToEdit.id}<input type="hidden" name="id"  value="${postToEdit.id}">
		<p>제목 : <input name="title" type="text" value="${postToEdit.title}"/>
		<p>내용 : <input name="contents" type="text" value="${postToEdit.contents}"/>
		<p>지역 : <input name="region" type="text" value="${postToEdit.region}"/>
		<p>공개여부 : [${privacyStatus}]
		<c:choose>
		<c:when test="${privacyStatus eq '공개'}">
		<p>공개 : <input name="isPrivate" type="radio" value="1" checked/> 비공개<input name="isPrivate" type="radio" value="0"/>
		</c:when>
		<c:otherwise>
		<p>공개 : <input name="isPrivate" type="radio" value="1" /> 비공개<input name="isPrivate" type="radio" value="0" checked/>
		</c:otherwise>
		</c:choose>
		<p>만족도 : <input name="satisfaction" type="number" value="${postToEdit.satisfaction}"/>
		<p><input type="submit" value="작성"/>
		<input type="hidden" name="p_unique" value="${postToEdit.p_unique}">
	</form>
	<a href="/localMaster/">홈</a>
</body>
</html>