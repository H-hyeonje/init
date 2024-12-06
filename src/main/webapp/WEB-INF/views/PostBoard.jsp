<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>개인 게시판</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px;
        }

        .home-link {
            display: inline-block;
            text-decoration: none;
            color: #333;
            margin-bottom: 20px;
            padding: 8px 15px;
            border-radius: 4px;
            background-color: #f0f0f0;
        }

        .home-link:hover {
            background-color: #e0e0e0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f5f5f5;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        td a {
            text-decoration: none;
            color: #2c3e50;
        }

        td a:hover {
            color: #3498db;
        }

        .pagination {
            margin-top: 20px;
            text-align: center;
        }

        .pagination a {
            display: inline-block;
            padding: 8px 12px;
            margin: 0 4px;
            border: 1px solid #ddd;
            text-decoration: none;
            color: #333;
            border-radius: 4px;
        }

        .pagination a:hover {
            background-color: #f5f5f5;
        }

        .pagination span {
            color: #333;
        }
    </style>
</head>
<body>
    <a href="/localMaster/" class="home-link">홈</a>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>조회</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userId}" var="post" varStatus="loop">
                <tr>
                    <td>${postPageNumbers.get((ps-1)*5+loop.index)}</td>
                    <td><a href="/localMaster/Postview/${post.p_unique}">${post.title}</a></td>
                    <td>${post.id}</td>
                    <td>${post.view}</td>
                    <td>${formattedBoardTimes[status.index]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:forEach items="${totalPageNumbers}" var="ps">
            <a href="/localMaster/PostBoard/${ps}?id=${userId[0].id}"><span>[${ps}]</span></a>
        </c:forEach>
    </div>
</body>
</html>