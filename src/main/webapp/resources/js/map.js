document.addEventListener("DOMContentLoaded", function() {
    var map, ps;

    var searchResultsOriginal = []; 
    var searchMarkersOriginal = [];
    var searchList = [];
    var searchMarkers = [];
    var myList = [];           
    var myListMarkers = [];    

    var infoWindowHover = new kakao.maps.InfoWindow(); 
    var infoWindowClick = new kakao.maps.InfoWindow();
    var activeMarker = null;   

    var markerImageActive = createMarkerImage(
        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', 
        36, 52, 18, 52
    );

    var markerImageYellow = createMarkerImage(
        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', 
        24, 35, 12, 35
    );

    var mapContainer = document.getElementById('map');
    var searchBox = document.getElementById("searchBox");
    var searchBtn = document.getElementById("searchBtn");
    var placesListEl = document.getElementById("placesList");
    var myListEl = document.getElementById("myList");

    initMap();
    initEvents();

    function initMap() {
        var mapOption = { 
            center: new kakao.maps.LatLng(37.5665, 126.9780), 
            level: 11 
        };
        map = new kakao.maps.Map(mapContainer, mapOption);
        ps = new kakao.maps.services.Places();
    }

    function initEvents() {
        kakao.maps.event.addListener(map, 'click', function () {
            resetActiveMarker();
            closeInfoWindows();
        });

        searchBtn.addEventListener("click", function() {
            var keyword = searchBox.value.trim();
            if (keyword === "") {
                alert("검색어를 입력하세요");
                return;
            }
            ps.keywordSearch(keyword, placesSearchCB);
        });
    }

    function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            clearSearchResults(); 

            data.forEach(function(place, i) {
                place.originalIndex = i;
                place.inMyList = false; 
            });
            
            searchResultsOriginal = data.slice();
            searchMarkersOriginal = data.map(function(place) {
                var marker = createMarker(new kakao.maps.LatLng(place.y, place.x));
                marker.isMyList = false;
                marker.placeId = place.id;
                marker.originalIndex = place.originalIndex;
                return marker;
            });

            searchList = data.slice();
            searchMarkers = searchMarkersOriginal.slice();

            renderSearchResults(true);
        } else {
            alert("검색 결과가 없습니다.");
        }
    }

    function renderSearchResults(setBoundsOnFirstLoad) {
        placesListEl.innerHTML = ""; 
        var bounds = new kakao.maps.LatLngBounds();

        searchList.sort((a,b) => a.originalIndex - b.originalIndex);
        searchMarkers.sort((a,b) => a.originalIndex - b.originalIndex);

        searchList.forEach(function(place, index) {
            var marker = searchMarkers[index];

            if (!place.inMyList) {
                marker.setMap(map);
            } else {
                marker.setMap(null);
            }

            addMarkerHoverEvent(marker, place);
            addMarkerClickEvent(marker, place);

            var listItem = createSearchListItem(place, index, marker);
            placesListEl.appendChild(listItem);

            if (!place.inMyList) {
                bounds.extend(marker.getPosition());
            }
        });

        if (setBoundsOnFirstLoad) {
            map.setBounds(bounds);
        }
    }

    function createMarker(position, image) {
        var markerOptions = { position: position };
        if (image) {
            markerOptions.image = image;
        }
        return new kakao.maps.Marker(markerOptions);
    }

    function createMarkerImage(src, width, height, offsetX, offsetY) {
        return new kakao.maps.MarkerImage(
            src,
            new kakao.maps.Size(width, height),
            { offset: new kakao.maps.Point(offsetX, offsetY) }
        );
    }

    function addMarkerHoverEvent(marker, place) {
        kakao.maps.event.addListener(marker, 'mouseover', function() {
            if (!marker.isMyList && activeMarker !== marker) {
                infoWindowHover.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                infoWindowHover.open(map, marker);
            }
        });
        kakao.maps.event.addListener(marker, 'mouseout', function() {
            if (!marker.isMyList) {
                infoWindowHover.close();
            }
        });
    }

    function addMarkerClickEvent(marker, place) {
        kakao.maps.event.addListener(marker, 'click', function () {
            if (marker.isMyList) {
                // 마이리스트 마커 로직
                if (!marker.myInfoWindow) {
                    marker.myInfoWindow = new kakao.maps.InfoWindow();
                    marker.myInfoOpen = false;
                }

                if (!marker.myInfoOpen) {
                    showMyListInfoWindow(marker, place);
                } 
                // 열려있다면 X를 누르기 전까지 아무동작 안함
            } else {
                // 검색 마커 로직
                updateActiveMarker(marker);
                showInfoWindowClick(marker, place);
            }
        });
    }

    function showMyListInfoWindow(marker, place) {
        // 각 마커별 고유한 closeBtnId 생성
        var closeBtnId = 'infowindow-close-' + place.id;
        var iwContent = `
            <div style="position:relative;padding:10px;font-size:14px;max-width:200px;">
                <div style="position:absolute;right:5px;top:5px;cursor:pointer;" id="${closeBtnId}" class="infowindow-close-btn">X</div>
                <b>${place.place_name}</b><br>
                주소: ${place.address_name || "정보 없음"}
            </div>
        `;

        marker.myInfoWindow.setContent(iwContent);
        marker.myInfoWindow.open(map, marker);
        marker.myInfoOpen = true;

        kakao.maps.event.addListener(marker.myInfoWindow, 'domready', function() {
            var closeBtn = document.getElementById(closeBtnId);
            if (closeBtn) {
                closeBtn.addEventListener("click", function() {
                    // X 버튼으로 인포윈도우 닫기
                    marker.myInfoWindow.close();
                    marker.myInfoOpen = false;
                });
            }
        });
    }

    function createSearchListItem(place, index, marker) {
        var listItem = document.createElement("div");
        listItem.className = "listItem";

        var buttonHtml = place.inMyList 
           ? "<span style='color:gray;'>저장됨</span>" 
           : `<button type="button" data-place-id="${place.id}">저장</button>`;

        listItem.innerHTML = `
            <span><b>${index + 1}. ${place.place_name}</b></span>
            ${buttonHtml}
        `;

        listItem.addEventListener('click', function (e) {
            if (e.target.tagName.toLowerCase() !== 'button') {
                if (!marker.isMyList) {
                    updateActiveMarker(marker);
                    showInfoWindowClick(marker, place);
                    map.setCenter(marker.getPosition());
                }
            }
        });

        if (!place.inMyList) {
            var saveBtn = listItem.querySelector('button');
            saveBtn.addEventListener('click', function(evt) {
                evt.stopPropagation();
                addToMyList(place, marker);
            });
        }

        return listItem;
    }

    function addToMyList(place, marker) {
        // 기존 검색 인포윈도우 닫기
        infoWindowClick.close();
        resetActiveMarker();

        var coords = new kakao.maps.LatLng(place.y, place.x);
        var myMarker = createMarker(coords, markerImageYellow);
        myMarker.setMap(map);
        myMarker.isMyList = true; 
        myMarker.placeId = place.id;
        myMarker.originalIndex = place.originalIndex;

        myListMarkers.push(myMarker);
        myList.push(place);

        place.inMyList = true;
        marker.setMap(null);

        addMarkerClickEvent(myMarker, place);

        renderSearchListAgain();
        renderMyList();

        // 추가하자마자 마이리스트 인포윈도우 표시
        showMyListInfoWindow(myMarker, place);
    }

    function renderMyList() {
        myListEl.innerHTML = "";
        if (myList.length === 0) {
            myListEl.innerHTML = "<p>저장된 장소가 없습니다.</p>";
            return;
        }

        myList.forEach(function (place, index) {
            var listItem = document.createElement("div");
            listItem.className = "listItem";
            listItem.innerHTML = `
                <span>${index + 1}. ${place.place_name}</span>
                <button type="button" style="color: red;">삭제</button>
            `;
            var delBtn = listItem.querySelector('button');
            delBtn.addEventListener('click', function(e) {
                e.stopPropagation();
                removeFromMyList(index);
            });

            myListEl.appendChild(listItem);
        });
    }

    function removeFromMyList(index) {
        var place = myList[index];
        var myMarker = myListMarkers[index];

        // 마커 인포윈도우 열려있다면 닫기
        if (myMarker.myInfoWindow && myMarker.myInfoOpen) {
            myMarker.myInfoWindow.close();
            myMarker.myInfoOpen = false;
        }

        myMarker.setMap(null);
        myList.splice(index, 1);
        myListMarkers.splice(index, 1);
        place.inMyList = false;

        // 검색 마커 복원
        var originalMarker = searchMarkers.find(m => m.placeId === place.id);
        if (originalMarker) {
            originalMarker.setMap(map);
        }

        renderMyList();
        renderSearchListAgain();
    }

    function renderSearchListAgain() {
        placesListEl.innerHTML = "";
        searchMarkers.forEach(m => m.setMap(null));
        renderSearchResults(false);
    }

    function updateActiveMarker(marker) {
        if (activeMarker && activeMarker !== marker) {
            resetMarkerImage(activeMarker);
        }
        marker.setImage(markerImageActive);
        activeMarker = marker;
    }

    function resetMarkerImage(marker) {
        if (marker.isMyList) {
            marker.setImage(markerImageYellow);
        } else {
            marker.setImage(null); 
        }
    }

    function resetActiveMarker() {
        if (activeMarker) {
            resetMarkerImage(activeMarker);
            activeMarker = null;
        }
    }

    function showInfoWindowClick(marker, place) {
        infoWindowHover.close();
        infoWindowClick.close();

        var iwContent = `
            <div style="padding:10px;font-size:14px;">
                <b>${place.place_name}</b><br>
                주소: ${place.address_name || "정보 없음"}
            </div>
        `;
        infoWindowClick.setContent(iwContent);
        infoWindowClick.open(map, marker);
    }

    function closeInfoWindows() {
        infoWindowHover.close();
        infoWindowClick.close();
    }

    function clearSearchResults() {
        searchMarkers.forEach(function(marker) {
            marker.setMap(null);
        });
        searchMarkers = [];
        searchList = [];

        searchMarkersOriginal.forEach(function(marker){
            marker.setMap(null);
        });
        searchMarkersOriginal = [];
        searchResultsOriginal = [];

        placesListEl.innerHTML = "";
    }
});
