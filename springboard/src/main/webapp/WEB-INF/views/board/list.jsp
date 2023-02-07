<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 연습</h1>
	<table border="1">
		<tr>
			<th class="title">No</th>
			<th class="title">제목</th>
			<th class="title">Name</th>
			<th class="title">View</th>
			<th class="title">작성일</th>
	

		</tr>

		<c:forEach items="${boards }" var="board">
			<tr>
				<td>${board.board_id }</td>
				<td><a href="./contents?board_id=${board.board_id }">${board.write_title }[${board.count }]</a>
				</td>
				<td>${board.writer_id }</td>
				<td>${board.write_view }</td>
				<td>${board.write_date}</td>
			</tr>
		</c:forEach>
			
	</table>
 
	<c:forEach begin="${pagination_start }" end="${pagination_end }"
		var="i">
		<a href="./list?page=${i }">${i }</a>
	</c:forEach>

	<br>
	<a class="btn" href="./write">글쓰기</a>
</body>
</html>