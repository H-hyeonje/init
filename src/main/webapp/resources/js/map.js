// 전역 변수로 map 객체를 선언하여 한 번만 생성하고 이후에는 재사용합니다.
var map;

// 지도 생성 함수
function createMap() {
    var mapContainer = document.getElementById('imageMap'); // 지도를 표시할 div
    var mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 13 // 지도의 확대 레벨
    };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다.
    map = new kakao.maps.Map(mapContainer, mapOption);
}

function marker(response) {
    var positions = [];
    
    // response에서 마커 데이터를 가져와 positions 배열에 저장
    for (var i = 0; i < response.length; i++) {
        positions.push({
            title: response[i].name, // 타이틀로 사용
            latlng: new kakao.maps.LatLng(response[i].latitude, response[i].longitude), // 위도와 경도
            name: response[i].name, // content로 사용할 name 추가
			address:response[i].address,
			phone1:response[i].phone1,
			menu:response[i].menu
        });
    }

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

    // 기존 마커들을 삭제하거나 새로 추가할 수 있습니다.
    for (let i = 0; i < positions.length; i++) {  // let 사용으로 i 값 고정
        var imageSize = new kakao.maps.Size(24, 35); // 마커 이미지 크기
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); // 마커 이미지 생성

        // 마커를 생성하고 지도에 추가
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커 위치
            title: positions[i].title, // 마커의 타이틀
            image: markerImage // 마커 이미지
        });

        // 마커 클릭 시 해당 마커에 대한 정보창 표시
		var infowindow = new kakao.maps.InfoWindow({
		    content: `
			<div class="info-window-content">
			    <strong class="info-title">${positions[i].name}</strong><br>
			    <span class="info-address">${positions[i].address}</span><br>
			    <span class="info-phone">${positions[i].phone1}</span><br>
			    <p class="info-menu">${positions[i].menu}</p>
			</div>
		    ` // 다양한 정보를 보여줄 수 있음
		});

        // 클로저를 이용해 marker와 infowindow를 연결
        kakao.maps.event.addListener(marker, 'click', (function(marker, infowindow) {
            return function() {
                infowindow.open(map, marker);  // 클릭 시 정보창을 마커와 함께 열기
            };
        })(marker, infowindow));  // 즉시 호출 함수로 클로저를 사용하여 변수 전달
    }
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

// 최초 페이지 로드 시 지도 생성
$(document).ready(function() {
    createMap(); // 한 번만 호출하여 지도 생성
});

// AJAX 요청 후 마커 업데이트
$("#Dining").on("click", function() {
    var page = $("#Dining").val(); // 버튼의 value 값을 가져옵니다.
    $.ajax({
        url: "/localMaster/Maps",
        type: "GET",
        contentType: "application/json;charset=UTF-8",
        data: { num: page }, // 서버로 전달할 데이터
        success: function(response) {
            marker(response); // 서버 응답으로 마커 추가
        },
        error: function(xhr, status, error) {
            console.error("좋아요 실패: " + error);
        }
    });
});

