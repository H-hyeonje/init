window.markerManager = window.markerManager || (function () {
    let isMarkerMode = false; // 마커 모드 상태
    const markerModeBtn = document.getElementById("marker-mode-btn");
    const myListContainer = document.getElementById("my-list");
    let myList = [];
    let markers = {}; // 마커 저장소

    // 마커 모드 버튼 클릭 이벤트
    markerModeBtn.addEventListener("click", () => {
        isMarkerMode = !isMarkerMode;
        markerModeBtn.textContent = isMarkerMode ? "마커 모드 OFF" : "마커 모드 ON";
    });

    // 지도 클릭 이벤트: 마커 생성
    kakao.maps.event.addListener(window.map, "click", function (mouseEvent) {
        if (!isMarkerMode) return;

        const position = mouseEvent.latLng;
        const uniqueId = `marker-${Date.now()}`;

        // 마커 생성
        const marker = new kakao.maps.Marker({
            position: position,
            map: window.map
        });

        // 인포창 초기화
        const infowindow = new kakao.maps.InfoWindow({
            content: `
                <div style="padding:5px; position:relative;">
                    <button id="close-btn-${uniqueId}" style="
                        position: absolute; 
                        top: 2px; 
                        right: 2px; 
                        background: none; 
                        border: none; 
                        font-size: 12px; 
                        cursor: pointer;">✖</button>
                    <input type="text" id="marker-input-${uniqueId}" placeholder="마커 이름 입력">
                    <button id="register-btn-${uniqueId}">등록</button>
                </div>
            `
        });
        infowindow.open(window.map, marker);

        let isInfoOpen = true;

        // 마커 클릭 시 인포창 토글
        kakao.maps.event.addListener(marker, "click", () => {
            if (isInfoOpen) infowindow.close();
            else infowindow.open(window.map, marker);
            isInfoOpen = !isInfoOpen;
        });

        // 'X' 버튼 클릭 이벤트: 마커 삭제
        setTimeout(() => {
            document.getElementById(`close-btn-${uniqueId}`).addEventListener("click", () => {
                removeMarker(uniqueId, marker, infowindow);
            });
        }, 0);

        // 등록 버튼 클릭 이벤트
        setTimeout(() => {
            document.getElementById(`register-btn-${uniqueId}`).addEventListener("click", () => {
                const markerName = document.getElementById(`marker-input-${uniqueId}`).value.trim();
                if (markerName) {
                    // 인포창 내용 업데이트
                    infowindow.setContent(`
                        <div style="padding:5px; position:relative;">
                            <button id="close-btn-${uniqueId}" style="
                                position: absolute; 
                                top: 2px; 
                                right: 2px; 
                                background: none; 
                                border: none; 
                                font-size: 12px; 
                                cursor: pointer;">✖</button>
                            <strong>${markerName}</strong><br>
                            <input type="checkbox" id="checkbox-${uniqueId}">
                            <label for="checkbox-${uniqueId}">My 리스트에 추가</label>
                        </div>
                    `);

                    // 체크박스 이벤트 추가
                    setTimeout(() => {
                        document.getElementById(`checkbox-${uniqueId}`).addEventListener("change", (e) => {
                            if (e.target.checked) {
                                addToMyList(markerName, position, marker, uniqueId);
                            } else {
                                removeFromMyList(uniqueId);
                            }
                        });

                        // 'X' 버튼 이벤트 다시 연결
                        document.getElementById(`close-btn-${uniqueId}`).addEventListener("click", () => {
                            removeMarker(uniqueId, marker, infowindow);
                        });
                    }, 0);
                } else {
                    alert("마커 이름을 입력해주세요!");
                }
            });
        }, 0);

        // 마커 모드 종료
        isMarkerMode = false;
        markerModeBtn.textContent = "마커 모드 ON";
    });

    // 마커 및 My 리스트에서 제거
    function removeMarker(id, marker, infowindow) {
        if (marker) {
            marker.setMap(null); // 지도에서 마커 제거
        }
        if (infowindow) {
            infowindow.close(); // 인포창 닫기
        }
        removeFromMyList(id); // My 리스트에서 제거
    }

    // My 리스트에 추가
    function addToMyList(name, position, marker, id) {
        myList.push({ id, name, lat: position.getLat(), lng: position.getLng(), marker });
        markers[id] = marker; // 마커 저장
        updateMyList();
    }

    // My 리스트에서 삭제
    function removeFromMyList(id) {
        myList = myList.filter(item => item.id !== id);

        // 체크박스 상태 해제
        const checkbox = document.getElementById(`checkbox-${id}`);
        if (checkbox) checkbox.checked = false;

        updateMyList();
    }

    // My 리스트 업데이트
    function updateMyList() {
        myListContainer.innerHTML = "";
        myList.forEach(item => {
            const listItem = document.createElement("li");
            listItem.textContent = `${item.name} (${item.lat.toFixed(4)}, ${item.lng.toFixed(4)})`;

            const deleteBtn = document.createElement("button");
            deleteBtn.textContent = "삭제";
            deleteBtn.style.marginLeft = "10px";

            deleteBtn.addEventListener("click", () => {
                removeMarker(item.id, item.marker, null);
            });

            listItem.appendChild(deleteBtn);
            myListContainer.appendChild(listItem);
        });
    }

    return {};
})();


