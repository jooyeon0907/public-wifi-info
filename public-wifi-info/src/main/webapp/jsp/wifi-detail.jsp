<%@page import="db.WifiDAO"%>
<%@page import="db.WifiInfo"%>
<%@page import="db.BookmarkGroupDAO"%>
<%@page import="db.BookmarkGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	
	<%@ include file="header.jsp" %>
	
	<%
		String managerNo = request.getParameter("mgrNo");
		String distance = request.getParameter("distance");
		WifiDAO wifiDAO = new WifiDAO();
		WifiInfo wifi = wifiDAO.getWifiInfo(managerNo);
	%>
	
	<div class="margin-top-10">
		<form action="bookmark-add-submit.jsp" method="post">
			<input type="hidden" name="wifi_manager_no" value="<%=wifi.getManagerNo()%>">
		  	<select name="bg_name_options">
		  		<%
					BookmarkGroupDAO bgDAO = new BookmarkGroupDAO();
					List<BookmarkGroup> bgList = bgDAO.getBookmarkGroupList();	
				%>
	    		<option value="-1">북마크 그룹 이름 선택</option>
				<%
					if (bgList != null || bgList.size() != 0) {
						for(BookmarkGroup bg: bgList) {
				%>
	    			<option value="<%=bg.getId()%>"><%=bg.getName()%></option>			
				<%
						}
					}
				%>
	  		</select>
	  		<input type="submit" value="즐겨찾기 추가하기">	
		</form>
	</div>
	<div class="table-div">
		<table id="customers">
			<colgroups>
				<col style="width: 20%"/>
				<col style="width: 80%"/>
			</colgroups>
			<tbody>
				<tr>
					<th>거리(Km)</th>
					<td><%=distance%></td>
				</tr>
			 <%
			 	if (wifi != null) {
			 %>
				<tr>
					<th>관리번호</th>
					<td><%=wifi.getManagerNo()%></td>
				</tr>
				<tr>
					<th>자치구</th>
					<td><%=wifi.getDistrict()%></td>
				</tr>
				<tr>
					<th>와이파이명</th>
					<td>
						<a href="/jsp/wifi-detail.jsp?mgrNo=<%=managerNo%>&distance=<%=distance%>">
							<%=wifi.getMainName()%>
						</a>
					</td>
				</tr>
				<tr>
					<th>도로명주소</th>
					<td><%=wifi.getAddress1()%></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td><%=wifi.getAddress2()%></td>
				</tr>
				<tr>
					<th>설치위치(층)</th>
					<td><%=wifi.getInstallationFloor()%></td>
				</tr>
				<tr>
					<th>설치유형</th>
					<td><%=wifi.getInstallationType()%></td>
				</tr>
				<tr>
					<th>설치기관</th>
					<td><%=wifi.getInstallationBy()%></td>
				</tr>
				<tr>
					<th>서비스구분</th>
					<td><%=wifi.getServiceType()%></td>
				</tr>
				<tr>
					<th>망종류</th>
					<td><%=wifi.getCmcwr()%></td>
				</tr>
				<tr>
					<th>설치년도</th>
					<td><%=wifi.getConstructionYear()%></td>
				</tr>
				<tr>
					<th>실내외구분</th>
					<td><%=wifi.getInOutDoor()%></td>
				</tr>
				<tr>
					<th>WIFI접속환경</th>
					<td><%=wifi.getRemarks3()%></td>
				</tr>
				<tr>
					<th>X좌표</th>
					<td><%=wifi.getLatitude()%></td>
				</tr>
				<tr>
					<th>Y좌표</th>
					<td><%=wifi.getLongitude()%></td>
				</tr>
				<tr>
					<th>작업일자</th>
					<td><%=wifi.getWorkDatetime()%></td>
				</tr>
			 <%
			 	} else {
			 		
			 	}
			 %>
			</tbody>
		</table>
	</div>
</body>
</html>