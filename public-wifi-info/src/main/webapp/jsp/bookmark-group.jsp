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
	<h1>위치 히스토리 목록</h1>
	
	<%@ include file="header.jsp" %>
	
	<div class="margin-top-10">
		<button type="button" onClick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	</div>
	
	<%
		BookmarkGroupDAO bgDAO = new BookmarkGroupDAO();
		List<BookmarkGroup> bgList = bgDAO.getBookmarkGroupList();	
	%>
	<div class="table-div">
		<table id="customers">
			<thead>
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>순서</th>
					<th>등록일자</th>
					<th>수정일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (bgList == null || bgList.size() == 0) {
				%>
					<tr>
						<td colspan="6" class ="is-not-info" >정보가 존재하지 않습니다.</td>
					</tr>  
				<%						
					} else {
						for(BookmarkGroup bg: bgList) {
					%>
						<tr>
							<td><%=bg.getId()%></td>
							<td><%=bg.getName()%></td>
							<td><%=bg.getOrder()%></td>
							<td><%=bg.getRegDate()%></td>
							<td>
								<%=bg.getLastUpdated() == null? "" : bg.getLastUpdated()%>
							</td>
							<td class="note-btn">
								<a href="bookmark-group-edit.jsp?id=<%=bg.getId()%>" class="padding-2">수정</a>
								<a href="bookmark-group-delete.jsp?id=<%=bg.getId()%>" class="padding-2">삭제</a>
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