<%@page import="db.HistoryDAO"%>
<%@page import="db.History"%>
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
	<h1>위치 히스토리 목록</h1>
	
	<%@ include file="header.jsp" %>
	<div class="table-div">
		<table id="customers">
			<thead>
				<tr>
					<th>ID</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>조회일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%
					HistoryDAO historyDAO = new HistoryDAO();
					List<History> historyList = historyDAO.getLocalHistoryList();	
					
					if (historyList == null || historyList.size() == 0) {
				%>
					<tr>
						<td colspan="5" class ="is-not-info" >정보가 존재하지 않습니다.</td>
					</tr>  
				<%
  				} else {
						for(History history: historyList) {
  				%>
						<tr>
							<td><%=history.getId()%></td>
							<td><%=history.getX()%></td>
							<td><%=history.getY()%></td>
							<td><%=history.getSearchDate()%></td>
							<td class="note_btn">
								<a href="history-delete.jsp?id=<%=history.getId()%>" class="padding_2">삭제</a>
							</td>
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