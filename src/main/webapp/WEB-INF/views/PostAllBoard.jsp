<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AllBoard</title>
</head>
<body>
	<a href="/localMaster/">홈</a>
	<table>
    <thead>
        <tr>
            <th></th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>작성일</th>
            <th>조회</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${All}" var="post" varStatus="loop">
            <tr>
                <td>${Allnumber.get((ps-1) * 5 + loop.index)}</td>
                <td><a href="/localMaster/Postview/${post.p_unique}">${post.title}</a></td>
                <td>${post.id}</td>
                <td>${post.view}</td>
                <td>${time[loop.index]}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<br>
 	<c:forEach items="${total}" var="ps" >
        	<a href="/localMaster/PostAllBoard/${ps}"><span>[${ps}]</span></a>
        </c:forEach>
	
</body>
</html>