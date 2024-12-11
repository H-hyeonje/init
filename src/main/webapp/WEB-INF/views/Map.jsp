<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>

<body>
	<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c46d3ceb006f99f0537b7a3928c66fb"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
	
	<form action="return setMap()">
		<input type="text" id="x">
		<input type="text" id="y">	
		<input type="submit">	
	</form>
	
	<script>
	function setMap() {
	    var x = parseFloat(document.getElementById('x').value); // 위도
	    var y = parseFloat(document.getElementById('y').value); // 경도

	    // 위도와 경도가 유효한 값인지 확인
	    if (isNaN(x) || isNaN(y)) {
	        alert("위도와 경도를 올바르게 입력하세요.");
	        return false; // 폼 제출을 막고, 맵을 표시하지 않음
	    }

	    // 지도 생성
	    var container = document.getElementById('map');
	    var options = {
	        center: new kakao.maps.LatLng(x, y), // 입력받은 위도, 경도
	        level: 3 // 확대 레벨
	    };

	    // 카카오 맵 객체 생성
	    var map = new kakao.maps.Map(container, options);

	    // 마커를 지도에 표시
	    var marker = new kakao.maps.Marker({
	        position: new kakao.maps.LatLng(x, y) // 마커 위치 설정
	    });
	    marker.setMap(map);

	    return false; // 폼 제출을 막고, 페이지 리로드 방지
	}

		var map = new kakao.maps.Map(container, options);
		var clusterer = new kakao.maps.MarkerClusterer({
		    map: map,
		    markers: markers,
		    gridSize: 35,
		    averageCenter: true,
		    minLevel: 6,
		    disableClickZoom: true,
		    styles: [{
		        width : '53px', height : '52px',
		        background: 'url(cluster.png) no-repeat',
		        color: '#fff',
		        textAlign: 'center',
		        lineHeight: '54px'
		    }]
		});
		
	</script>

</body>
</html>