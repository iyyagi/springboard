<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="3">글 제목 : ${board.write_title }</td>
		</tr>
		<tr>
			<td colspan="3">글쓴이 : ${board.writer_id }</td>
		</tr>
		<tr>
			<td colspan="1">글 내용 :</td>
			<td colspan="2"><textarea readonly="readonly" cols="40" rows="10">${board.write_content }</textarea></td>
		</tr>

	</table>
	조회 수 : ${board.write_view }
	추천 수 : ${board.write_recommend }
	비추 수 : ${board.write_not_recommend }
	<br>
	<button onclick="location.href='./modify?board_id=${board.board_id}'">글
		수정</button>
	<button onclick="location.href='./delete?board_id=${board.board_id}'">글
		삭제</button>
	<button onclick="location.href='./list?page=1'">글 목록</button>
	<hr />

	<c:choose>
		<c:when test="${not empty replys }">
			<table>
				
				<c:forEach items="${replys }" var="reply">
					<tr>
						<td>닉네임 : ${reply.comment_id }</td>
					</tr>
					<tr>
						
						<td>댓글 내용 : ${reply.comment_view }</td>
						<td>작성일 : ${reply.write_date }</td>
						<td><a href="./replyModify?no=${reply.comment_no }&board_id=${board.board_id}"
							onclick="return confirm('댓글 수정을 하시겠습니까?');">수정</a></td>
						<td><a href="./replyDelete?no=${reply.comment_no }&board_id=${board.board_id}"
							onclick="return confirm('정말로 삭제 하시겠습니까?');">삭제</a></td>
					</tr>
					<tr>
						<td style="background-color: orange"></td>
					</tr>
				</c:forEach>

			</table>
		</c:when>

		<c:otherwise>
			<table>
				<tr>
					<td>댓글을 입력해 주세요.</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<form action="./reply" method="POST">
		<input type="hidden" name="board_id" value="${board.board_id }" />
		닉네임 : <input type="text" name="comment_id" /> 비밀번호 : <input
			type="password" name="comment_pw" /><br>
		<textarea cols="100" rows="5" name="comment_view"></textarea>
	
		<br>
		<button>댓글 달기</button>
	</form>
</body>
</html>