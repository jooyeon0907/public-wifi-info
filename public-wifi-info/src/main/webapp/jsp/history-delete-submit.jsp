<%@page import="db.HistoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
		    
			int id = Integer.valueOf(request.getParameter("id"));

			HistoryDAO historyDAO = new HistoryDAO();
			int res = historyDAO.deleteLocalHistory(id);
			
			String message = res > 0 ? "위치 히스토리 정보를 삭제하였습니다." : "위치 히스토리 정보 삭제를 실패하였습니다.";
		    String redirectURL = res > 0 ? "history.jsp" : "history-delete.jsp";
	%>
	
	
</body>
<script>
    alert("<%= message %>");
    window.location.href = "<%= redirectURL %>";
</script>
</html>