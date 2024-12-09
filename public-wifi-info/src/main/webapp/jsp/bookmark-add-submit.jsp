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
	    
		String managerNo = request.getParameter("wifi_manager_no");
		int bookmarkGroupId = Integer.valueOf(request.getParameter("bg_name_options"));
		String message = "";
		String redirectURL = "";
		if (bookmarkGroupId < 0) {
			message = "북마크 그룹을 선택해주세요.";
			redirectURL = "wifi-detail.jsp";
		} else {
			BookmarkDAO bookmarkDao = new BookmarkDAO();
			int res = bookmarkDao.createBookmark(managerNo, bookmarkGroupId);
					
			message = res > 0 ? "북마크 정보를 추가하였습니다." : "북마크 정보 추가를 실패하였습니다.";
			redirectURL = res > 0 ? "bookmark-list.jsp" : "wifi-detail.jsp";
		}
	%>
	

</body>
<script>
    alert("<%= message %>");
    window.location.href = "<%= redirectURL %>";
</script>
</html>