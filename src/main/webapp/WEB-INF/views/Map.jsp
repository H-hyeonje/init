<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
	/* 전체 페이지 스타일 */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

/* 지도 영역 */
#map {
    width: 100%;
    height: 500px;
    background-color: #ddd;
    margin-top: 20px;
}

/* 16개 지역 버튼들이 배치될 컨테이너 */
.region-buttons {
    display: grid;
    grid-template-columns: repeat(4, 1fr);  /* 4개씩 가로로 배치 */
    gap: 10px;  /* 버튼 사이 간격 */
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;
}

/* 버튼 스타일 */
.region-btn {
    padding: 10px;
    background-color: #2d98f0;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
}

/* 버튼 hover 효과 */
.region-btn:hover {
    background-color: #1e7db4;
}

/* 버튼이 클릭되었을 때 스타일 */
.region-btn:active {
    background-color: #1c6ea4;
}
	
	
	
	</style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>맛집 지도</title>
</head>
<body>
 <div id="map" style="width:100%;height:500px;"></div>
	<div>
		<button id="marker">마커 만들기</button>
	</div>
	<form id="SearchForm">
		<input type="text" id="Search">
		<button id="Searchs">검색</button>
	</form>
    <!-- 16개 지역 버튼들 -->
    <div class="region-buttons">
        <button class="region-btn" data-num="서울">서울</button>
        <button class="region-btn" data-num="부산"">부산</button>
        <button class="region-btn" data-num="대구">대구</button>
    </div>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c46d3ceb006f99f0537b7a3928c66fb&libraries=services"></script>
    <script src="${pageContext.request.contextPath}/resources/js/map.js" ></script>
</body>
</html>
