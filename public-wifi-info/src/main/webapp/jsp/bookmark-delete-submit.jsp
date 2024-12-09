<%@page import="db.BookmarkDAO"%>
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
	
		BookmarkDAO bookmarkDao = new BookmarkDAO();
		int res = bookmarkDao.deleteBookmark(id);
				
		String message = res > 0 ? "북마크 정보를 삭제하였습니다." : "북마크 정보 삭제를 실패하였습니다.";
	    String redirectURL = res > 0 ? "bookmark-list.jsp" : "bookmark-delete.jsp";
	%>
	
	
</body>
<script>
    alert("<%= message %>");
    window.location.href = "<%= redirectURL %>";
</script>
</html>