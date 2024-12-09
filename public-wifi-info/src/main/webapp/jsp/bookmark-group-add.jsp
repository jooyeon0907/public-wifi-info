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
		<form action="bookmark-group-add-submit.jsp" method="get">
			<table id="customers">
				<colgroups>
					<col style="width: 20%"/>
					<col style="width: 80%"/>
				</colgroups>
				<tbody>
					<tr>
						<th>북마크 이름</th>
						<td><input type="text" name="bookmark_name"></td>
					</tr>
					<tr>
						<th>순서</th>
						<td><input type="text" name="order"></td>
					</tr>
					<tr class="table-botton">
						<td colspan="2">
							<button type="submit">추가</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>