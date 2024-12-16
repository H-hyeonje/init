<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c46d3ceb006f99f0537b7a3928c66fb&libraries=services"></script>
   	<script src="${pageContext.request.contextPath}/resources/js/map.js" ></script>
    
</head>
<body>
	<div id="map" style="width:100%; height:500px;"></div>
<input type="text" id="keyword" placeholder="검색어를 입력하세요">
<button onclick="searchPlaces()">검색</button>

<h3>검색 결과</h3>
<ul id="placesList" style="list-style:none; padding:0;"></ul>

<h3>My 리스트</h3>
<ul id="myPlacesList" style="list-style:none; padding:0;"></ul>
	
</body>
</html>
