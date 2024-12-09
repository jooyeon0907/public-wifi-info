<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>위치 정보 가져오기</title>
    <script type="text/javascript">
        // 위치 정보를 가져오는 함수
        function getLocation() {
            if (navigator.geolocation) {
                // 브라우저가 위치 정보 지원하는 경우
                navigator.geolocation.getCurrentPosition(function(position) {
                    // 위치 정보가 성공적으로 받아졌을 때
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;

                    // 위치 정보 화면에 출력
                    document.getElementById("location").innerHTML = 
                        "위도: " + latitude + "<br>경도: " + longitude;
                }, function(error) {
                    // 위치 정보 오류 발생시
                    alert("위치 정보를 가져오는 데 실패했습니다.");
                });
            } else {
                // 위치 정보를 지원하지 않는 브라우저
                alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
            }
        }
    </script>
</head>
<body>
    <h1>내 위치 정보</h1>
    <button onclick="getLocation()">내 위치 가져오기</button>
    <div id="location"></div> <!-- 위치 정보 출력 부분 -->
</body>
</html>