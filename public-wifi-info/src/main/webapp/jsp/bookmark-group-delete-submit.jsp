<%@page import="db.BookmarkGroupDAO"%>
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
	
		BookmarkGroupDAO bgDao = new BookmarkGroupDAO();
		int res = bgDao.deleteBookmarkGroup(id);
				
		String message = res > 0 ? "북마크 그룹 정보를 삭제하였습니다." : "북마크 그룹 정보 삭제를 실패하였습니다.";
	    String redirectURL = res > 0 ? "bookmark-group.jsp" : "bookmark-group-delete.jsp";
	%>
	
	
</body>
<script>
    alert("<%= message %>");
    window.location.href = "<%= redirectURL %>";
</script>
</html>