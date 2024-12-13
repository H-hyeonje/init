var markers = [];
var markerMode = false;
var markernum = 0;
var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(36.5, 128.0),
        level: 13
    };
var map = new kakao.maps.Map(mapContainer, mapOption);

$(".region-btn").on("click", function () {
    var region = $(this).data("num");
    var latitude = null;
    var longitude = null;
    var level = null;
    if (region === "서울") {
        latitude = 37.5665;
        longitude = 126.9780;
        level = 9
    }
    if (region === "부산") {
        latitude = 35.1796;
        longitude = 129.0756;
        level = 10
    }
    if (region === "대구") {
        latitude = 35.8723;
        longitude = 128.6025;
        level = 10
    }
    var latLng = new kakao.maps.LatLng(latitude, longitude);
    map.setCenter(latLng); // 새로운 좌표로 지도 중심 변경
    map.setLevel(level);
});

document.getElementById("marker").addEventListener("click", function () {
    markerMode = !markerMode;
    this.textContent = markerMode ? "마커모드 off" : "마커모드 on";
});

document.getElementById("SearchForm").addEventListener("submit", function (event) {
    event.preventDefault();
    var search = document.getElementById("Search").value;
    var ps = new kakao.maps.services.Places();
});

kakao.maps.event.addListener(map, "click", function (event) {
    if (markerMode) {
        var latLng = event.latLng;

        // 마커 생성
        var marker = new kakao.maps.Marker({
            position: latLng,
            clickable: true
        });

        marker.setMap(map); // 지도에 마커 표시
        markerMode = false;
        document.getElementById("marker").textContent = "마커모드 on";
        markernum++;

        // 인포윈도우 내용에 마커 번호와 삭제 버튼 추가
        var infowindowContent = `
            <div>
                <button class='marker-delete-btn' data-index='${markernum}'>x</button>
                <p>Marker #${markernum}</p>
            </div>
        `;
        var infowindow = new kakao.maps.InfoWindow({
            content: infowindowContent
        });

        // 마커 클릭 시 인포윈도우 열기
        kakao.maps.event.addListener(marker, "click", function () {
            // 이미 다른 인포윈도우가 열려있으면 닫고, 클릭한 마커의 인포윈도우 열기
            if (infowindow.getMap()) {
                infowindow.close(); // 이미 열려있으면 닫기
            } else {
                // 인포윈도우가 닫혀 있으면 열기
                markers.forEach(item => {
                    if (item.infowindow.getMap()) {
                        item.infowindow.close(); // 다른 마커의 인포윈도우는 닫기
                    }
                });
                infowindow.open(map, marker); // 클릭한 마커의 인포윈도우 열기
            }
        });

        // 마커와 인포윈도우 배열에 저장
        markers.push({ marker: marker, infowindow: infowindow });

        // 삭제 버튼 클릭 이벤트 (동적 바인딩 필요)
        setTimeout(() => {
            var deleteButton = document.querySelector(`.marker-delete-btn[data-index='${markernum}']`);
            if (deleteButton) {
                deleteButton.addEventListener("click", function () {
                    // 마커 삭제
                    marker.setMap(null);
                    infowindow.close();

                    // 배열에서 제거
                    markers = markers.filter(item => item.marker !== marker);
                });
            }
        }, 0);
    }
});




