<%@page import="db.BookmarkDAO"%>
<%@page import="db.Bookmark"%>
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
		BookmarkDAO bookmarkDAO = new BookmarkDAO();
		Bookmark bookmark = bookmarkDAO.getBookmark(id);
	%>
	
	<form action="bookmark-delete-submit.jsp" method="post">
		<input type="hidden" name="id" value="<%=id%>">
		<div class="table-div">
		<table id="customers">
				<colgroups>
					<col style="width: 20%"/>
					<col style="width: 80%"/>
				</colgroups>
				<tbody>
					<tr>
						<th>북마크 이름</th>
						<td><%=bookmark.getBookMarkame()%></td>
					</tr>
					<tr>
						<th>와이파이명</th>
						<td><%=bookmark.getWifiName()%></td>
					</tr>
					<tr>
						<th>등록일자</th>
						<td><%=bookmark.getRegDate()%></td>
					</tr>
					<tr class="table-botton">
						<td colspan="2">
							<a href="bookmark-list.jsp">돌아가기</a>
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