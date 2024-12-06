<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .post-form {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input[type="text"],
        .form-group input[type="number"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .radio-group {
            margin: 10px 0;
        }

        .radio-group label {
            margin-right: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .home-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #333;
            padding: 8px 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .home-link:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <form action="Postadd" method="post" class="post-form">
        <div class="form-group">
            <label for="id">아이디</label>
            <input id="id" name="id" type="text" required/>
        </div>
        
        <div class="form-group">
            <label for="title">제목</label>
            <input id="title" name="title" type="text" required/>
        </div>
        
        <div class="form-group">
            <label for="contents">내용</label>
            <textarea id="contents" name="contents" rows="5" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box;"></textarea>
        </div>
        
        <div class="form-group">
            <label for="region">지역</label>
            <input id="region" name="region" type="text" required/>
        </div>
        
        <div class="form-group">
            <label>공개 설정</label>
            <div class="radio-group">
                <label>
                    <input name="isPrivate" type="radio" value="1" required/> 공개
                </label>
                <label>
                    <input name="isPrivate" type="radio" value="0"/> 비공개
                </label>
            </div>
        </div>
        
        <div class="form-group">
            <label for="satisfaction">만족도</label>
            <input id="satisfaction" name="satisfaction" type="number" min="0" max="10"/>
        </div>
        
        <input type="submit" value="작성"/>
    </form>
    
    <a href="/localMaster/" class="home-link">홈</a>
</body>
</html>