<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSON Test</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .nav-links {
            margin-bottom: 30px;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }

        .nav-links a {
            text-decoration: none;
            color: #333;
            margin-right: 20px;
            padding: 5px 10px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .nav-links a:hover {
            background-color: #f0f0f0;
        }

        .search-form {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
        }

        .search-form p {
            margin: 10px 0;
        }

        .search-form input[type="text"] {
            padding: 8px;
            width: 200px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .search-form input[type="submit"] {
            padding: 8px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .search-form input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="nav-links">
        <a href="Post">Post작성</a>
        <a href="PostAllBoard/1">게시판</a>
        <a href="editor">에디터</a>
    </div>

    <form class="search-form" action="PostBoard/1" method="get">
        <p>
            <label for="id">ID:</label>
            <input id="id" name="id" type="text">
        </p>
        <p>
            <input value="가자" type="submit">
        </p>
    </form>
</body>
</html>