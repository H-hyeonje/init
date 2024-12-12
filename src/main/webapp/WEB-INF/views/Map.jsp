<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.info-window-content {
    font-family: 'Arial', sans-serif;
    background-color: #fff;
    border-radius: 8px;
    padding: 15px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 200px; /* 너비 조정 */
    color: #333;
}

.info-title {
    font-size: 18px;
    font-weight: bold;
    color: #2c3e50;
    margin-bottom: 10px;
    display: block;
}

.info-address {
    font-size: 14px;
    color: #7f8c8d;
    margin-bottom: 8px;
    display: block;
}

.info-phone {
    font-size: 14px;
    color: #3498db;
    margin-bottom: 10px;
    display: block;
}

.info-menu {
    font-size: 14px;
    color: #16a085;
    margin-top: 8px;
    display: block;
    word-wrap: break-word;
}

/* 추가적인 작은 스타일 */
.info-menu p {
    margin-top: 5px;
    color: #16a085;
}


</style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>카카오 맵 - 전국 맛집 지도</title>
</head>
<body>
    <button id="Dining" value="1">맛집</button>
    <div id="imageMap" style="width:100%;height:500px;"></div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c46d3ceb006f99f0537b7a3928c66fb"></script>
    <script src="${pageContext.request.contextPath}/resources/js/map.js" ></script>
</body>
</html>
