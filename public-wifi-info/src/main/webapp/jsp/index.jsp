<%@page import="db.WifiDAO"%>
<%@page import="db.WifiInfo"%>
<%@page import="db.HistoryDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/styles.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
<script>
   function getLocation() {
       if (navigator.geolocation) {
           navigator.geolocation.getCurrentPosition(function(position) {
               var latitude = position.coords.latitude;
               var longitude = position.coords.longitude;

               document.getElementById("lat").value = latitude;
               document.getElementById("lnt").value = longitude;
           }, function(error) {
               alert("위치를 가져오는 데 실패했습니다. 오류 코드: " + error.code);
           });
       } else {
           alert("이 브라우저는 Geolocation을 지원하지 않습니다.");
       }
    }
</script>

	<h1>와이파이 정보 구하기</h1>
	
	<%@ include file="header.jsp" %>
	<%
			String latParam = request.getParameter("lat");
			String lntParam = request.getParameter("lnt");
			
			Double lat = latParam == null || latParam.equals("0.0")? 0.0 : Double.parseDouble(latParam);
			Double lnt = lntParam == null || lntParam.equals("0.0")? 0.0 : Double.parseDouble(lntParam);
	%>
    <div>
        <form action="/" method="get">
        	<div class="input-container">
	            <label for="lat">LAT (위도):</label>
	            <input type="text" id="lat" name="lat" value="<%=lat==null?0.0 : lat%>">
	
	            <label for="lnt">, LNT (경도):</label>
	            <input type="text" id="lnt" name="lnt" value="<%=lnt==null?0.0 : lnt%>">
	
	            <button type="button" onclick="getLocation()">내 위치 가져오기</button><br><br>
	
	            <input type="submit" value="근처 WIFI 정보 보기">
            </div>
        </form>
    </div>
	<div class="table-div">
		<table id="customers">
			<thead>
				<tr>
					<th>거리(Km)</th>
					<th>관리번호</th>
					<th>자치구</th>
					<th>와이파이명</th>
					<th>도로명주소</th>
					<th>상세주소</th>
					<th>설치위치(층)</th>
					<th>설치유형</th>
					<th>설치기관</th>
					<th>서비스구분</th>
					<th>망종류</th>
					<th>설치년도</th>
					<th>실내외구분</th>
					<th>WIFI접속환경</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>작업일자</th>
				</tr>
			</thead>
			<tbody>
	<%
			if (lat == null || lnt == null || lat == 0.0 || lnt == 0.0) {
	%>
				<tr>
					<td colspan="17" class ="is-not-info" >위치 정보를 입력한 후에 조회해 주세요.</td>
				</tr>  
	<%
  			} else {
  		// 위치 히스토리 저장
  		HistoryDAO historyDao = new HistoryDAO();
  		historyDao.createLocalHistory(lat, lnt);
  		
  		
  		
  		// 와이파이 목록 출력
  		WifiDAO wifiDao = new WifiDAO();
  		List<WifiInfo> wifiList = wifiDao.getWifiNearBy(lat, lnt);
  		
  		for(int i=0; i < wifiList.size(); i++) {
  			WifiInfo wifi = wifiList.get(i);
  	%>
				<tr>
					<td><%=wifi.getDistance()%></td>
					<td><%=wifi.getManagerNo()%></td>
					<td><%=wifi.getDistrict()%></td>
					<td>
						<a href="/jsp/wifi-detail.jsp?mgrNo=<%=wifi.getManagerNo()%>&distance=<%=wifi.getDistance()%>">
							<%=wifi.getMainName()%>
						</a>
					</td>
					<td><%=wifi.getAddress1()%></td>
					<td><%=wifi.getAddress2()%></td>
					<td><%=wifi.getInstallationFloor()%></td>
					<td><%=wifi.getInstallationType()%></td>
					<td><%=wifi.getInstallationBy()%></td>
					<td><%=wifi.getServiceType()%></td>
					<td><%=wifi.getCmcwr()%></td>
					<td><%=wifi.getConstructionYear()%></td>
					<td><%=wifi.getInOutDoor()%></td>
					<td><%=wifi.getRemarks3()%></td>
					<td><%=wifi.getLatitude()%></td>
					<td><%=wifi.getLongitude()%></td>
					<td><%=wifi.getWorkDatetime()%></td>
				</tr>
	<%
			}
		}
	%>
			</tbody>
		</table>
	</div>
	
</body>
</html>