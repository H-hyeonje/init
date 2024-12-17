<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
    /* 스타일은 필요에 맞추어 조정 */
    #map { width: 100%; height: 500px; }
    #placesList, #myList {
        border: 1px solid #ddd; 
        padding: 10px;
        min-height: 200px;
    }
    .listItem {
        display: flex; 
        justify-content: space-between; 
        padding: 5px; 
        cursor: pointer;
    }
    .locationBtn { margin-right: 5px; }
</style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    
</head>
<body>
	<%  String[] buttons = {"서울", "대전", "대구", "부산", "제주", "경남"}; 
	request.setAttribute("buttons", buttons);
	%>
   <div id="map"></div>
    <div>
        <c:forEach items="${buttons}" var="a">
            <button class="locationBtn">${a}</button>
        </c:forEach> 
    </div>
    <div>
        <input type="text" id="searchBox" placeholder="검색">
        <button id="searchBtn">검색</button>
    </div>
    <div style="display: flex; justify-content: space-between; gap: 20px;">
        <!-- 검색 결과 리스트 -->
        <div style="width: 50%;">
            <h3>검색 결과</h3>
            <div id="placesList"></div>
        </div>

        <!-- 마이 리스트 -->
        <div style="width: 50%;">
            <h3>마이 리스트</h3>
            <div id="myList">
                <p>저장된 장소가 없습니다.</p>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c46d3ceb006f99f0537b7a3928c66fb&libraries=services"></script>
   	<script src="${pageContext.request.contextPath}/resources/js/map.js" ></script>
</body>
</html>
