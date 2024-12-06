<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .edit-form {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: inline-block;
            width: 100px;
            font-weight: bold;
            margin-right: 10px;
        }

        .form-group input[type="text"],
        .form-group input[type="number"] {
            width: calc(100% - 120px);
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .radio-group {
            display: inline-block;
        }

        .radio-group label {
            margin-right: 20px;
            font-weight: normal;
            width: auto;
        }

        .privacy-status {
            color: #666;
            margin-left: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
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
    <form action="/localMaster/PostUpdate" method="post" class="edit-form">
        <div class="form-group">
            <label>아이디:</label>
            <span>${postToEdit.id}</span>
            <input type="hidden" name="id" value="${postToEdit.id}">
        </div>
        
        <div class="form-group">
            <label>제목:</label>
            <input name="title" type="text" value="${postToEdit.title}" required/>
        </div>
        
        <div class="form-group">
            <label>내용:</label>
            <textarea name="contents" rows="5" style="width: calc(100% - 120px); padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box;">${postToEdit.contents}</textarea>
        </div>
        
        <div class="form-group">
            <label>지역:</label>
            <input name="region" type="text" value="${postToEdit.region}" required/>
        </div>
        
        <div class="form-group">
            <label>공개여부:</label>
            <span class="privacy-status">[${privacyStatus}]</span>
            <div class="radio-group">
                <c:choose>
                    <c:when test="${privacyStatus eq '공개'}">
                        <label><input name="isPrivate" type="radio" value="1" checked/> 공개</label>
                        <label><input name="isPrivate" type="radio" value="0"/> 비공개</label>
                    </c:when>
                    <c:otherwise>
                        <label><input name="isPrivate" type="radio" value="1"/> 공개</label>
                        <label><input name="isPrivate" type="radio" value="0" checked/> 비공개</label>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
        <div class="form-group">
            <label>만족도:</label>
            <input name="satisfaction" type="number" value="${postToEdit.satisfaction}" min="0" max="10"/>
        </div>
        
        <input type="hidden" name="p_unique" value="${postToEdit.p_unique}">
        <input type="submit" value="수정하기"/>
    </form>
    
    <a href="/localMaster/" class="home-link">홈</a>
</body>
</html>