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
	
		
	    
		String name = request.getParameter("bookmark_name");
		String order = request.getParameter("order");
		int res = -1;
		
		if (!(name == null || name.equals("") || order == null || order.equals(""))) {
			BookmarkGroupDAO bgDao = new BookmarkGroupDAO();
			res = bgDao.createBookmarkGroup(name, Integer.valueOf(order));		
		}
	
				
		String message = res > 0 ? "북마크 그룹 정보를 추가하였습니다." : "북마크 그룹 정보 추가를 실패하였습니다.";
	    String redirectURL = res > 0 ? "bookmark-group.jsp" : "bookmark-group-add.jsp";
	%>
	
	
</body>
<script>
    alert("<%= message %>");
    window.location.href = "<%= redirectURL %>";
</script>
</html>