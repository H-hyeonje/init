// 전역 변수
var map; // 지도 객체
var ps; // 장소 검색 객체
var infoWindow; // 인포창 객체
var searchMarkers = []; // 검색 마커 배열
var myList = []; // My 리스트 배열
var originalMarkers = []; // 원래 마커 데이터를 저장하는 배열
var myListMarkers = [];
var currentOpenMarker = null; // 현재 열린 인포창의 마커

// 지도 초기화
window.onload = function () {
    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(37.5665, 126.9780),
        level: 5
    };

    map = new kakao.maps.Map(container, options);
    ps = new kakao.maps.services.Places();
    infoWindow = new kakao.maps.InfoWindow({ zIndex: 1 });
};

// 마커 삭제 함수
function clearMarker(marker) {
    if (marker) {
        marker.setMap(null);
    }
}

// 장소 검색
function searchPlaces() {
    var keyword = document.getElementById('keyword').value;

    if (!keyword.trim()) {
        alert('검색어를 입력해주세요!');
        return;
    }

    ps.keywordSearch(keyword, function (data, status) {
        if (status === kakao.maps.services.Status.OK) {
            displayPlaces(data);
        } else {
            alert('검색 결과가 없습니다.');
        }
    });
}

// 인포창 토글 기능
function toggleInfoWindow(marker, content) {
    if (currentOpenMarker === marker) {
        infoWindow.close();
        currentOpenMarker = null;
    } else {
        infoWindow.setContent(content);
        infoWindow.open(map, marker);
        currentOpenMarker = marker;
    }
}

function displayPlaces(places) {
    console.log('검색된 장소:', places); // 검색된 장소 로그 출력

    var listEl = document.getElementById('placesList');
    var bounds = new kakao.maps.LatLngBounds();

    // 기존 마커 초기화
    searchMarkers.forEach(item => clearMarker(item.marker));
    searchMarkers = [];
    originalMarkers = []; // 원본 마커 배열 초기화

    listEl.innerHTML = '';

    for (let i = 0; i < places.length; i++) {
        let place = places[i];
        let position = new kakao.maps.LatLng(place.y, place.x);

        // 마커 생성
        let marker = new kakao.maps.Marker({
            position: position,
            map: map
        });

        // 검색 마커 및 원본 마커 저장
        searchMarkers.push({ id: place.id, marker: marker });
        originalMarkers.push({ id: place.id, lat: place.y, lng: place.x, name: place.place_name });

        console.log('현재 원본 마커 배열 상태:', originalMarkers); // 원본 마커 배열 상태 출력

        // 인포창 내용 설정
        let content = `
            <div style="padding:5px; font-size:12px;">
                <strong>${place.place_name}</strong><br>
                ${place.road_address_name || place.address_name}
            </div>
        `;

        // 마커 클릭 이벤트
        kakao.maps.event.addListener(marker, 'click', function () {
            toggleInfoWindow(marker, content);
        });

        // 리스트 항목 생성
        var itemEl = document.createElement('li');
        itemEl.innerHTML = `
            <span style="cursor:pointer;"><strong>${place.place_name}</strong></span><br>
            <small>${place.road_address_name || place.address_name}</small><br>
            <button onclick="swapMarker('${place.id}', ${place.y}, ${place.x}, '${place.place_name}')">My 리스트 추가</button>
        `;
        itemEl.style.padding = '10px';
        itemEl.style.borderBottom = '1px solid #ddd';

        // 리스트 클릭 이벤트
        (function (marker, content) {
            itemEl.onclick = function (event) {
                if (event.target.tagName === 'BUTTON') return; // 버튼 클릭 무시
                map.panTo(marker.getPosition());
                toggleInfoWindow(marker, content);
            };
        })(marker, content);

        listEl.appendChild(itemEl);
        bounds.extend(position);
    }

    map.setBounds(bounds);
}






// My 리스트에 마커 추가
function swapMarker(id, lat, lng, name) {
    if (myList.some(item => item.id === id)) {
        alert('이미 My 리스트에 추가된 장소입니다!');
        return;
    }

    const userConfirmed = confirm(`"${name}"을 My 리스트에 추가하시겠습니까?\n위도: ${lat}, 경도: ${lng}`);

    if (userConfirmed) {
        // 기존 검색 마커 삭제
        let index = searchMarkers.findIndex(item => item.id === id);
        if (index !== -1) {
            clearMarker(searchMarkers[index].marker);
            searchMarkers.splice(index, 1);
        }

        // My 리스트 마커 생성
        let markerImage = new kakao.maps.MarkerImage(
            'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',
            new kakao.maps.Size(24, 35)
        );

        let newMarker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(lat, lng),
            map: map,
            image: markerImage
        });

        // My 리스트에 마커 추가
        myList.push({ id, name, marker: newMarker });
        
        // My 리스트에 마커 정보 저장
        myListMarkers.push({ id, lat, lng, name });

        // 콘솔로 상태 출력
        console.log('현재 My 리스트 마커 배열 상태:', myListMarkers);

        updateMyList();
    } else {
        alert('My 리스트에 추가가 취소되었습니다.');
    }
}




// My 리스트 갱신
function updateMyList() {
    var myListEl = document.getElementById('myPlacesList');
    myListEl.innerHTML = '';

    for (let i = 0; i < myList.length; i++) {
        let item = myList[i];
        var itemEl = document.createElement('li');
        itemEl.innerHTML = `
            <span>${item.name}</span>
            <button onclick="removeFromMyList('${item.id}')">삭제</button>
        `;
        itemEl.style.padding = '10px';
        itemEl.style.borderBottom = '1px solid #ddd';

        myListEl.appendChild(itemEl);
    }
}

function removeFromMyList(id) {
    let index = myList.findIndex(item => item.id === id);

    if (index !== -1) {
        let item = myList[index];
        clearMarker(item.marker); // My 리스트 마커 삭제

        // My 리스트에서 삭제한 후 원래 마커 복원
        let original = myListMarkers.find(marker => marker.id === id);
        if (original) {
            let restoredMarker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(original.lat, original.lng), // 원래 위치
                map: map
            });

            searchMarkers.push({ id: original.id, marker: restoredMarker });

            // 복원된 마커에 클릭 이벤트 추가
            let content = `
                <div style="padding:5px; font-size:12px;">
                    <strong>${original.name}</strong>
                </div>
            `;
            kakao.maps.event.addListener(restoredMarker, 'click', function () {
                toggleInfoWindow(restoredMarker, content);
            });
        }

        myList.splice(index, 1); // My 리스트에서 제거
        myListMarkers.splice(myListMarkers.findIndex(item => item.id === id), 1); // 마커 정보 배열에서 제거
        updateMyList();
    }
}


// My 리스트 업데이트
function updateMyList() {
    var myListEl = document.getElementById('myPlacesList');
    myListEl.innerHTML = '';

    for (let i = 0; i < myList.length; i++) {
        let item = myList[i];

        var itemEl = document.createElement('li');
        itemEl.innerHTML = `
            <span>${item.name}</span>
            <button onclick="removeFromMyList('${item.id}')">삭제</button>
        `;
        itemEl.style.padding = '10px';
        itemEl.style.cursor = 'pointer';

        myListEl.appendChild(itemEl);
    }
}

// My 리스트에서 삭제
function removeFromMyList(id) {
    let index = myList.findIndex(item => item.id === id);
    if (index !== -1) {
        clearMarker(myList[index].marker);
        myList.splice(index, 1);
        updateMyList();
    }
}



