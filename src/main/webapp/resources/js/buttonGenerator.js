// 행정구역 리스트 및 중심 좌표
const regions = [
    { name: "서울특별시", lat: 37.5665, lng: 126.9780 },
    { name: "부산광역시", lat: 35.1796, lng: 129.0756 },
    { name: "대구광역시", lat: 35.8714, lng: 128.6014 },
    { name: "인천광역시", lat: 37.4563, lng: 126.7052 },
    { name: "광주광역시", lat: 35.1601, lng: 126.8514 },
    { name: "대전광역시", lat: 36.3504, lng: 127.3845 },
    { name: "울산광역시", lat: 35.5384, lng: 129.3114 },
    { name: "세종특별자치시", lat: 36.4801, lng: 127.2890 },
    { name: "경기도", lat: 37.2751, lng: 127.0092 },
    { name: "강원도", lat: 37.8854, lng: 127.7298 },
    { name: "충청북도", lat: 36.6357, lng: 127.4912 },
    { name: "충청남도", lat: 36.5184, lng: 126.8000 },
    { name: "전라북도", lat: 35.7175, lng: 127.1530 },
    { name: "전라남도", lat: 34.8194, lng: 126.8930 },
    { name: "경상북도", lat: 36.4919, lng: 128.8889 },
    { name: "경상남도", lat: 35.2598, lng: 128.6647 },
    { name: "제주특별자치도", lat: 33.4996, lng: 126.5312 }
];

// 버튼 생성 및 이벤트 추가
(function createRegionButtons() {
    const buttonContainer = document.getElementById("button-container");

    regions.forEach(region => {
        const button = document.createElement("button");
        button.textContent = region.name;
        button.classList.add("region-button");

        button.addEventListener("click", () => {
            const moveLatLon = new kakao.maps.LatLng(region.lat, region.lng);
            window.map.setCenter(moveLatLon); // 전역 map 객체 사용
            console.log(`${region.name}로 지도 중심 이동!`);
        });

        buttonContainer.appendChild(button);
    });

    console.log("행정구역 버튼 생성 완료!");
})();

