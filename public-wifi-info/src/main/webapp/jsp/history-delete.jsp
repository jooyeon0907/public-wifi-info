<%@page import="db.HistoryDAO"%>
<%@page import="db.History"%>
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
	
	<%
			int id = Integer.valueOf(request.getParameter("id"));
		
			HistoryDAO historyDAO = new HistoryDAO();
			History history = historyDAO.getLocalHistory(id);
	%>
	
	<form action="history-delete-submit.jsp" method="post">
		<input type="hidden" name="id" value="<%=id%>">
		<div class="table-div">
			<table id="customers">
				<colgroups>
					<col style="width: 20%"/>
					<col style="width: 80%"/>
				</colgroups>
				<tbody>
					<tr>
						<th>X좌표</th>
						<td><%=history.getX()%></td>
					</tr>
					<tr>
						<th>Y좌표</th>
						<td><%=history.getY()%></td>
					</tr>
					<tr>
						<th>조회일자</th>
						<td><%=history.getSearchDate()%></td>
					</tr>
					<tr class="table-botton">
						<td colspan="2">
							<a href="history.jsp">돌아가기</a>
							 | 
							<input type="submit" value="삭제">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>