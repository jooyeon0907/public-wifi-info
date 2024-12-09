<%@page import="db.BookmarkGroupDAO"%>
<%@page import="db.BookmarkGroup"%>
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
		BookmarkGroupDAO bgDAO = new BookmarkGroupDAO();
		BookmarkGroup bg = bgDAO.getBookmarkGroup(id);
	%>
	<form action="bookmark-group-edit-submit.jsp?" method="post">
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
						<td> <input type="text" name="bookmark_name" value="<%=bg.getName()%>"> </td>
					</tr>
					<tr>
						<th>순서</th>
						<td><input type="text" name="order" value="<%=bg.getOrder()%>"></td>
					</tr>
					<tr class="table-botton">
						<td colspan="2">
							<a href="bookmark-group.jsp">돌아가기</a>
							 |  
							<input type="submit" value="수정">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>